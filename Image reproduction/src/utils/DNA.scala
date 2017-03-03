package utils

import classes.Individu
import classes.Cercle
import scala.reflect.ClassTag
import scala.util.Random
import java.awt.Color
import java.awt.Graphics2D
import classes.Ellipse

class DNA(typeIndividu: Int) {

  val typeIndiv = typeIndividu

  var pop = 0
  def copyDNA(dna_in: Array[Individu], dna_out: Array[Individu]) = {
    for (index <- 0 to dna_in.size - 1) {
      this.typeIndiv match {
        case 1 => {
          dna_out(index).asInstanceOf[Ellipse].color = dna_in(index).asInstanceOf[Ellipse].color
          dna_out(index).asInstanceOf[Ellipse].xPos = dna_in(index).asInstanceOf[Ellipse].xPos
          dna_out(index).asInstanceOf[Ellipse].yPos = dna_in(index).asInstanceOf[Ellipse].yPos
          dna_out(index).asInstanceOf[Ellipse].width = dna_in(index).asInstanceOf[Ellipse].width
          dna_out(index).asInstanceOf[Ellipse].height = dna_in(index).asInstanceOf[Ellipse].height
        }
        case 2 => {
          dna_out(index).asInstanceOf[Cercle].color = dna_in(index).asInstanceOf[Cercle].color
          dna_out(index).asInstanceOf[Cercle].xPos = dna_in(index).asInstanceOf[Cercle].xPos
          dna_out(index).asInstanceOf[Cercle].yPos = dna_in(index).asInstanceOf[Cercle].yPos
          dna_out(index).asInstanceOf[Cercle].rayon = dna_in(index).asInstanceOf[Cercle].rayon
        }
      }
    }
  }

  def copyGene(dna_in: Array[Individu], dna_out: Array[Individu], index: Int) = {
    this.typeIndiv match {
      case 1 => {
        dna_out(index).asInstanceOf[Ellipse].color = dna_in(index).asInstanceOf[Ellipse].color
        dna_out(index).asInstanceOf[Ellipse].xPos = dna_in(index).asInstanceOf[Ellipse].xPos
        dna_out(index).asInstanceOf[Ellipse].yPos = dna_in(index).asInstanceOf[Ellipse].yPos
        dna_out(index).asInstanceOf[Ellipse].width = dna_in(index).asInstanceOf[Ellipse].width
        dna_out(index).asInstanceOf[Ellipse].height = dna_in(index).asInstanceOf[Ellipse].height
      }
      case 2 => {
        dna_out(index).asInstanceOf[Cercle].color = dna_in(index).asInstanceOf[Cercle].color
        dna_out(index).asInstanceOf[Cercle].xPos = dna_in(index).asInstanceOf[Cercle].xPos
        dna_out(index).asInstanceOf[Cercle].yPos = dna_in(index).asInstanceOf[Cercle].yPos
        dna_out(index).asInstanceOf[Cercle].rayon = dna_in(index).asInstanceOf[Cercle].rayon
      }
    }

  }

  def initDna(popSize: Int, winSize: (Int, Int)): Array[Individu] = {
    val rand = new Random
    this.pop = popSize
    var individus = new Array[Individu](popSize)
    for (index <- 0 to individus.size - 1) {
      this.typeIndiv match {
        case 1 => individus(index) = new Ellipse(rand.nextInt(winSize._1), rand.nextInt(winSize._2), rand.nextInt(winSize._1), rand.nextInt(winSize._2), new Color(1, 1, 1, 0.1.asInstanceOf[Float]))
        case 2 => individus(index) = new Cercle(rand.nextInt(winSize._1), rand.nextInt(winSize._2), rand.nextInt(Math.min(winSize._1, winSize._2)), new Color(1, 1, 1, 0.1.asInstanceOf[Float]))
      }

    }
    individus
  }

  def drawDNA(g: Graphics2D, dna: Array[Individu], w: Int, h: Int) = {

    g.setColor(Color.WHITE)
    g.fillRect(0, 0, w, h)

    for (elem <- dna) {
      elem.draw(g)
    }
  }

  def mutate(dna: Array[Individu]): Int = {
    val randNumber = new Random().nextInt(this.pop)
    dna(randNumber).mutate
    randNumber
  }
}
