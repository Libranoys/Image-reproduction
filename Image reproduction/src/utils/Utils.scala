package utils

import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Color
import java.awt.image.BufferedImage

object Utils {
  def InitCanvas(g: Graphics2D, w:Int, h:Int) = {
    g.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
      java.awt.RenderingHints.VALUE_ANTIALIAS_ON)

    g.setColor(Color.WHITE)
    g.fillRect(0, 0, w, h)

  }
  
  def calculFitness(img : BufferedImage, img_input : BufferedImage) : Long= {
    var total = 0
    for(x <- 0 to img_input.getWidth-1) {
      for(y <- 0 to img_input.getHeight-1) {
        val c = new Color(img_input.getRGB(x, y) - img.getRGB(x, y))
        val dr = c.getRed
        val db = c.getBlue
        val dg = c.getGreen
        total += (dr*dr + db*db + dg*dg)
      }
    }
    total
  }

}
