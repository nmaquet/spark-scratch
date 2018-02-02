#!/bin/bash

set -e

aws s3 cp target/scala-2.11/spark-scratch_2.11-1.0.jar s3://emr-meeting-test/HelloSpark.jar

aws emr add-steps --cluster-id j-2QQY538Q74SK8 --steps Type=Spark,Name=HelloSpark,ActionOnFailure=CONTINUE,Args=[--class,HelloSpark,s3://emr-meeting-test/HelloSpark.jar]
