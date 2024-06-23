object DataEngineeringPortfolio {

  // Función para contar palabras en un texto
  def contarPalabras(texto: String): Map[String, Int] = {
    texto.split("\\s+")
      .map(_.toLowerCase)
      .groupBy(identity)
      .view.mapValues(_.length)
      .toMap
  }

  // Función para calcular la serie de Fibonacci
  def fibonacci(n: Int): List[Int] = {
    def fibHelper(n: Int, a: Int, b: Int): List[Int] = n match {
      case 0 => Nil
      case 1 => List(a)
      case _ => a :: fibHelper(n - 1, b, a + b)
    }
    fibHelper(n, 0, 1)
  }

  // Función para filtrar elementos únicos en una lista
  def elementosUnicos[A](lista: List[A]): List[A] = {
    lista.groupBy(identity).collect { case (elem, List(_)) => elem }.toList
  }

  // Función para calcular el promedio de una lista de números
  def promedio(nums: List[Double]): Double = {
    if (nums.isEmpty) 0.0
    else nums.sum / nums.length
  }

  // Ejemplos de ejecución
  def main(args: Array[String]): Unit = {
    // Ejemplo 1: contarPalabras
    val textoEjemplo = "Este es un ejemplo de texto. Este texto contiene palabras repetidas."
    val resultadoConteo = contarPalabras(textoEjemplo)
    println("Conteo de palabras:")
    resultadoConteo.foreach { case (palabra, conteo) =>
      println(s"$palabra: $conteo")
    }

    // Ejemplo 2: fibonacci
    val n = 10
    val serieFibonacci = fibonacci(n)
    println(s"Los primeros $n números de Fibonacci son: $serieFibonacci")

    // Ejemplo 3: elementosUnicos
    val listaEjemplo = List(1, 2, 3, 2, 4, 1, 5, 6, 4)
    val elementosUnicosLista = elementosUnicos(listaEjemplo)
    println(s"Elementos únicos en la lista: $elementosUnicosLista")

    // Ejemplo 4: promedio
    val listaNumeros = List(1.5, 2.5, 3.5, 4.5, 5.5)
    val promedioLista = promedio(listaNumeros)
    println(s"El promedio de la lista de números es: $promedioLista")

  }
}