package spark

import org.apache.spark.sql.SparkSession

object SparkSessionExample extends App{
  // Crear la sesión de Spark
  val spark = SparkSession.builder
    .appName("Spark Example")
    .master("local[*]")  // Usar todos los núcleos locales
    .getOrCreate()

  spark.sparkContext.setLogLevel("FATAL")

  // Crear un DataFrame a partir de una secuencia
  val data = Seq(
    ("Alice", 34),
    ("Bob", 45),
    ("Catherine", 29)
  )

  // Definir el esquema del DataFrame
  import spark.implicits._
  val df = data.toDF("Name", "Age")

  // Mostrar el DataFrame
  df.show()

  // Parar la sesión de Spark
  spark.stop()

}
