package spark

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._

object SparkSQLExample extends App {

  // Sesion de Spark
  val spark = SparkSession.builder
    .appName("SparkSQLExample")
    .master("local[*]")
    .getOrCreate()

  spark.sparkContext.setLogLevel("FATAL")

  // Lectura del DF desde un fichero JSON
  val df = spark.read.json(Config.jsonPath)
  df.show()

  // Mostrar el esquema del DataFrame
  df.printSchema()

  // Ejemplo 1: Filtrar alumnos que han obtenido una nota superior a 7
  val dfSup7 = df.filter(col("grade") > 7)
  dfSup7.show()

  // Ejemplo 2: Calcular la nota media por asignatura
  val dfMedias = df.groupBy("subject")
    .agg(avg("grade").alias("average_grade"))
  dfMedias.show()

  // Ejemplo 3: Añadir una nueva columna con puntos extra
  val dfExtra = df.withColumn("grade_with_extra", col("grade") + 0.5)
  dfExtra.show()

  // Ejemplo 4: Contar el número de estudiantes con cada nota
  val dfNumGrades = df.groupBy("grade_description")
    .agg(count("student_id").alias("count"))
  dfNumGrades.show()

  spark.stop()
}

