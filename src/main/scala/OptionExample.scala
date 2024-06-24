// La clase llamada Option que se usa para manejar valores que pueden ser opcionales
// Un Option puede ser Some(valor) si el valor está presente, o None si el valor está ausente.

object OptionExample {
  def main(args: Array[String]): Unit = {
    val someValue: Option[Int] = Some(42)
    val noValue: Option[Int] = None

    // Imprimir los valores de Option
    println(someValue) // Output: Some(42)
    println(noValue)   // Output: None

    // Uso de getOrElse para manejar los valores
    val value1 = someValue.getOrElse(0)
    val value2 = noValue.getOrElse(0)

    println(value1) // Output: 42
    println(value2) // Output: 0
  }
}
