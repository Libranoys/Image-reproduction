import java.awt.image.BufferedImage
import utils._
import javax.imageio.ImageIO
import java.io.File
import classes.Cercle
import scala.util.Random
import java.awt.Color
import java.awt.geom.Ellipse2D
import classes.Engine
import scala.io.StdIn

object main extends App {

  var FILENAME = ""

  val MAX_POP = 50
  var ITERATION_NUMBER = 1000

  val ELLIPSE = 1
  val CERCLE = 2
  val POLYGONE = 3
  var CHOICE = 1

  while (FILENAME == "") {
    print("Veuillez entrer le nom d'une image (ex: joconde.jpg): ")
    FILENAME = StdIn.readLine()
    print("Veuillez entrer un nombre d'itération (ex: 1000): ")
    ITERATION_NUMBER = StdIn.readInt()
    print("Veuillez entrer le nombre coorespondant au type d'individu (1 => ELLIPSE, 2 => CERCLE, 3 => POLYGONE): ")
    CHOICE = StdIn.readInt()
  }

  val IMAGE_INPUT = ImageIO.read(new File(FILENAME))

  val SIZE = (IMAGE_INPUT.getWidth, IMAGE_INPUT.getHeight)

  val IMAGE_TEST = new BufferedImage(SIZE._1, SIZE._2, BufferedImage.TYPE_INT_RGB)
  val CANVAS_TEST = IMAGE_TEST.createGraphics

  val IMAGE_BEST = new BufferedImage(SIZE._1, SIZE._2, BufferedImage.TYPE_INT_RGB)
  val CANVAS_BEST = IMAGE_BEST.createGraphics

  Utils.InitCanvas(CANVAS_TEST, IMAGE_TEST.getWidth, IMAGE_TEST.getHeight)
  Utils.InitCanvas(CANVAS_BEST, IMAGE_BEST.getWidth, IMAGE_BEST.getHeight)

  val DNA_Factory = new DNA(CHOICE)

  var DNA_BEST = DNA_Factory.initDna(MAX_POP, SIZE)
  var DNA_TEST = DNA_Factory.initDna(MAX_POP, SIZE)
  DNA_Factory.copyDNA(DNA_BEST, DNA_TEST)

  DNA_Factory.drawDNA(CANVAS_BEST, DNA_BEST, IMAGE_BEST.getWidth, IMAGE_BEST.getHeight)
  DNA_Factory.drawDNA(CANVAS_TEST, DNA_TEST, IMAGE_TEST.getWidth, IMAGE_TEST.getHeight)

  val engine = new Engine(DNA_TEST, DNA_BEST, IMAGE_TEST, IMAGE_BEST, IMAGE_INPUT, DNA_Factory)
  engine.start(ITERATION_NUMBER)
  

  ImageIO.write(IMAGE_BEST, "png", new File(FILENAME + "out.png"))

}