package spark

import org.scalatest.funsuite.AnyFunSuiteLike

class StudentGradesGeneratorTest extends AnyFunSuiteLike {

  // Crear tabla para test
  val datosDePrueba: Map[Double, String] = Map(
    0.toDouble -> "Suspenso",
    5.toDouble -> "Aprobado",
    7.toDouble -> "Notable",
    9.toDouble -> "Sobresaliente",
    3.toDouble -> "Suspenso",
    6.toDouble -> "Aprobado",
    8.toDouble -> "Notable",
    10.toDouble -> "Matrícula de Honor",
    1.toDouble -> "Suspenso",
    2.toDouble -> "Suspenso",
    4.toDouble -> "Suspenso",
    6.5.toDouble -> "Aprobado",
    7.5.toDouble -> "Notable",
    8.5.toDouble -> "Notable",
    // Qué se supone que debería pasar si la nota es negativa o mayor de 10?
    -1.toDouble -> "Suspenso",
    11.toDouble -> "Nota no válida",
    Double.MaxValue -> "Nota no válida",
    Double.MinValue -> "Suspenso",
  )

  test("Test StudentGradesGenerator") {

    datosDePrueba.foreach { case (actual, esperado) =>
      println(s"Test con $actual")
      val calculado = StudentGradesFunctions.getGradeDescriptionV2(actual)
      assert(calculado == esperado)
    }


  }

}
