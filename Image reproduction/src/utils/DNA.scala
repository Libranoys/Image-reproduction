package utils

import classes.Individu
import classes.Cercle
import scala.reflect.ClassTag
import scala.util.Random
import java.awt.Color
import java.awt.Graphics2D
import classes.Ellipse
import classes.PolygoneFactory
import classes.CercleFactory
import classes.EllipseFactory
import classes.Polygone

class DNA(typeIndividu: Int) {

  val typeIndiv = typeIndividu

  var pop = 0
  def copyDNA(dna_in: Array[Individu], dna_out: Array[Individu]) = {
    for (index <- 0 to dna_in.size - 1) {
      copyGene(dna_in, dna_out, index)
    }
  }

  def copyGene(dna_in: Array[Individu], dna_out: Array[Individu], index: Int) = {
    for(i <- 0 to dna_out(index).asInstanceOf[Polygone].xArray.size-1) {
          dna_out(index).asInstanceOf[Polygone].xArray(i) = dna_in(index).asInstanceOf[Polygone].xArray(i)
    }
    for(i <- 0 to dna_out(index).asInstanceOf[Polygone].yArray.size-1) {
          dna_out(index).asInstanceOf[Polygone].yArray(i) = dna_in(index).asInstanceOf[Polygone].yArray(i)
    }
    dna_out(index).asInstanceOf[Polygone].color = dna_in(index).asInstanceOf[Polygone].color 
    dna_out(index).asInstanceOf[Polygone].nbrCote = dna_in(index).asInstanceOf[Polygone].nbrCote 
  }

  def initDna(popSize: Int, winSize: (Int, Int)): Array[Individu] = {
    val rand = new Random
    this.pop = popSize
    var individus = new Array[Individu](popSize)
    for (index <- 0 to individus.size - 1) {
      this.typeIndiv match {
        case 1 => individus(index) = EllipseFactory.random(winSize)
        case 2 => individus(index) = CercleFactory.random(winSize)
        case 3 => individus(index) = PolygoneFactory.random(6, winSize)
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
    val randNumber = (new Random()).nextInt(this.pop)
    dna(randNumber).mutate
    randNumber
  }
}
