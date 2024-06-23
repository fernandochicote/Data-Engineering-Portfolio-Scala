object SimpleFunctions {

  // Funciones aritméticas básicas
  def sumar(a: Int, b: Int): Int = {
    a + b
  }

  def restar(a: Int, b: Int): Int = {
    a - b
  }

  def multiplicar(a: Int, b: Int): Int = {
    a * b
  }

  def dividir(a: Int, b: Int): Option[Double] = {
    if (b != 0) Some(a.toDouble / b)
    else None
  }

  // Función para invertir una cadena
  def invertirCadena(cadena: String): String = {
    cadena.reverse
  }

  // Función para contar palabras en una cadena
  def contarPalabras(cadena: String): Int = {
    cadena.split("\\s+").length
  }

  // Función para encontrar el mayor número en una lista
  def mayorEnLista(lista: List[Int]): Option[Int] = {
    if (lista.nonEmpty) Some(lista.max)
    else None
  }

  // Función para encontrar el menor número en una lista
  def menorEnLista(lista: List[Int]): Option[Int] = {
    if (lista.nonEmpty) Some(lista.min)
    else None
  }

  // Función para sumar todos los elementos de una lista
  def sumarLista(lista: List[Int]): Int = {
    lista.sum
  }

  // Función para multiplicar todos los elementos de una lista
  def multiplicarLista(lista: List[Int]): Int = {
    lista.product
  }

  // Función para verificar si una lista contiene un valor
  def contiene(lista: List[Int], valor: Int): Boolean = {
    lista.contains(valor)
  }

  // Ejecución de pruebas simples para mostrar la funcionalidad
  def main(args: Array[String]): Unit = {
    println(s"Sumar 5 + 3: ${sumar(5, 3)}")
    println(s"Restar 10 - 4: ${restar(10, 4)}")
    println(s"Multiplicar 6 * 7: ${multiplicar(6, 7)}")
    println(s"Dividir 20 / 4: ${dividir(20, 4).getOrElse("División por cero")}")
    println(s"Invertir 'Hola Mundo': ${invertirCadena("Hola Mundo")}")
    println(s"Contar palabras en 'Scala es genial': ${contarPalabras("Scala es genial")}")
    println(s"Mayor en la lista List(1, 3, 5, 2): ${mayorEnLista(List(1, 3, 5, 2)).getOrElse("Lista vacía")}")
    println(s"Menor en la lista List(1, 3, 5, 2): ${menorEnLista(List(1, 3, 5, 2)).getOrElse("Lista vacía")}")
    println(s"Sumar todos los elementos de la lista List(1, 2, 3, 4): ${sumarLista(List(1, 2, 3, 4))}")
    println(s"Multiplicar todos los elementos de la lista List(1, 2, 3, 4): ${multiplicarLista(List(1, 2, 3, 4))}")
    println(s"¿La lista List(1, 2, 3) contiene el valor 2?: ${contiene(List(1, 2, 3), 2)}")
  }
}
