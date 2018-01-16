import org.apache.spark.sql.SparkSession

object HelloSpark {
  val SPARK_HOME = "/Users/nicolasm/Tools/spark-2.2.1-bin-hadoop2.7/"

  def main(args: Array[String]) {
    val logFile = SPARK_HOME + "README.md"
    val spark = SparkSession.builder.appName("HelloSpark").getOrCreate()
    val logData = spark.read.textFile(logFile).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println(s"Lines with a: $numAs, Lines with b: $numBs")
    spark.stop()
  }
}
