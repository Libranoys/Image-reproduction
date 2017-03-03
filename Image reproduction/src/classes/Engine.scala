package classes

import java.awt.Graphics2D
import utils.Utils
import java.awt.image.BufferedImage
import utils.DNA
import java.io.File
import javax.imageio.ImageIO

class Engine(dna_te: Array[Individu], dna_be: Array[Individu], img_te: BufferedImage, img_be: BufferedImage, img_inp: BufferedImage, d: DNA) {

  var dna_t = dna_te
  var dna_b = dna_be
  var img_t = img_te
  var img_b = img_be
  val img_input = img_inp
  val DNA = d

  var modifs = 0

  var fitnessBest = Utils.calculFitness(img_t, img_input)

  private def evolve() : Boolean = {

    var doneSomething = false
    val indexGene = DNA.mutate(dna_t)
    DNA.drawDNA(img_t.getGraphics.asInstanceOf[Graphics2D], dna_t, img_t.getWidth, img_t.getHeight)
    val fitnessTest = Utils.calculFitness(img_t, img_input)

  
    if (fitnessTest < fitnessBest) {
      modifs = modifs + 1
      DNA.copyGene(dna_t, dna_b, indexGene)
      DNA.drawDNA(img_b.getGraphics.asInstanceOf[Graphics2D], dna_b, img_b.getWidth, img_b.getHeight)
      fitnessBest = fitnessTest
      doneSomething = true
    } else {
      DNA.copyGene(dna_b, dna_t, indexGene)
      Utils.deepCopy(img_b)
    }
    doneSomething
  }


  def start(iteration:Int) = {
    
    var countIteration = 0
    var countMutation = 0
    while (countIteration < iteration) {
      if(evolve()) {
        countMutation += 1
      }
      countIteration +=1
      if (countIteration % 1 == 0)
        println("Iteration num: " + 100*(countIteration.toFloat/iteration.toFloat) + "% ; Mutation num: " + countMutation)
      //if(countIteration %50==0)
       // ImageIO.write(img_b, "png", new File(countIteration + "out.png"))
    }
  }

}