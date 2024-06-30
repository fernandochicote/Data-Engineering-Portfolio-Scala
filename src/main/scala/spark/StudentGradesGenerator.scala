package spark

import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.types.{StructType, StructField, StringType, IntegerType, DoubleType}
import org.apache.spark.sql.Row
import scala.util.Random

object StudentGradesGenerator extends App {

  // Inicializa SparkSession
  val spark = SparkSession.builder
    .appName("StudentGradesGenerator")
    .master("local[*]")
    .getOrCreate()

  // Define el esquema del DataFrame
  val schema = StructType(List(
    StructField("student_id", IntegerType, nullable = false),
    StructField("student_name", StringType, nullable = false),
    StructField("subject", StringType, nullable = false),
    StructField("grade", DoubleType, nullable = false),
    StructField("grade_description", StringType, nullable = false) // Añadido grade_description
  ))

  // Lista de nombres de alumnos
  val studentNames = List("Fernando", "Mario", "Rafael", "Beatriz", "Soufian", "Daniel", "Guillermo", "Alberto")
  val subjects = List("Bases de datos", "SQL", "Python", "Spark", "Scala", "NoSQL", "Hadoop")

  // Función para obtener la descripción de la nota
  def getGradeDescription(grade: Double): String = {
    if (grade < 5) "Suspenso"
    else if (grade < 7) "Aprobado"
    else if (grade < 9) "Notable"
    else "Sobresaliente"
  }

  // Genera datos aleatorios
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

  // Crea el DataFrame
  val rdd = spark.sparkContext.parallelize(data)
  val df = spark.createDataFrame(rdd, schema)

  // Escribe el DataFrame en formato JSON
  val outputPath = "data/datagen/student_grades"
  df.write.json(outputPath)

  // Lee el DataFrame desde el fichero JSON
  val readDf = spark.read.json(outputPath)

  // Muestra el DataFrame leído
  readDf.show()

  // Detiene SparkSession
  spark.stop()
}
