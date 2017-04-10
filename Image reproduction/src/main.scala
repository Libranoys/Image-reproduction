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
import java.io.BufferedWriter
import java.io.FileWriter
import classes.Polygone
import classes.Polygone
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.ImageIcon
import javax.swing.JPanel
import java.awt.GridLayout
import com.github.tototoshi.csv.CSVWriter
import scala.collection.mutable.ListBuffer

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
  val shape_name = CHOICE match {
    case ELLIPSE => "el"
    case POLYGONE => "po"        
    case CERCLE => "ce"
  }
  
  val name = FILENAME.substring(0, FILENAME.lastIndexOf('.'))

  val IMAGE_INPUT = ImageIO.read(new File(FILENAME))

  val SIZE = (IMAGE_INPUT.getWidth, IMAGE_INPUT.getHeight)

  val IMAGE_TEST = new BufferedImage(SIZE._1, SIZE._2, BufferedImage.TYPE_INT_ARGB)
  val CANVAS_TEST = IMAGE_TEST.createGraphics

  val IMAGE_BEST = new BufferedImage(SIZE._1, SIZE._2, BufferedImage.TYPE_INT_ARGB)
  val CANVAS_BEST = IMAGE_BEST.createGraphics

  Utils.InitCanvas(CANVAS_TEST, IMAGE_TEST.getWidth, IMAGE_TEST.getHeight)
  Utils.InitCanvas(CANVAS_BEST, IMAGE_BEST.getWidth, IMAGE_BEST.getHeight)
CANVAS_BEST.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON)
  val DNA_Factory = new DNA(CHOICE)

  val DNA_BEST = DNA_Factory.initDna(MAX_POP, SIZE)
  val DNA_TEST = DNA_Factory.initDna(MAX_POP, SIZE)
  DNA_Factory.copyDNA(DNA_BEST, DNA_TEST)

  DNA_Factory.drawDNA(CANVAS_BEST, DNA_BEST, IMAGE_BEST.getWidth, IMAGE_BEST.getHeight)
  DNA_Factory.drawDNA(CANVAS_TEST, DNA_TEST, IMAGE_TEST.getWidth, IMAGE_TEST.getHeight)

  val engine = new Engine(DNA_TEST, DNA_BEST, IMAGE_TEST, IMAGE_BEST, IMAGE_INPUT, DNA_Factory)
  engine.start(ITERATION_NUMBER)
  
  // Sauvegarde données pour test
  val f = new File(name + "_"+shape_name+"_datas.csv")
  val writer = CSVWriter.open(f)
  val csvschema = List("time", "iterations", "mutations", "fitness")
  var listRecords : List[List[Any]] = List()
  listRecords = listRecords ++ List(csvschema)
  for (i <- 0 to engine.time.length-1) {
    listRecords = listRecords ++ List(List(engine.time(i), engine.iterations(i), engine.mutations(i), engine.fitness(i)))
  }
  writer.writeAll(listRecords)
  writer.close
  
  
  ImageIO.write(IMAGE_BEST, "png", new File(name + "_out.png"))

}