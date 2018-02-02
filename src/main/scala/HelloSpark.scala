import org.apache.spark.sql.SparkSession

object HelloSpark {
  val logFile = "s3://emr-meeting-test/README.md"
  def main(args: Array[String]) {
    val spark = SparkSession.builder.appName("HelloSpark").getOrCreate()
    val logData = spark.read.textFile(logFile).cache()
    val numAs = logData.filter(_.contains("a")).count()
    val numBs = logData.filter(_.contains("b")).count()
    println(s"Lines with a: $numAs, Lines with b: $numBs")
    spark.stop()
  }
}
