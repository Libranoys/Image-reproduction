import java.awt.image.BufferedImage
import utils._
import javax.imageio.ImageIO
import java.io.File
import classes.Cercle
import scala.util.Random
import java.awt.Color
import java.awt.geom.Ellipse2D
import classes.Engine

object main extends App {

  val filename = "joconde.jpg"
  val MAX_POP = 50
  val SIZE = (500, 500)

  val IMAGE_TEST = new BufferedImage(SIZE._1, SIZE._2, BufferedImage.TYPE_INT_RGB)
  val CANVAS_TEST = IMAGE_TEST.createGraphics

  val IMAGE_BEST = new BufferedImage(SIZE._1, SIZE._2, BufferedImage.TYPE_INT_RGB)
  val CANVAS_BEST = IMAGE_BEST.createGraphics

  Utils.InitCanvas(CANVAS_TEST, IMAGE_TEST.getWidth, IMAGE_TEST.getHeight)
  Utils.InitCanvas(CANVAS_BEST, IMAGE_BEST.getWidth, IMAGE_BEST.getHeight)
  
  val IMAGE_INPUT = ImageIO.read(new File(filename))

  val rand = new Random

  var DNA_BEST = new Cercle(rand.nextInt(SIZE._1), rand.nextInt(SIZE._2), rand.nextInt(50), new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)))
  var DNA_TEST = new Cercle(0, 0, 0, Color.WHITE)
  DNA.copyDNA(DNA_BEST, DNA_TEST)
  
  DNA_BEST.draw(CANVAS_BEST)
  DNA_TEST.draw(CANVAS_TEST)
  
  val engine = new Engine(DNA_TEST, DNA_BEST, IMAGE_TEST, IMAGE_BEST, IMAGE_INPUT)
  engine.start
  
  ImageIO.write(IMAGE_BEST, "png", new File("drawing.png"))

}