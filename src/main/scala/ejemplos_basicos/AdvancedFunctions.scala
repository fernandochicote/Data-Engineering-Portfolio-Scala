package ejemplos_basicos

object AdvancedFunctions {

  // Funciones aritméticas básicas usando HOF y currying
  def operar(a: Int, b: Int)(operacion: (Int, Int) => Int): Int = operacion(a, b)

  val sumar: (Int, Int) => Int = _ + _
  val restar: (Int, Int) => Int = _ - _
  val multiplicar: (Int, Int) => Int = _ * _

  def main(args: Array[String]): Unit = {
    println(s"Sumar 5 + 3: ${operar(5, 3)(sumar)}")
    println(s"Restar 10 - 4: ${operar(10, 4)(restar)}")
    println(s"Multiplicar 6 * 7: ${operar(6, 7)(multiplicar)}")
  }
}

