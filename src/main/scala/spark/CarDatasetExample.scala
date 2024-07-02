package spark

import org.apache.spark.sql.{SparkSession, Dataset}
import org.apache.spark.sql.functions._

// Case class para reprsentar coches
case class Car(make: String, model: String, year: Int, price: Double)

object CarDatasetExample extends App{

  // Inicializar una SparkSession
  val spark: SparkSession = SparkSession.builder
    .appName("CarDatasetExample")
    .master("local[*]")
    .getOrCreate()

  spark.sparkContext.setLogLevel("FATAL")

  import spark.implicits._

  // Secuencia de objetos de tipo Car
  val datos = Seq(
    Car("Seat", "Leon", 2018, 15000.0),
    Car("Dacia", "Sandero", 2017, 10000.0),
    Car("Ford", "Focus", 2019, 22000.0),
    Car("Audi", "A3", 2016, 17000.0),
    Car("BMW", "X5", 2020, 50000.0)
  )

  // Crear un Dataset a partir de la secuencia de datos de coches
  val ds_car: Dataset[Car] = spark.createDataset(datos)

  // Mostrar el contenido del Dataset de coches
  println("Contenido del Dataset de coches:")
  ds_car.show()

  // Operaciones con datasets
  // Filtrar coches fabricados después de 2018
  println("Coches fabricados después de 2018:")
  ds_car.filter(_.year > 2018).show()

  // Precio promedio de los coches
  val averagePrice = ds_car.agg(avg("price")).as[Double].first()
  println(s"Precio promedio de los coches: $averagePrice")

  spark.stop()
}

