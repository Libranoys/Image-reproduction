package classes

import java.awt.Color
import java.awt.Graphics2D
import java.awt.geom.Ellipse2D
import scala.util.Random

class Ellipse(x: Double, y: Double, w: Double, h: Double, c: Color, s:(Int, Int)) extends Individu {

  var width = w
  var height = h
  var xPos = x
  var yPos = y
  var color = c
  val winSize = s

  def mutate(): Unit = {
    val rand = new Random
    val r = rand.nextFloat() * 2
    if (r < 1) {

      if (r < 0.25) this.color = new Color(rand.nextInt(255), this.color.getGreen, this.color.getBlue, this.color.getAlpha)
      else if (r < 0.5) this.color = new Color(this.color.getRed, rand.nextInt(255), this.color.getBlue, this.color.getAlpha)
      else if (r < 0.75) this.color = new Color(this.color.getRed, this.color.getGreen, rand.nextInt(255), this.color.getAlpha)
      else if (r < 1) this.color = new Color(this.color.getRed, this.color.getGreen, this.color.getBlue, rand.nextInt(255))

    } else {
      if (r < 1.25) this.width = rand.nextInt(this.winSize._1)
      else if (r < 1.5) this.height = rand.nextInt(this.winSize._2)
      else if (r < 1.75) this.xPos = rand.nextInt(this.winSize._1)
      else if (r < 2) this.yPos = rand.nextInt(this.winSize._2)
    }
  }

  def draw(g: Graphics2D) = {
    g.setColor(this.color)
    g.fill(new Ellipse2D.Double(this.xPos - (this.height / 2), this.yPos - (this.width / 2), this.width, this.height))
  }

  def copy: Ellipse = {
    new Ellipse(this.xPos, this.yPos, this.width, this.height, this.color, this.winSize)
  }
}

object EllipseFactory {
  
  def random(winSize : (Int, Int)) : Ellipse = {
    val rand = new Random
    new Ellipse(rand.nextInt(winSize._1), rand.nextInt(winSize._2), rand.nextInt(winSize._1), rand.nextInt(winSize._2), new Color(0,0,0, 0.1.asInstanceOf[Float]), winSize)
  }
}