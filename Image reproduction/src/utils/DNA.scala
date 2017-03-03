package utils

import classes.Individu
import classes.Cercle
import scala.reflect.ClassTag
import scala.util.Random
import java.awt.Color
import java.awt.Graphics2D

object DNA {
  var pop =0
  def copyDNA(dna_in: Array[Cercle], dna_out: Array[Cercle]) = {
    for (i <- 0 to dna_in.size-1) {
      dna_out(i).color = dna_in(i).color
      dna_out(i).xPos = dna_in(i).xPos
      dna_out(i).yPos = dna_in(i).yPos
      dna_out(i).rayon = dna_in(i).rayon
    }
  }
  
  def copyGene(dna_in: Array[Cercle], dna_out: Array[Cercle], i: Int) = {
    dna_out(i).color = dna_in(i).color
    dna_out(i).xPos = dna_in(i).xPos
    dna_out(i).yPos = dna_in(i).yPos
    dna_out(i).rayon = dna_in(i).rayon
  }

  def initDna(popSize: Int, winSize: (Int, Int)): Array[Cercle] = {
    val rand = new Random
    this.pop = popSize
    var cercles = new Array[Cercle](popSize)
    for (i <- 0 to cercles.size-1) {
      cercles(i) = new Cercle(rand.nextInt(winSize._1), rand.nextInt(winSize._2), rand.nextInt(100), new Color(1, 1, 1, 0.1.asInstanceOf[Float]))
    }
    cercles
  }
  
  
  def drawDNA(g: Graphics2D, dna: Array[Cercle], w: Int, h: Int) = {
    g.setColor(Color.WHITE)
    g.fillRect(0, 0, w, h)
    
    for (cercle <- dna) {
      cercle.draw(g)
    }
  }
  
  def mutate(dna : Array[Cercle]):Int =  {
    val randN = new Random().nextInt(this.pop)
    dna(randN).mutate() 
    randN
  }

}