package utils

import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Color
import java.awt.image.BufferedImage
import classes.Cercle
import java.awt.image.ColorModel

object Utils {
  def InitCanvas(g: Graphics2D, w: Int, h: Int) = {
    g.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
      java.awt.RenderingHints.VALUE_ANTIALIAS_ON)

    g.setColor(Color.WHITE)
    g.fillRect(0, 0, w, h)

  }

  def calculFitness(img: BufferedImage, img_input: BufferedImage): Long = {
    var total = 0
    for (x <- 0 to img_input.getWidth - 1) {
      for (y <- 0 to img_input.getHeight - 1) {
        val cIn = new Color(img_input.getRGB(x, y))
        val c = new Color(img.getRGB(x, y))
        val dr = cIn.getRed - c.getRed
        val db = cIn.getBlue - c.getBlue
        val dg = cIn.getGreen - c.getGreen
        
        total += Math.abs(dg)+Math.abs(db)+Math.abs(dr)
      }
    }
    total
  }


  def deepCopy(bi: BufferedImage): BufferedImage = {
    val cm = bi.getColorModel();
    val isAlphaPremultiplied = cm.isAlphaPremultiplied();
    val raster = bi.copyData(null);
    return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
  }


}
