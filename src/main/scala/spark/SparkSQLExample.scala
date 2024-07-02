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
  val df_sup7 = df.filter(col("grade") > 7)
  df_sup7.show()

  // Ejemplo 2: Calcular la nota media por asignatura
  val df_medias = df.groupBy("subject")
    .agg(avg("grade").alias("average_grade"))
  df_medias.show()

  // Ejemplo 3: Añadir una nueva columna con puntos extra
  val df_extra = df.withColumn("grade_with_extra", col("grade") + 0.5)
  df_extra.show()

  // Ejemplo 4: Contar el número de estudiantes con cada nota
  val df_num_grades = df.groupBy("grade_description")
    .agg(count("student_id").alias("count"))
  df_num_grades.show()

  spark.stop()
}

