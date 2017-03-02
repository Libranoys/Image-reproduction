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
    val r = rand.nextInt(4) 
    r match {
      case 0 => this.rayon = rand.nextInt(50)
      case 1 => this.xPos = rand.nextInt(500)
      case 2 => this.yPos = rand.nextInt(500)
      case 3 => this.color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255))
    }
  }

  def draw(g: Graphics2D) = {
    g.setColor(this.color)
    g.fill(new Ellipse2D.Double(this.xPos - this.rayon, this.yPos - this.rayon, 2 * this.rayon, 2 * this.rayon))
    println(this.color + " "+ 2 * this.rayon + ";" + 2 * this.rayon + ";" + (this.xPos - this.rayon) + ";" + (this.yPos - this.rayon))
  }
}