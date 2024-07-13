package spark

import org.apache.spark.sql.SparkSession

trait SparkSessionProvider {
  def appName: String
  def master: String = "local[*]"
  def logLevel: String = "WARN"

  val spark: SparkSession = SparkSession.builder
    .appName(appName)
    .master(master)
    .getOrCreate()

  spark.sparkContext.setLogLevel(logLevel)
}