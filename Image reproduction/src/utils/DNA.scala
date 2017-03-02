package utils

import classes.Individu
import classes.Cercle

object DNA {

  def copyDNA(dna_in: Cercle, dna_out: Cercle) = {
    dna_out.color = dna_in.color
    dna_out.xPos = dna_in.xPos
    dna_out.yPos = dna_in.yPos
    dna_out.rayon = dna_in.rayon
  }

}