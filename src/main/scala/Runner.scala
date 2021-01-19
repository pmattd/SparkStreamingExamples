import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StructType

object Runner {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("StructuredNetworkWordCount")
      .master("local[*]")
      .getOrCreate()


    println("runnin")
    val userSchema = new StructType()
      .add("identity", "string")
      .add("age", "integer")
      .add("name","string")

    val csvDF = spark
      .readStream
      .option("sep", ",")
      .schema(userSchema)      // Specify schema of the csv files
      .csv("C:/Users/pmatt/Documents/Dev/big-data-env/source/customers")    // Equivalent to format("csv").load("/path/to/directory")



    val query = csvDF.writeStream
      .format("console")
      .option("truncate","false")
      .start()

    query.awaitTermination()


  }


}
