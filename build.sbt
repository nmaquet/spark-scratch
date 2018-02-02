name := "Spark Scratch"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.2.1"

libraryDependencies += "org.apache.spark" %% "spark-yarn" % "2.2.1"

libraryDependencies += "org.apache.hadoop" % "hadoop-aws" % "2.7.3"

enablePlugins(SparkSettings)
