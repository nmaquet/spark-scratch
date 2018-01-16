#!/bin/bash

SPARK_HOME=/Users/nicolasm/Tools/spark-2.2.1-bin-hadoop2.7
PROJECT_DIR=/Users/nicolasm/Workspace/spark-scratch

$SPARK_HOME/bin/spark-submit \
 --class "HelloSpark" \
 --master local[4] \
 --conf "spark.driver.extraJavaOptions=-Dlog4j.configuration=file://$PROJECT_DIR/conf/log4j.xml" \
 target/scala-2.11/spark-scratch_2.11-1.0.jar
