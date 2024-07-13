package spark

import org.apache.parquet.format.IntType
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{DoubleType, IntegerType, StringType, StructType}
import org.apache.spark.sql.{Row, SparkSession}
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach}
import org.scalatest.funsuite.AnyFunSuiteLike

class DataFrameProcessorTest extends AnyFunSuiteLike with BeforeAndAfterAll {

  val spark: SparkSession = SparkSession.builder()
    .master("local[2]")
    .config("spark.sql.shuffle.partitions", "2")
    // Disabel the UI
    .config("spark.ui.enabled", "false")
    // Disable compression
    .config("spark.sql.compression.codec", "uncompressed")
    .config("spark.sql.adaptive.enabled", "false")
    .appName("DataFrameProcessorTest")
    .getOrCreate()

  spark.sparkContext.setLogLevel("ERROR")

  // Un DataFrameProcessor es una clase que contiene funciones que procesan DataFrames
  val dfProcessor: DataFrameProcessor = new DataFrameProcessor(spark)


  test("filter grades above 7") {
    val data = Seq(
      Row("maths", 7.5),
      Row("english", 6.0),
      Row("physics", 8.2)
    )
    val schema = new StructType().add("subject", StringType).add("grade", DoubleType)
    val df = spark.createDataFrame(spark.sparkContext.parallelize(data), schema)

    val result = dfProcessor.filterGradesAbove7(df)
    assert(result.count() == 2)
    assert(result.filter(col("grade") < 7).count() == 0)
  }

  test("calculate average grade by subject") {
    val data = Seq(
      Row("maths", 7.5),
      Row("maths", 6.0),
      Row("english", 6.0),
      Row("english", 8.0),
      Row("physics", 8.2)
    )
    val schema = new StructType().add("subject", StringType).add("grade", DoubleType)
    val df = spark.createDataFrame(spark.sparkContext.parallelize(data), schema)

    val result = dfProcessor.calculateAverageGradeBySubject(df)
    assert(result.count() == 3)
    assert(result.filter(col("subject") === "maths").first().getDouble(1) == 6.75)
    assert(result.filter(col("subject") === "english").first().getDouble(1) == 7.0)
    assert(result.filter(col("subject") === "physics").first().getDouble(1) == 8.2)
  }

  test("add extra grades") {
    val data = Seq(
      Row("maths", 7.5),
      Row("english", 6.0),
      Row("physics", 8.2)
    )
    val schema = new StructType().add("subject", StringType).add("grade", DoubleType)
    val df = spark.createDataFrame(spark.sparkContext.parallelize(data), schema)

    val result = dfProcessor.addExtraGrades(df)
    result.show(false)
    assert(result.count() == 3)
    assert(result.filter(col("grade_with_extra") === 8.0).count() == 1)
    assert(result.filter(col("grade_with_extra") === 6.5).count() == 1)
    assert(result.filter(col("grade_with_extra") === 7.0).count() == 0)

  }

  test("count students per grade description") {
    import spark.implicits._

    val data = Seq(
      Row(1, "maths", 7.5, "good"),
      Row(2, "english", 6.0, "bad"),
      Row(3, "physics", 8.2, "good"),
      Row(4, "maths", 6.0, "bad"),
      Row(5, "english", 8.0, "good"),
      Row(6, "physics", 8.2, "good")
    )
    val schema = new StructType()
      .add("student_id", IntegerType)
      .add("subject", StringType)
      .add("grade", DoubleType)
      .add("grade_description", StringType)
    val df = spark.createDataFrame(spark.sparkContext.parallelize(data), schema)

    println(" -- Dataframe: df")
    df.show(false)

    val result = dfProcessor.countStudentsPerGradeDescription(df)
    println(" -- Dataframe: result")
    result.show(false)
    assert(result.count() == 2)
    assert(result.filter(col("grade_description") === "good").first().getLong(1) == 4)
    assert(result.filter(col("grade_description") === "bad").first().getLong(1) == 2)
  }

  override def afterAll(): Unit = {
    if (spark != null) spark.close()
    super.afterAll()
  }
}