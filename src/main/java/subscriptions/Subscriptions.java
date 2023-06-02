package subscriptions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import webPageParser.RedditParser;
import webPageParser.RssParser;

public class Subscriptions {
    private List<SimpleSubscription> subscriptionsList;

    private final SparkSession sparkSession;

    // INITIALIZATION

    public Subscriptions() {
        super();
        this.subscriptionsList = new ArrayList<>();
        this.sparkSession = null;
    }

    public Subscriptions(SparkSession sparkSession) {
        super();
        this.subscriptionsList = new ArrayList<>();
        this.sparkSession = sparkSession;
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

    private void setSubscriptionsList(List<SimpleSubscription> subscriptionList) {
        this.subscriptionsList = subscriptionList;
    }

    public void addSimpleSubscription(SimpleSubscription simpleSubscription) {
        this.subscriptionsList.add(simpleSubscription);
    }

    // PRINT
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (SimpleSubscription s : subscriptionsList) {
            str.append(s.toString());
        }
        return "[" + str + "]";
    }

    public void prettyPrint() {
        System.out.println(this);
    }

    public void parse(String subscriptionsFilePath) throws FileNotFoundException, JSONException {

        FileReader reader = new FileReader(subscriptionsFilePath);

        JSONTokener token = new JSONTokener(reader);
        JSONArray arr = new JSONArray(token);

        if(sparkSession == null) { // No se paraleliza
            for (int i = 0, szi = arr.length(); i < szi; i++) {
                JSONObject obj = arr.getJSONObject(i);

                SimpleSubscription simpleSubscription = new SimpleSubscription();
                simpleSubscription.setUrl(obj.getString("url"));
                String urlType = obj.getString("urlType");
                simpleSubscription.setUrlType(urlType);

                // Inyectar parser adecuado
                if (urlType.equals("rss")) {
                    simpleSubscription.setParser(new RssParser());
                } else if (urlType.equals("reddit")) {
                    simpleSubscription.setParser(new RedditParser());
                }

                JSONArray arrUrlParams = obj.getJSONArray("urlParams");
                for (int j = 0, szj = arrUrlParams.length(); j < szj; j++) {
                    simpleSubscription.addUrlParameter(arrUrlParams.getString(j));
                }

                this.addSimpleSubscription(simpleSubscription);
            }
        } else { // Se paraleliza
            // Preparo la lista de JSONObject a paralelizar
            // Considero Strings porque JSONObject no es Serializable
            List<String> arrObjString = new ArrayList<>();
            for (int i = 0, szi = arr.length(); i < szi; i++)
                arrObjString.add(arr.getJSONObject(i).toString());
            JavaRDD<String> objStringDataset = sparkSession.createDataset(arrObjString, Encoders.bean(String.class)).javaRDD();

            List<SimpleSubscription> simpleSubscriptionList = objStringDataset
                    // Creo la simpleSubscription y la instancio
                    .flatMap(objString -> {
                        JSONObject obj = new JSONObject(objString);

                        SimpleSubscription simpleSubscription = new SimpleSubscription();
                        simpleSubscription.setUrl(obj.getString("url"));
                        String urlType = obj.getString("urlType");
                        simpleSubscription.setUrlType(urlType);

                        // Inyectar parser adecuado
                        if (urlType.equals("rss")) {
                            simpleSubscription.setParser(new RssParser());
                        } else if (urlType.equals("reddit")) {
                            simpleSubscription.setParser(new RedditParser());
                        }

                        JSONArray arrUrlParams = obj.getJSONArray("urlParams");
                        for (int j = 0, szj = arrUrlParams.length(); j < szj; j++)
                            simpleSubscription.addUrlParameter(arrUrlParams.getString(j));

                        return Collections.singletonList(simpleSubscription).iterator();
                    })
                    // Obtengo la lista de simpleSubscriptions
                    .collect();

            // Seteo la lista obtenida
            setSubscriptionsList(simpleSubscriptionList);
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
