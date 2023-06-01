package subscriptions;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.json.JSONException;

import java.lang.reflect.Type;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import webPageParser.RedditParser;
import webPageParser.RssParser;

public class Subscriptions implements Serializable {
    private List<SimpleSubscription> subscriptionsList;

    // INITIALIZATION

    public Subscriptions() {
        super();
        this.subscriptionsList = new ArrayList<SimpleSubscription>();
    }

    // GET
    public int getSubscriptionListSize() {
        return subscriptionsList.size();
    }

    public List<SimpleSubscription> getSubscriptionList() {
        return subscriptionsList;
    }

    public SimpleSubscription getSubscriptionList(int index) {
        return subscriptionsList.get(index);
    }

    // SET

    public void addSimpleSubscription(SimpleSubscription simpleSubscription) {
        this.subscriptionsList.add(simpleSubscription);
    }

    // PRINT
    @Override
    public String toString() {
        String str = "";
        for (SimpleSubscription s : subscriptionsList) {
            str += s.toString();
        }
        return "[" + str + "]";
    }

    public void prettyPrint() {
        System.out.println(this.toString());
    }

    private static class Subscription {
        private String url;
        private List<String> urlParams;
        private String urlType;

        public String getUrl() {
            return url;
        }

        public List<String> getUrlParams() {
            return urlParams;
        }

        public String getUrlType() {
            return urlType;
        }
    }

    public void parse(String subscriptionsFilePath, SparkContextHolder sparkHolder) throws FileNotFoundException, JSONException {

        JavaSparkContext sparkContext = sparkHolder.getSparkContext();

        JavaRDD<String> jsonData = sparkContext.wholeTextFiles(subscriptionsFilePath).values();

        JavaRDD<Subscription> subscriptionsRDD = jsonData.flatMap(json -> {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Subscription>>() {}.getType();
            List<Subscription> subscriptions = gson.fromJson(json, type);
            return subscriptions.iterator();
        });

        // Extract the required fields
        JavaRDD<SimpleSubscription> simpleSubscriptionsRDD = subscriptionsRDD.map(subscription -> {
            String url = subscription.getUrl();
            String urlType = subscription.getUrlType();
            List<String> urlParams = subscription.getUrlParams();

            SimpleSubscription simpleSubscription = new SimpleSubscription();
            simpleSubscription.setUrl(url);
            simpleSubscription.setUrlType(urlType);

            if (urlType.equals("rss")) {
                simpleSubscription.setParser(new RssParser());
            } else if (urlType.equals("reddit")) {
                simpleSubscription.setParser(new RedditParser());
            }

            for (String param : urlParams) {
                simpleSubscription.addUrlParameter(param);
            }

            return simpleSubscription;
        });

        List<SimpleSubscription> simpleSubscriptions = simpleSubscriptionsRDD.collect();
        for (SimpleSubscription simpleSubscription : simpleSubscriptions) {
            this.addSimpleSubscription(simpleSubscription);
        }
    }

    // MAIN

    public static void main(String[] args) {
        Subscriptions a = new Subscriptions();

        SimpleSubscription s0 = new SimpleSubscription(
                "https://www.chicagotribune.com/arcio/rss/category/%s/?query=display_date:[now-2d+TO+now]&sort=display_date:desc",
                null, "rss");
        s0.addUrlParameter("business");

        SimpleSubscription s1 = new SimpleSubscription("https://rss.nytimes.com/services/xml/rss/nyt/%s.xml", null,
                "rss");
        s1.addUrlParameter("Business");
        s1.addUrlParameter("Technology");

        a.addSimpleSubscription(s0);
        a.addSimpleSubscription(s1);
        a.prettyPrint();
    }
}
