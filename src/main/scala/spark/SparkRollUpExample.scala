package spark

import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.functions._

object SparkRollUpExample extends App {

  val spark = SparkSession.builder
    .appName("SparkWindowExample")
    .master("local[*]")
    .getOrCreate()

  spark.sparkContext.setLogLevel("FATAL")

  val df = spark.read.json(Config.jsonPath)

  df.show()

  df.printSchema()

  df.createOrReplaceTempView("student_grades")

  val query_gs = """
                  SELECT student_name, subject ,AVG(grade) AS average_grade
                  FROM student_grades
                  GROUP BY GROUPING SETS ((student_name), (subject), (student_name, subject))
                  """
  val df_gs = spark.sql(query_gs)
  df_gs.show()

  val df_roll = df.rollup("subject", "student_name")
    .agg(avg("grade").alias("average_grade"))
  df_roll.show()

  // Detiene SparkSession
  spark.stop()
}


