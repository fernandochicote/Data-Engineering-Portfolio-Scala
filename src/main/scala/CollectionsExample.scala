//Las colecciones en Scala sirven para almacenar elementos de datos de manera estructurada
//y permiten operaciones funcionales avanzadas

object CollectionsExample {

  def main(args: Array[String]): Unit = {
    // Lista de enteros
    val numeros = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(s"Lista original: $numeros")

    // Pares de la lista numeros
    val pares = numeros.filter(_ % 2 == 0)
    println(s"Números pares: $pares")

    // Funcion map para obtener los cuadrados de cada uno de los elementos de la lista numeros
    // La función map en Scala se utiliza para transformar los elementos de una colección aplicando una función
    // a cada uno de ellos y devolviendo una nueva colección con los resultados de las transformaciones.
    val cuadrados = numeros.map(n => n * n)
    println(s"Cuadrados: $cuadrados")

    // Sumar los numeros de la lista
    val suma = numeros.sum
    println(s"Suma de los elementos: $suma")

    // Encontrar el número máximo
    val maximo = numeros.max
    println(s"Número máximo: $maximo")

    // Encontrar el número mínimo
    val minimo = numeros.min
    println(s"Número mínimo: $minimo")

    // Combinar dos listas
    val otraLista = List(11, 12, 13)
    val combinada = numeros ++ otraLista
    println(s"Lista combinada: $combinada")

    // Crear un conjunto de elementos únicos
    val numerosRepetidos = List(1, 1, 2, 2, 3, 3, 4, 4, 5, 5)
    val conjunto = numerosRepetidos.toSet
    println(s"Conjunto (elementos únicos): $conjunto")

    // Funcion toMap para crear un diccionario con clave - valor
    // El método toMap se utiliza para convertir una colección de pares (clave, valor) en un mapa (Map).
    // Esto es especialmente útil cuando se tiene una colección de tuplas o pares
    // y se desea transformarlos en un diccionario.
    val mapaCuadrados = numeros.map(n => n -> (n * n)).toMap
    println(s"Mapa de número a su cuadrado: $mapaCuadrados")
  }
}

