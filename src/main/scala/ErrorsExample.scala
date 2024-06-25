object ErrorsExample {

  // Ejemplo 1: Manejo de errores con try-catch-finally
  // En Scala, try, catch y finally son bloques utilizados para manejar excepciones,
  // proporcionando una forma estructurada de gestionar errores en tu código
  // try maneja el código que podría lanzar errores
  // catch maneja esos errores específicamente
  // finally ejecuta código sin importar si hay errores o no.
  def division1(a: Int, b: Int): Int = {
    try {
      a / b
    } catch {
      case ex: Exception =>
        println(s"Error: División por cero - $ex")
        0
    } finally {
      println("Operación de división finalizada.")
    }
  }

  // Ejemplo 2: Manejo de errores con Option
  // Permite retornar un valor opcional luego de una división,
  // evitando errores de división por cero y manejando el resultado sin excepciones.
  def division2(a: Int, b: Int): Option[Int] = if (b != 0) Some(a / b) else None

  // Ejemplo 3: Manejo de errores con Either
  // Permite manejar resultados que pueden ser exitosos (Right(valor)) o contener un error (Left(error)),
  // proporcionando una forma de manejar distintos escenarios de manera controlada.
  def division3(a: Int, b: Int): Either[String, Int] = if (b != 0) Right(a / b) else Left("Error: División por cero")

  def main(args: Array[String]): Unit = {
    // Ejemplo 1: Uso de try-catch-finally
    println("Ejemplo 1: Uso de try-catch-finally")
    println("División de 10 por 2:")
    val resultado1 = division1(10, 2)
    println(s"Resultado: $resultado1")

    println("\nDivisión de 10 por 0:")
    val resultado2 = division1(10, 0)
    println(s"Resultado: $resultado2")

    // Ejemplo 2: Uso de Option
    println("\nEjemplo 2: Uso de Option")
    println("División de 10 por 2:")
    val resultado3 = division2(10, 2)
    resultado3 match {
      case Some(value) => println(s"Resultado: $value")
      case None => println("Error: División por cero")
    }

    println("\nDivisión de 10 por 0:")
    val resultado4 = division2(10, 0)
    resultado3 match {
      case Some(value) => println(s"Resultado: $value")
      case None => println("Error: División por cero")
    }

    // Ejemplo 3: Uso de Either
    println("\nEjemplo 3: Uso de Either")
    println("División de 10 por 2:")
    val resultado5 = division3(10, 2)
    resultado5 match {
      case Right(value) => println(s"Resultado: $value")
      case Left(error) => println(error)
    }

    println("\nDivisión de 10 por 0:")
    val resultado6 = division3(10, 0)
    resultado6 match {
      case Right(value) => println(s"Resultado: $value")
      case Left(error) => println(error)
    }
  }
}
