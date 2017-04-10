package classes

import java.awt.Graphics2D
import utils.Utils
import java.awt.image.BufferedImage
import utils.DNA
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.ImageIcon
import javax.swing.JPanel
import javax.swing.JButton

class Engine(dna_te: Array[Individu], dna_be: Array[Individu], img_te: BufferedImage, img_be: BufferedImage, img_inp: BufferedImage, d: DNA) {

  var timeBegin = System.currentTimeMillis()
  var dna_t = dna_te
  var dna_b = dna_be
  var img_t = img_te
  var img_b = img_be
  val img_input = img_inp
  val DNA = d
  var nb_save = 0
  
  val SIZE = (img_t.getWidth, img_t.getHeight)
  val MAX = SIZE._1 * SIZE._2 * 3 * 255  
  
  val frame = new JFrame
  frame.setTitle("Frame")
  frame.setSize(SIZE._1, SIZE._2)
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  var label = new JLabel(new ImageIcon(img_b))
  
  var time : List[Long] = List()
  var fitness : List[Double] = List()
  var mutations : List[Int] = List()
  var iterations : List[Int] = List()

  val panel = new JPanel

  var modifs = 0

  var fitnessBest = Utils.calculFitness(img_t, img_input)

  private def evolve(): Boolean = {

    var doneSomething = false
    val indexGene = DNA.mutate(dna_t)
    DNA.drawDNA(img_t.getGraphics.asInstanceOf[Graphics2D], dna_t, img_t.getWidth, img_t.getHeight)
    val fitnessTest = Utils.calculFitness(img_t, img_input)
    if (fitnessTest < fitnessBest) {

      label = new JLabel(new ImageIcon(img_b))

      modifs = modifs + 1
      DNA.copyGene(dna_t, dna_b, indexGene)
      DNA.drawDNA(img_b.getGraphics.asInstanceOf[Graphics2D], dna_b, img_b.getWidth, img_b.getHeight)
      this.fitnessBest = fitnessTest
      doneSomething = true
    } else {
      DNA.copyGene(dna_b, dna_t, indexGene)
    }
    doneSomething

  }

  def start(iteration: Int) = {

    panel.setLayout(null)

    label.setBounds(0, 0, SIZE._1, SIZE._2)
    panel.add(label)

    //panel.add(label2)
    frame.add(panel)
    frame.setFocusable(true)
    frame.toFront
    frame.requestFocus
    frame.setLocationRelativeTo(null)
    frame.setVisible(true)
    

    var countIteration = 0
    var countMutation = 0
    while (countIteration < iteration) {
      if (evolve()) {
        countMutation += 1
      }
      frame.repaint()
      countIteration += 1
      /*val step = if ((iteration/1000) < 10)
          10 
      else 
        iteration/1500*/
      if(Utils.save(this.img_b, countMutation, nb_save))
        nb_save +=1
      if (countIteration % 10 == 0) {
        var timeAfter= System.currentTimeMillis()
        time = time ++ List(timeAfter-timeBegin)
        iterations = iterations ++ List(countIteration)
        mutations = mutations++ List(countMutation)
        fitness = fitness ++ List((1-(fitnessBest/MAX.asInstanceOf[Float])).asInstanceOf[Double])
        println("Iteration num: " + 100 * (countIteration.toFloat / iteration.toFloat) + "% ; Mutation num: " + countMutation +" FITNESS: "+100*(1-(fitnessBest/MAX.asInstanceOf[Float]))+"%")
      }//if(countIteration %50==0)
      // ImageIO.write(img_b, "png", new File(countIteration + "out.png"))
    }
  }

}