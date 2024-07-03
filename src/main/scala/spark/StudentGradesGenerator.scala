package spark

import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.types.{StructType, StructField, StringType, IntegerType, DoubleType}
import org.apache.spark.sql.Row
import scala.util.Random

object StudentGradesFunctions {
  // Una implementación alternativa de la función getGradeDescription más funcional
  def getGradeDescriptionV2(grade: Double): String = {
    grade match {
      case g if g < 5 => "Suspenso"
      case g if g < 7 => "Aprobado"
      case g if g < 9 => "Notable"
      case g if g < 10 => "Sobresaliente"
      case g if g == 10 => "Matrícula de Honor"
      case _ => "Nota no válida"
    }
  }
}

object StudentGradesGenerator extends App {

  // Sesion de Spark
  val spark = SparkSession.builder
    .appName("StudentGradesGenerator")
    .master("local[*]")
    .getOrCreate()

  spark.sparkContext.setLogLevel("FATAL")


  // Esquema del dataframe
  val schema = StructType(List(
    StructField("student_id", IntegerType, nullable = false),
    StructField("student_name", StringType, nullable = false),
    StructField("subject", StringType, nullable = false),
    StructField("grade", DoubleType, nullable = false),
    StructField("grade_description", StringType, nullable = false) // Añadido grade_description
  ))

  // Lista de alumnos y asignaturas
  val studentNames = List("Fernando", "Mario", "Rafael", "Beatriz", "Soufian", "Daniel", "Guillermo", "Alberto")
  val subjects = List("Bases de datos", "SQL", "Python", "Spark", "Scala", "NoSQL", "Hadoop")

  // Función para obtener la descripción de la nota
  def getGradeDescription(grade: Double): String = {
    if (grade < 5) "Suspenso"
    else if (grade < 7) "Aprobado"
    else if (grade < 9) "Notable"
    else "Sobresaliente"
  }


  // Genera datos aleatorios y la nota
  val random = new Random()
  val data = for {
    studentId <- 1 to studentNames.length
    studentName = studentNames(studentId - 1)
    subject <- subjects
  } yield {
    val grade = BigDecimal(1 + random.nextDouble() * 9).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble // Notas entre 1 y 10, redondeadas a 2 decimales
    val gradeDescription = getGradeDescription(grade)
    Row(studentId, studentName, subject, grade, gradeDescription)
  }

  // Creacion del dataframe
  val rdd = spark.sparkContext.parallelize(data)
  val df = spark.createDataFrame(rdd, schema)

  // Guardado en formato JSON
  df.write.json(Config.jsonPath)

  // Lectura del fichero
  val readDf = spark.read.json(Config.jsonPath)

  readDf.show()

  spark.stop()
}
