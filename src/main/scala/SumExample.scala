import SparkWrapper.{customerFiles, spark, userSchema}
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.types.StructType

object SumExample {

  def main(args: Array[String]): Unit = {

    spark.sparkContext.setLogLevel("ERROR")


    val csvDF = spark
      .readStream
      .option("sep", ",")
      .schema(userSchema)
      .csv(customerFiles)

    val countdf = csvDF.drop("identity").groupBy(col("name")).sum("age")

    val query = countdf.writeStream
      .outputMode("complete")
      .format("console")
      .option("truncate", "false")
      .start()

    query.awaitTermination()
  }
}
