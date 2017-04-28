package classes
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Color

trait Individu {
  var color : Color
  def mutate
  def draw(g: Graphics2D)
}