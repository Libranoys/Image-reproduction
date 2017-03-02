package classes

import java.awt.Graphics2D
import utils.Utils
import java.awt.image.BufferedImage
import utils.DNA

class Engine(dna_te: Cercle, dna_be: Cercle, img_te: BufferedImage, img_be: BufferedImage, img_inp: BufferedImage) {

  var dna_t = dna_te
  var dna_b =   dna_be
  var img_t = img_te
  var img_b = img_be
  val img_input = img_inp

  var fitnessBest = Utils.calculFitness(img_b, img_input)

  private def evolve() = {
    dna_t.mutate()
    dna_t.draw(img_t.getGraphics.asInstanceOf[Graphics2D])
    val fitnessTest = Utils.calculFitness(img_t, img_input)
    
    if(fitnessTest >= fitnessBest) {
      DNA.copyDNA(this.dna_t, this.dna_b)
      dna_b.draw(img_b.getGraphics.asInstanceOf[Graphics2D])
      fitnessBest = fitnessTest
    }else {
      DNA.copyDNA(this.dna_b, this.dna_t)
      this.img_t = this.img_b
    }
  }
  
  def start = {
    var i = 0 
    while(i<1000) {
      evolve()
      i= i+1
      println(i)
    }
  }

}