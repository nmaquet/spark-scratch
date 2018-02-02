import sbt._
import sbt.Keys._

object SparkSettings extends AutoPlugin {

  def mustGetEnv(variable: String) : String = {
    sys.env.get(variable) match {
      case Some(s) => s
      case None => throw new RuntimeException(s"Could not find environment variable: ${variable}")
    }
  }

  val sparkMaster = settingKey[String]("The Spark master, e.g. \"local[4]\" or \"yarn\"")
  val awsAccessKeyId = settingKey[String]("AWS access key ID")
  val awsSecretAccessKey = settingKey[String]("AWS secret access key")
  val sparkLogLevel = settingKey[String]("Spark log level")

  override lazy val projectSettings = Seq(
    sparkMaster := "yarn",
    awsAccessKeyId := mustGetEnv("SBT_AWS_ACCESS_KEY_ID"),
    awsSecretAccessKey := mustGetEnv("SBT_AWS_SECRET_ACCESS_KEY"),
    sparkLogLevel := "WARN",
    initialCommands in console := {
      s"""
      import org.apache.spark.sql.SparkSession
      val spark = SparkSession.builder
        .master("${sparkMaster.value}")
        .config("spark.hadoop.fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")
        .config("spark.hadoop.fs.s3a.access.key", "${awsAccessKeyId.value}")
        .config("spark.hadoop.fs.s3a.secret.key", "${awsSecretAccessKey.value}")
        .appName("SbtSparkShell").getOrCreate()
      val sc = spark.sparkContext
      sc.setLogLevel("${sparkLogLevel.value}")
      """
    },
    cleanupCommands in console := {
      s"""
      spark.stop()
      """
    }
  )
}
