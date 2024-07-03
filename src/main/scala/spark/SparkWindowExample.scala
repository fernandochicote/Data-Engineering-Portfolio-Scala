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
  val colPartition = "student_id"
  val windowMedia = Window.partitionBy(colPartition)
  val windowRank = Window.partitionBy(colPartition).orderBy(desc("grade"))

  // Nota media del estudiante
  val dfMedia = df.withColumn("nota_media", avg("grade").over(windowMedia))
  dfMedia.show()

  // Ranking de notas para cada estudiante
  val dfRank = df.withColumn("rank", rank().over(windowRank))
  dfRank.show()

  // Detiene SparkSession
  spark.stop()
}

