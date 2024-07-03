package spark

import org.apache.spark.sql.{Dataset, SparkSession}
import org.apache.spark.sql.functions._
import spark.Domain.{Car, factorConversionPrecioActual}

object Domain {
  // Case class para reprsentar coches
  // Añadido currentPrice como Option porque es algo que calcularemos más adelante
  case class Car(make: String, model: String, year: Int, price: Double, currentPrice: Option[Double] = None)
  private val factorConversionPrecioActual: Double = 0.85
  val precioActual: Car => Double = _.price * factorConversionPrecioActual

}

object Utils {
  // Function to display dataset content
  def showDatasetContent[T](ds: Dataset[T], message: String): Unit = {
    println(message)
    ds.show()
  }

  def createSparkSession(appName: String): SparkSession = {
    SparkSession.builder
      .appName(appName)
      .master("local[*]")
      .getOrCreate()
  }

}

object CarDatasetExample extends App{

  // Inicializar una SparkSession
  implicit val spark: SparkSession = Utils.createSparkSession("CarDatasetExample")

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
  // En Scala se usa el nombrado con cammelCase
  val dsCar: Dataset[Car] = spark.createDataset(datos)

  // Mostrar el contenido del Dataset de coches
  Utils.showDatasetContent(dsCar, "Contenido del Dataset de coches:")

  // Operaciones con datasets
  // Filtrar coches fabricados después de 2018
  println("Coches fabricados después de 2018:")

  val posterioresA2018: Car => Boolean = _.year > 2018
  dsCar.filter(posterioresA2018).show()

  // Precio promedio de los coches
  val averagePrice = dsCar.agg(avg("price")).as[Double].first()
  println(s"Precio promedio de los coches: $averagePrice")

  // Añado ejemplo de map para mostrar que no es necesario withColumn
  // Añadir una nueva columna con el precio del coche en euros
  val dsConPrecioActual: Dataset[Car] = dsCar.map(car => car.copy(currentPrice = Some(Domain.precioActual(car))))
  Utils.showDatasetContent(dsConPrecioActual, "Dataset de coches con precio actual:")
  // Ordenar el Dataset por precio de forma descendente
  val dsOrdenado: Dataset[Car] = dsConPrecioActual.orderBy($"price".desc)
  Utils.showDatasetContent(dsOrdenado, "Dataset de coches ordenado por precio:")

  spark.stop()
}

