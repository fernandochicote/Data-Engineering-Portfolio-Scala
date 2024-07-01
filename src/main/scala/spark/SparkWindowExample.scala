package spark

import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

object SparkWindowExample extends App {

  // Sesion de spark
  val spark = SparkSession.builder
    .appName("SparkWindowExample")
    .master("local[*]")
    .getOrCreate()

  spark.sparkContext.setLogLevel("FATAL")

  // Mismo fichero JSON
  val df = spark.read.json(Config.jsonPath)
  df.show()
  df.printSchema()

  // Definicion de las ventanas
  val window_media = Window.partitionBy("student_id")
  val window_rank = Window.partitionBy("student_id").orderBy(desc("grade"))

  // Nota media del estudiante
  val df_media = df.withColumn("nota_media", avg("grade").over(window_media))
  df_media.show()

  // Ranking de notas para cada estudiante
  val df_rank = df.withColumn("rank", rank().over(window_rank))
  df_rank.show()

  // Detiene SparkSession
  spark.stop()
}

