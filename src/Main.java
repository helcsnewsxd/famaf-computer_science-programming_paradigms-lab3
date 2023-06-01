import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.json.JSONException;
import org.xml.sax.SAXException;

import com.google.protobuf.TextFormat.ParseException;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import java.util.ArrayList;

import subscriptions.SimpleSubscription;
import subscriptions.SparkContextHolder;
import subscriptions.Subscriptions;
import webPageParser.EmptyFeedException;
import namedEntity.entities.NamedEntity;
import namedEntity.heuristic.Heuristic;
import namedEntity.heuristic.QuickHeuristic;
import feed.Article;
import feed.Feed;
import httpRequest.HttpRequestException;
import httpRequest.InvalidUrlTypeToFeedException;

public class Main implements Serializable {
    private static String subscriptionsFilePath = "config/subscriptions.json";

    private static void printHelp() {
        System.out.println("Please, call this program in correct way: FeedReader [-ne]");
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, JSONException, IOException {
        System.out.println("************* FeedReader version 1.0 *************");
        SparkConf conf = new SparkConf().setAppName("NamedEntity Recognizer").setMaster("local[*]");
        SparkContextHolder sparkHolder = new SparkContextHolder();
        JavaSparkContext sparkContext = new JavaSparkContext(conf);
        sparkHolder.setSparkContext(sparkContext);

        if (args.length > 1 || (args.length == 1 && !args[0].equals("-ne")))
            printHelp();
        else {
            Boolean normalPrint = args.length == 0;

            // List of errors
            List<String> subscriptionErrors = new ArrayList<String>();

            // Get subscriptions
            Subscriptions subscriptions = new Subscriptions();
            try {
            subscriptions.parse(subscriptionsFilePath, sparkHolder);
            } catch (IOException e) {
                subscriptionErrors.add("Error parsing subscriptions file: " + e.getMessage());
            }

            JavaRDD<SimpleSubscription> subscriptionsRDD = sparkContext.parallelize(subscriptions.getSubscriptionList());

            JavaRDD<Feed> feedsRDD = subscriptionsRDD.flatMap(subscription -> {
                List<Feed> feeds = new ArrayList<>();
                for (int j = 0, szj = subscription.getUrlParametersSize(); j < szj; j++) {
                    try {
                    Feed feed = subscription.parse(j);
                    feeds.add(feed);
                    } catch (InvalidUrlTypeToFeedException e) {
                        subscriptionErrors.add(
                                "Invalid URL Type to get feed in " + subscription.getFormattedUrlForParameter(j));
                    } catch (HttpRequestException e) {
                        subscriptionErrors.add(
                                "Error in connection: " + e.getMessage() + " " + subscription.getFormattedUrlForParameter(j));
                    } catch (EmptyFeedException e) {
                        subscriptionErrors.add(
                                "Empty Feed in " + subscription.getFormattedUrlForParameter(j));
                    } catch (MalformedURLException e) {
                        subscriptionErrors.add(
                                "Malformed URL exception en subscription " + subscription.getFormattedUrlForParameter(j));
                    } catch (IOException e) {
                        subscriptionErrors.add(
                                "IO exception en subscription " + subscription.getFormattedUrlForParameter(j));
                    } catch (ParserConfigurationException e) {
                        subscriptionErrors.add(
                                "Parse error in " + subscription.getFormattedUrlForParameter(j));
                    } catch (SAXException e) {
                        subscriptionErrors.add(
                                "SAX Exception in " + subscription.getFormattedUrlForParameter(j));
                    }
                }
                return feeds.iterator();
            });

            
            if (!normalPrint) {
                // Print feed to user
                feedsRDD.foreach(feed -> feed.prettyPrint());
            } else {
                // heuristic in use
                Heuristic heur = new QuickHeuristic();

                JavaRDD<Article> articlesRDD = feedsRDD.flatMap(feed -> feed.getArticleList().iterator());
                JavaRDD<List<NamedEntity>> namedEntitiesRDD = articlesRDD.map(article -> processNamedEntities(article, heur, sparkHolder));
                
                namedEntitiesRDD.foreach(namedEntitiesList -> {
                    for (NamedEntity namedEntity : namedEntitiesList) {
                        System.out.println(namedEntity.getName());
                        System.out.println(namedEntity.getFrequency());
                        System.out.println(namedEntity.getCategory());
                        System.out.println(namedEntity.getTheme());
                        System.out.println(namedEntity.getClass().toString());
                        System.out.println("-----------");
                    }
                });
            }

            // Print errors
            if (subscriptionErrors.size() != 0) {
                System.out.println("==================================================");
                System.out.println(
                        "There was a total of " + subscriptionErrors.size() + " errors in the creation of the Feeds:");
                for (String s : subscriptionErrors) {
                    System.out.print("  - ");
                    System.out.println(s);
                }
            }
        }
        // close spark context
        sparkHolder.closeSparkContext();
    }

    public static List<NamedEntity> processNamedEntities(Article article, Heuristic heur, SparkContextHolder sparkHolder) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // Procesar las entidades nombradas en el artículo utilizando la heurística proporcionada
        article.computeNamedEntities(heur, sparkHolder);
        // Devolver la lista de entidades nombradas encontradas en el artículo
        return article.getNamedEntityList();
    }
}
