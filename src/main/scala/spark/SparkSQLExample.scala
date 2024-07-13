package spark

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._

class DataFrameProcessor(spark: SparkSession) {
  def filterGradesAbove7(df: DataFrame): DataFrame = {
    df.filter(col("grade") > 7)
  }

  def calculateAverageGradeBySubject(df: DataFrame): DataFrame = {
    df.groupBy("subject")
      .agg(avg("grade").alias("average_grade"))
  }

  def addExtraGrades(df: DataFrame): DataFrame = {
    df.withColumn("grade_with_extra", col("grade") + 0.5)
  }

  def countStudentsPerGradeDescription(df: DataFrame): DataFrame = {
    df.groupBy("grade_description")
      .agg(count("student_id").alias("count"))
  }
}

object SparkSQLExample extends App with SparkSessionProvider {

  override def appName: String = "SparkSQLExample"
  override def logLevel: String = "FATAL"

    // Reading the DF from a JSON file
  val df = spark.read.json(Config.jsonPath)
  df.show()

  // Show the DataFrame schema
  df.printSchema()

  // Processor instance
  val processor = new DataFrameProcessor(spark)

  // Example 1: Filter students who have obtained a grade above 7
  val dfSup7 = processor.filterGradesAbove7(df)
  dfSup7.show()

  // Example 2: Calculate the average grade by subject
  val dfMedias = processor.calculateAverageGradeBySubject(df)
  dfMedias.show()

  // Example 3: Add a new column with extra points
  val dfExtra = processor.addExtraGrades(df)
  dfExtra.show()

  // Example 4: Count the number of students with each grade description
  val dfNumGrades = processor.countStudentsPerGradeDescription(df)
  dfNumGrades.show()

  spark.stop()
}