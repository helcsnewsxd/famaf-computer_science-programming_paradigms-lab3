import feed.Feed;
import httpRequest.HttpRequestException;
import httpRequest.InvalidUrlTypeToFeedException;
import namedEntity.heuristic.Heuristic;
import namedEntity.heuristic.QuickHeuristic;
import namedEntity.heuristic.RandomHeuristic;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.xml.sax.SAXException;
import scala.Tuple2;
import subscriptions.SimpleSubscription;
import subscriptions.Subscriptions;
import webPageParser.EmptyFeedException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private static final String subscriptionsFilePath = "config/subscriptions.json";

    private static void printHelp() {
        System.out.println("Please, call this program in correct way: FeedReader [-ne]");
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("************* FeedReader version 2.0 (Spark) *************");

        if (args.length > 1 || (args.length == 1 && !args[0].equals("-ne"))) {
            printHelp();
            return;
        }

        // Configuración de Spark
        SparkConf sparkConf = new SparkConf()
                .setAppName("feedReader")
                .setMaster("local[*]");

        JavaSparkContext spark = new JavaSparkContext(sparkConf);

        // Deshabilitar los LOGS de INFO (porque ya anda bien)
        spark.setLogLevel("ERROR");

        boolean normalPrint = args.length == 0;

        Subscriptions subscriptions = new Subscriptions();
        subscriptions.parse(subscriptionsFilePath);

        // Paralelizo la lista de las subscripciones para hacerlo de forma concurrente
        JavaRDD<SimpleSubscription> subscriptionList = spark.parallelize(subscriptions.getSubscriptionList());

        // Obtengo todos los feeds
        // Se consideran tuplas (feed, error). Una es null y la otra es dato (se usa
        // para diferenciar)
        JavaRDD<Tuple2<Feed, String>> feeds = subscriptionList
                // Separo las subscripciones por sus parámetros
                .flatMap(simpleSubscription -> {
                    List<Tuple2<SimpleSubscription, Integer>> feedConstructorOptionsList = new ArrayList<>();
                    for (int i = 0, szi = simpleSubscription.getUrlParametersSize(); i < szi; i++)
                        feedConstructorOptionsList.add(new Tuple2<>(simpleSubscription, i));
                    return feedConstructorOptionsList.iterator();
                })
                // Obtengo el feed en base a los parámetros considerados (subscripción y
                // urlParameter)
                // Se devuelve en el formato (feed, error) siendo solo una null en cada tupla
                .flatMap(feedOptions -> {
                    try {
                        Feed actualFeed = feedOptions._1().parse(feedOptions._2());
                        return Collections.singletonList(new Tuple2<Feed, String>(actualFeed, null)).iterator();
                    } catch (InvalidUrlTypeToFeedException e) {
                        String actualError = "Invalid URL Type to get feed in "
                                + feedOptions._1().getFormattedUrlForParameter(feedOptions._2());
                        return Collections.singletonList(new Tuple2<Feed, String>(null, actualError)).iterator();
                    } catch (IOException e) {
                        String actualError = "IO exception in subscription "
                                + feedOptions._1().getFormattedUrlForParameter(feedOptions._2());
                        return Collections.singletonList(new Tuple2<Feed, String>(null, actualError)).iterator();
                    } catch (HttpRequestException e) {
                        String actualError = "Error in connection: " + e.getMessage() + " "
                                + feedOptions._1().getFormattedUrlForParameter(feedOptions._2());
                        return Collections.singletonList(new Tuple2<Feed, String>(null, actualError)).iterator();
                    } catch (ParserConfigurationException | ParseException e) {
                        String actualError = "Parse error in "
                                + feedOptions._1().getFormattedUrlForParameter(feedOptions._2());
                        return Collections.singletonList(new Tuple2<Feed, String>(null, actualError)).iterator();
                    } catch (SAXException e) {
                        String actualError = "SAX Exception in "
                                + feedOptions._1().getFormattedUrlForParameter(feedOptions._2());
                        return Collections.singletonList(new Tuple2<Feed, String>(null, actualError)).iterator();
                    } catch (EmptyFeedException e) {
                        String actualError = "Empty Feed in "
                                + feedOptions._1().getFormattedUrlForParameter(feedOptions._2());
                        return Collections.singletonList(new Tuple2<Feed, String>(null, actualError)).iterator();
                    }
                });

        // Preparo la lista de feeds obtenidos
        JavaRDD<Feed> feedList = feeds
                .filter(actualFeed -> actualFeed._1() != null)
                .flatMap(actualFeed -> Collections.singletonList(actualFeed._1()).iterator());

        // Preparo la lista de errores que sucedieron
        JavaRDD<String> errorList = feeds
                .filter(actualFeed -> actualFeed._2() != null)
                .flatMap(actualFeed -> Collections.singletonList(actualFeed._2()).iterator());

        if (normalPrint) {
            // Muestra los feeds al usuario
            feedList.foreach(Feed::prettyPrint);
        } else {
            // Heurística en uso
            Heuristic heuristicUsed = new QuickHeuristic();

            JavaRDD<namedEntity.entities.NamedEntity> namedEntities = feedList
                    // Obtengo todos los artículos
                    .flatMap(feed -> feed.getArticleList().iterator())
                    // Obtengo las namedEntity
                    .flatMap(article -> {
                        article.computeNamedEntities(heuristicUsed);
                        return article.getNamedEntityList().iterator();
                    });

            // Muestro las namedEntity en pantalla
            namedEntities.foreach(namedEntity -> {
                System.out.println(namedEntity.getName());
                System.out.println(namedEntity.getFrequency());
                System.out.println(namedEntity.getCategory());
                System.out.println(namedEntity.getTheme());
                System.out.println(namedEntity.getClass().toString());
                System.out.println("-----------");
            });
        }

        // Imprimo los errores en caso que haya habido
        if (!errorList.isEmpty()) {
            System.out.println("==================================================");
            System.out.println(
                    "There was a total of " + errorList.count() + " errors in the creation of the Feeds:");
            errorList.foreach(error -> System.out.println("  - " + error));
        }

        spark.close();
    }
}