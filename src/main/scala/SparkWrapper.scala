import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StructType

object SparkWrapper {
  implicit val spark = SparkSession
    .builder
    .appName("WriteToConsole")
    .master("local[*]")
    .getOrCreate()

  val customerFiles = "C:/Users/pmatt/Documents/Dev/big-data-env/source/customers"

  val userSchema = new StructType()
    .add("identity", "string")
    .add("age", "integer")
    .add("name","string")

}
