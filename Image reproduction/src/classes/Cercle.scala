package classes

import java.awt.Color
import java.awt.Graphics2D
import java.awt.geom.Ellipse2D
import scala.util.Random

class Cercle(x: Double, y: Double, r: Double, c: Color, s: (Int, Int)) extends Individu {
  var rayon = r
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
      if (r < 1.33) this.rayon = rand.nextInt(this.winSize._1)
      else if (r < 1.66) this.xPos = rand.nextInt(this.winSize._1)
      else if (r < 2) this.yPos = rand.nextInt(this.winSize._2)

    }
  }

  def draw(g: Graphics2D) = {
    g.setColor(this.color)
    g.fill(new Ellipse2D.Double(this.xPos - this.rayon, this.yPos - this.rayon, 2 * this.rayon, 2 * this.rayon))
  }
  
  def copy : Cercle= {
    new Cercle(this.x, this.y, this.r, this.c, this.winSize)    
  }
}

object CercleFactory {
  def random(winSize:(Int, Int)) : Cercle  = {
    val rand = new Random
    new Cercle(rand.nextInt(winSize._1), rand.nextInt(winSize._2), rand.nextInt(Math.min(winSize._1, winSize._2)), new Color(1, 1, 1, 0.1.asInstanceOf[Float]), winSize)
    
  }
}