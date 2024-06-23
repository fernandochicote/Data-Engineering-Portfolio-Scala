object Main {
  def main(args: Array[String]): Unit = {
    println("Hello world!")
  }

  def contarPalabras(texto: String): Map[String, Int] = {
    texto.split("\\s+")
      .map(_.toLowerCase)
      .groupBy(identity)
      .view.mapValues(_.length)
      .toMap
  }

  // Ejemplo de ejecución
  val textoEjemplo = "Este es un ejemplo de texto. Este texto contiene palabras repetidas."
  val resultadoConteo = contarPalabras(textoEjemplo)
  println("Conteo de palabras:")
  resultadoConteo.foreach { case (palabra, conteo) =>
    println(s"$palabra: $conteo")
  }
}