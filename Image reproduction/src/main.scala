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

  val filename = "img_in.jpg"
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

  var DNA_BEST = DNA.initDna(MAX_POP, SIZE)
  var DNA_TEST = DNA.initDna(MAX_POP, SIZE)
  DNA.copyDNA(DNA_BEST, DNA_TEST)
  
  DNA.drawDNA(CANVAS_BEST, DNA_BEST, IMAGE_BEST.getWidth, IMAGE_BEST.getHeight)
  DNA.drawDNA(CANVAS_TEST, DNA_TEST, IMAGE_TEST.getWidth, IMAGE_TEST.getHeight)

  val engine = new Engine(DNA_TEST, DNA_BEST, IMAGE_TEST, IMAGE_BEST, IMAGE_INPUT)
  engine.start
  


  
  ImageIO.write(IMAGE_BEST, "png", new File("drawing.png"))

}