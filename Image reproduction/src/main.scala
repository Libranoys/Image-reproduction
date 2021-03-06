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
import classes.EngineWoWin

object main extends App {

  var FILENAME = ""

  var MAX_POP = 50
  var ITERATION_NUMBER = 1000

  val ELLIPSE = 1
  val CERCLE = 2
  val POLYGONE = 3
  var CHOICE = 1

  var rendu = ""
  var confirm = ""

  while (FILENAME == "") {
    print("Veuillez entrer le nom d'une image (ex: joconde.png): ")
    FILENAME = StdIn.readLine()
    print("Veuillez entrer un nombre d'itération (ex: 1000): ")
    ITERATION_NUMBER = StdIn.readInt()
    print("Veuillez entrer le nombre coorespondant au type d'individu (1 => ELLIPSE, 2 => CERCLE, 3 => POLYGONE): ")
    CHOICE = StdIn.readInt()
    print("Veuillez entrer le nombre d'individu: ")
    MAX_POP = StdIn.readInt()
    print("Voulez-vous afficher voir le rendu en temps reel ? (y, n)")
    rendu = StdIn.readLine().trim()
    if (rendu.toLowerCase() != "y" && rendu.toLowerCase() != "n") {
      println("Veuillez rentrer une entrée correct")
      System.exit(1)
    }
    
    val shape_intro = CHOICE match {
      case ELLIPSE  => "Ellipse"
      case POLYGONE => "Polygone"
      case CERCLE   => "Cercle"
    }
    val win_intro = rendu match {
      case "y" => "Avec fenetre"
      case "n" => "Sans fenetre"
    }

    println("Résumé : \nImage : "+FILENAME+"\nNombre d'itérations : "+ITERATION_NUMBER+"\nForme: "+shape_intro+"\nPopulation : "+MAX_POP+"\n"+win_intro+"\nConfirmation ? (y, n)")
    confirm = StdIn.readLine().trim()
    if (confirm.toLowerCase() != "y") {
      println("Veuillez recommencez")
      System.exit(1)
    }
  }
  val shape_name = CHOICE match {
    case ELLIPSE  => "el"
    case POLYGONE => "po"
    case CERCLE   => "ce"
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
  
  val DNA_Factory = new DNA(CHOICE)

  val DNA_BEST = DNA_Factory.initDna(MAX_POP, SIZE)
  val DNA_TEST = DNA_Factory.initDna(MAX_POP, SIZE)
  DNA_Factory.copyDNA(DNA_BEST, DNA_TEST)

  DNA_Factory.drawDNA(CANVAS_BEST, DNA_BEST, IMAGE_BEST.getWidth, IMAGE_BEST.getHeight)
  DNA_Factory.drawDNA(CANVAS_TEST, DNA_TEST, IMAGE_TEST.getWidth, IMAGE_TEST.getHeight)

  val window = rendu match {
    case "y" => true
    case "n" => false
  }
  
  // Si on active la sauvegarde regulire d'images (dans Engine)
  
  /* val dir = new File("img_out")
  dir.mkdir() */

  val engine = new Engine(DNA_TEST, DNA_BEST, IMAGE_TEST, IMAGE_BEST, IMAGE_INPUT, DNA_Factory)
  if (window) {
    val engine = new Engine(DNA_TEST, DNA_BEST, IMAGE_TEST, IMAGE_BEST, IMAGE_INPUT, DNA_Factory)
    engine.start(ITERATION_NUMBER)

    // Sauvegarde données pour test
    /*
    val f = new File(name + "_" + shape_name + "_datas.csv")
    val writer = CSVWriter.open(f)
    val csvschema = List("time", "iterations", "mutations", "fitness")
    var listRecords: List[List[Any]] = List()
    listRecords = listRecords ++ List(csvschema)
    for (i <- 0 to engine.time.length - 1) {
      listRecords = listRecords ++ List(List(engine.time(i), engine.iterations(i), engine.mutations(i), engine.fitness(i)))
    }
    writer.writeAll(listRecords)
    writer.close
    */

    ImageIO.write(IMAGE_BEST, "png", new File(name + "_out.png"))
  } else {
    val engine = new EngineWoWin(DNA_TEST, DNA_BEST, IMAGE_TEST, IMAGE_BEST, IMAGE_INPUT, DNA_Factory)
    engine.start(ITERATION_NUMBER)

    // Sauvegarde données pour test
    /*
    val f = new File(name + "_" + shape_name + "_datas.csv")
    val writer = CSVWriter.open(f)
    val csvschema = List("time", "iterations", "mutations", "fitness")
    var listRecords: List[List[Any]] = List()
    listRecords = listRecords ++ List(csvschema)
    for (i <- 0 to engine.time.length - 1) {
      listRecords = listRecords ++ List(List(engine.time(i), engine.iterations(i), engine.mutations(i), engine.fitness(i)))
    }
    writer.writeAll(listRecords)
    writer.close
    */

    ImageIO.write(IMAGE_BEST, "png", new File(name + "_out.png"))
  }

}