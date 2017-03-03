package classes

import java.awt.Color
import java.awt.Graphics2D
import java.awt.geom.Ellipse2D
import scala.util.Random

class Cercle(x: Double, y: Double, r: Double, c: Color) extends Individu {
  var rayon = r
  var xPos = x
  var yPos = y
  var color = c

  def mutate(): Unit = {
    val rand = new Random
    val r = rand.nextFloat() * 2
    if (r < 1) {
      if (r < 0.25) this.color = new Color(rand.nextInt(255), this.color.getGreen, this.color.getBlue, this.color.getAlpha)
      else if (r < 0.5) this.color = new Color(this.color.getRed, rand.nextInt(255), this.color.getBlue, this.color.getAlpha)
      else if (r < 0.75) this.color = new Color(this.color.getRed, this.color.getGreen, rand.nextInt(255), this.color.getAlpha)
      else if (r < 1) this.color = new Color(this.color.getRed, this.color.getGreen, this.color.getBlue, rand.nextInt(255))
    } else {
      if (r < 1.33) this.rayon = rand.nextInt(100)
      else if (r < 1.66) this.xPos = rand.nextInt(500)
      else if (r < 2) this.yPos = rand.nextInt(500)

    }
  }

  def draw(g: Graphics2D) = {
    g.setColor(this.color)
    g.fill(new Ellipse2D.Double(this.xPos - this.rayon, this.yPos - this.rayon, 2 * this.rayon, 2 * this.rayon))
    //println(this.color + " "+ 2 * this.rayon + ";" + 2 * this.rayon + ";" + (this.xPos - this.rayon) + ";" + (this.yPos - this.rayon))
  }
}