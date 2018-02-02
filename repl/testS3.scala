val logFile = "s3a://emr-meeting-test/nicolas/Readme.md"
val logData = spark.read.textFile(logFile).cache()
