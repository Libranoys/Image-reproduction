package classes

import java.awt.Color
import scala.util.Random
import java.awt.Graphics2D

class Polygone(vertexA: Array[(Int, Int)], c: Color, s: (Int, Int)) extends Individu {
  var vertexArray = vertexA
  var color = c
  var nbrCote = this.vertexArray.size
  val winSize = s
  
  //Initalisations du tableaux de vertices
  var xArray = new Array[Int](this.nbrCote)
  var yArray = new Array[Int](this.nbrCote)
  for (indexVertex <- 0 to this.nbrCote - 1) {
    xArray(indexVertex) = this.vertexArray(indexVertex)._1
    yArray(indexVertex) = this.vertexArray(indexVertex)._2
  }

  def draw(g: Graphics2D) = {
    g.setColor(this.color)
    g.fillPolygon(xArray, yArray, this.nbrCote)
  }

  def copy: Polygone = {
    new Polygone(this.vertexArray, this.color, this.winSize)
  }

  def mutate = {
    val rand = new Random
    val r = rand.nextFloat() * 2

    if (r < 1) {
      if (r < 0.25) this.color = new Color(rand.nextInt(255), this.color.getGreen, this.color.getBlue, this.color.getAlpha)
      else if (r < 0.5) this.color = new Color(this.color.getRed, rand.nextInt(255), this.color.getBlue, this.color.getAlpha)
      else if (r < 0.75) this.color = new Color(this.color.getRed, this.color.getGreen, rand.nextInt(255), this.color.getAlpha)
      else if (r < 1) this.color = new Color(this.color.getRed, this.color.getGreen, this.color.getBlue, rand.nextInt(255))
    } else {
      val vertexRan = rand.nextInt(this.nbrCote)
      if (r < 1.5) this.xArray(vertexRan) = rand.nextInt(this.winSize._1)
      else if (r < 2) this.yArray(vertexRan) = rand.nextInt(this.winSize._2)
    }
  }

}

object PolygoneFactory {
  def random(faceNumber: Int, winSize: (Int, Int)): Polygone = {
    val rand = new Random
    val vertexArray = new Array[(Int, Int)](faceNumber)
    for (index <- 0 to faceNumber - 1) {
      vertexArray(index) = (rand.nextInt(winSize._1), rand.nextInt(winSize._2))

    }
    val c = new Color(0, 0, 0, 0.001.asInstanceOf[Float])
    new Polygone(vertexArray, c, winSize)
  }
}