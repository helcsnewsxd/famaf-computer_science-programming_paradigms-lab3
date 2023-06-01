package subscriptions;
import java.io.Serializable;

import org.apache.spark.api.java.JavaSparkContext;

public class SparkContextHolder implements Serializable{
    private static transient JavaSparkContext sparkContext;

    public JavaSparkContext getSparkContext() {
        return sparkContext;
    }

    public void setSparkContext(JavaSparkContext context) {
        sparkContext = context;
    }

    public void closeSparkContext() {
        if (sparkContext != null) {
            sparkContext.close();
        }
    }
}
