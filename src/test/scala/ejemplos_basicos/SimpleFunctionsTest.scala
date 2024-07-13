package ejemplos_basicos

import org.scalatest.funsuite.AnyFunSuite

class SimpleFunctionsTest extends AnyFunSuite {

  test("sumar") {
    assert(SimpleFunctions.sumar(5, 3) == 8)
  }

  test("restar") {
    assert(SimpleFunctions.restar(10, 4) == 6)
  }

  test("multiplicar") {
    assert(SimpleFunctions.multiplicar(6, 7) == 42)
  }

  test("dividir") {
    assert(SimpleFunctions.dividir(20, 4).contains(5.0))
    assert(SimpleFunctions.dividir(20, 0).isEmpty) // Test division by zero
  }

  test("invertirCadena") {
    assert(SimpleFunctions.invertirCadena("Hola Mundo") == "odnuM aloH")
  }

  test("contarPalabras") {
    assert(SimpleFunctions.contarPalabras("Scala es genial") == 3)
  }

  test("mayorEnLista") {
    assert(SimpleFunctions.mayorEnLista(List(1, 3, 5, 2)).contains(5))
    assert(SimpleFunctions.mayorEnLista(List()).isEmpty) // Test empty list
  }

  test("menorEnLista") {
    assert(SimpleFunctions.menorEnLista(List(1, 3, 5, 2)).contains(1))
    assert(SimpleFunctions.menorEnLista(List()).isEmpty) // Test empty list
  }

  test("sumarLista") {
    assert(SimpleFunctions.sumarLista(List(1, 2, 3, 4)) == 10)
  }

  test("multiplicarLista") {
    assert(SimpleFunctions.multiplicarLista(List(1, 2, 3, 4)) == 24)
  }

  test("contiene") {
    assert(SimpleFunctions.contiene(List(1, 2, 3), 2))
    assert(!SimpleFunctions.contiene(List(1, 2, 3), 4)) // Test value not in list
  }
}