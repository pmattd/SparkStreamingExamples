import org.apache.spark.sql.types.StructType
import SparkWrapper.{customerFiles, spark, userSchema}

object SimpleSparkWriteToConsole {

  def main(args: Array[String]): Unit = {


    spark.sparkContext.setLogLevel("ERROR")

    val csvDF = spark
      .readStream
      .option("sep", ",")
      .schema(userSchema)
      .csv(customerFiles)


    val query = csvDF.writeStream
      .format("console")
      .option("truncate","false")
      .start()

    query.awaitTermination()
  }


}
