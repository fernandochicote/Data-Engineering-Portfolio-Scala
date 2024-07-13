package spark

import org.apache.spark.sql.functions._

object SparkRollUpExample extends App with SparkSessionProvider {

  override def appName: String = "SparkRollUpExample"
  override def logLevel: String = "FATAL"

  val df = spark.read.json(Config.jsonPath)

  df.show()

  df.printSchema()

  df.createOrReplaceTempView("student_grades")

  // Calculo de ambas formas de medias por asignatura y alumno dejando la nota individual de cada alumno por asignatura

  // Grouping sets
  val queryGs = """
                  SELECT student_name, subject ,AVG(grade) AS average_grade
                  FROM student_grades
                  GROUP BY GROUPING SETS ((student_name), (subject), (student_name, subject))
                  """
  val dfGs = spark.sql(queryGs)
  dfGs.show()

  // Rollup
  val dfRoll = df.rollup("subject", "student_name")
    .agg(avg("grade").alias("average_grade"))
  dfRoll.show()

  // Detiene SparkSession
  spark.stop()
}


