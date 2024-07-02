package spark

import org.apache.spark.sql.{SparkSession, DataFrame}

object PartitioningBucketingExample extends App {

  val spark: SparkSession = SparkSession.builder
    .appName("PartitioningBucketingExample")
    .master("local[*]")
    .getOrCreate()

  spark.sparkContext.setLogLevel("FATAL")


  // Leer la tabla JSON y aplicar particionado y bucketing
  try {
    // Ruta del archivo JSON
    val jsonTablePath = Config.jsonPath

    // Leer la tabla JSON
    val df = spark.read.json(jsonTablePath)

    // Mostrar los primeros registros del DataFrame leído
    println("Primeros registros del DataFrame JSON:")
    df.show()

    // Ejemplo de particionado y bucketing
    val partitionColumn = "student_id"
    val numBuckets = 4

    // Aplicar particionado
    df.write
      .partitionBy(partitionColumn)
      .mode("overwrite") // Modo de escritura: sobrescribir si ya existe
      .parquet(Config.partitionPath)

    println(s"Tabla particionada por '$partitionColumn' guardada en ${Config.partitionPath}.")

    // Aplicar bucketing
    df.write
      .bucketBy(numBuckets, partitionColumn)
      .sortBy(partitionColumn)
      .mode("overwrite") // Modo de escritura: sobrescribir si ya existe
      .saveAsTable("tabla_bucketizada")

    println(s"Tabla bucketizada por '$partitionColumn' en $numBuckets buckets guardada.")
  } finally {
    // Detener la sesión Spark al finalizar
    spark.stop()
  }
}

