import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.text.ParseException;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.xml.sax.SAXException;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import scala.Function0;
import scala.Tuple2;
import subscriptions.Subscriptions;
import webPageParser.EmptyFeedException;
import httpRequest.InvalidUrlTypeToFeedException;
import httpRequest.HttpRequestException;
import namedEntity.entities.NamedEntity;
import namedEntity.heuristic.Heuristic;
import namedEntity.heuristic.QuickHeuristic;
import feed.Article;
import feed.Feed;

public class Main {
    private static String subscriptionsFilePath = "config/subscriptions.json";

    private static void printHelp() {
        System.out.println("Please, call this program in correct way: FeedReader [-ne]");
    }

    public static void main(String[] args) throws FileNotFoundException, IllegalArgumentException, SecurityException {

        // Spark context
        SparkConf conf = new SparkConf().setAppName("FeedReader").setMaster("local[*]");

        JavaSparkContext sc = new JavaSparkContext(conf);
        sc.setLogLevel("ERROR");

        System.out.println("************* FeedReader version 1.0 *************");
        if (args.length > 1 || (args.length == 1 && !args[0].equals("-ne")))
        {
            printHelp();
            System.exit(0);
        }
        boolean normalPrint = args.length == 0;

        // Get subscriptions
        Subscriptions subscriptions = new Subscriptions();
        subscriptions.parse(subscriptionsFilePath);

        var rSubList = sc.parallelize(subscriptions.getSubscriptionList());

        // Fetch Feeds in parallel
        var feeds = rSubList.flatMap((simpleSubscription -> {
            List<Function0<Tuple2<Feed, String>>> frs = new ArrayList<>();

            for (int j = 0, szj = simpleSubscription.getUrlParametersSize(); j < szj; j++) {
                final int i = j;
                frs.add(() -> {
                    try {
                        return new Tuple2(simpleSubscription.parse(i), null);
                    } catch (InvalidUrlTypeToFeedException e) {
                        return new Tuple2(null,
                                "Invalid URL Type to get feed in "
                                        + simpleSubscription.getFormattedUrlForParameter(i));
                    } catch (HttpRequestException e) {
                        return new Tuple2(null,
                                "Error in connection: " + e.getMessage() + " "
                                        + simpleSubscription.getFormattedUrlForParameter(i));
                    } catch (EmptyFeedException e) {
                        return new Tuple2(null,
                                "Empty Feed in "
                                        + simpleSubscription.getFormattedUrlForParameter(i));
                    } catch (MalformedURLException e) {
                        return new Tuple2(null,
                                "Malformed URL exception en subscripcion "
                                        + simpleSubscription.getFormattedUrlForParameter(i));
                    } catch (IOException e) {
                        return new Tuple2(null,
                                "IO exception en subscripcion " + simpleSubscription.getFormattedUrlForParameter(i));
                    } catch (ParserConfigurationException e) {
                        return new Tuple2(null,
                                "Parse error in "
                                        + simpleSubscription.getFormattedUrlForParameter(i));
                    } catch (ParseException e) {
                        return new Tuple2(null,
                                "Parse error in "
                                        + simpleSubscription.getFormattedUrlForParameter(i));
                    } catch (SAXException e) {
                        return new Tuple2(null,
                                "SAX Exception in "
                                        + simpleSubscription.getFormattedUrlForParameter(i));
                    }
                });
            }

            return frs.iterator();
        })).mapToPair(Function0::apply);

        var parsedFeeds = feeds.filter((feedErrorTuple) -> {
            return feedErrorTuple._2() == null && feedErrorTuple._1() != null;
        }).map(Tuple2::_1);

        if(normalPrint) {
            // Filter out feeds and print them
            parsedFeeds.foreach((feed) -> {
                // Print feed to user
                feed.prettyPrint();
            });
        } else {
            parsedFeeds.flatMap(feed -> {
                if(feed.getArticleList() != null) {
                    return feed.getArticleList().iterator();
                } else {
                    return Collections.emptyIterator();
                }
            }).flatMap(article -> {
                Heuristic heur = new QuickHeuristic();
                article.computeNamedEntities(heur);
                if(article.getNamedEntityList() != null) {
                    return article.getNamedEntityList().iterator();
                } else {
                    return Collections.emptyIterator();
                }
            }).foreach(namedEntity -> {
                System.out.println(namedEntity.getName());
                System.out.println(namedEntity.getFrequency());
                System.out.println(namedEntity.getCategory());
                System.out.println(namedEntity.getTheme());
                System.out.println(namedEntity.getClass().toString());
                System.out.println("-----------");
            });
        }

        // Filter out Errors and print them
        var subscriptionErrors = feeds.filter((feedErrorTuple) -> {
            return feedErrorTuple._2() != null;
        }).map(Tuple2::_2);
        if(!subscriptionErrors.isEmpty()) {
            System.out.println("==================================================");
            System.out.println(
                    "There was a total of " + subscriptionErrors.count() + " errors in the creation of the Feeds:");
            subscriptionErrors.foreach((s) -> {
                System.out.print("  - ");
                System.out.println(s);
            });
        }


    }
}
