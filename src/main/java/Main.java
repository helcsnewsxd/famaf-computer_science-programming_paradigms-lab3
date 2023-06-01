import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.text.ParseException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.util.List;
import java.util.ArrayList;

import subscriptions.SimpleSubscription;
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

    public static void main(String[] args) throws FileNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        System.out.println("************* FeedReader version 1.0 *************");
        if (args.length > 1 || (args.length == 1 && !args[0].equals("-ne")))
            printHelp();
        else {
            Boolean normalPrint = args.length == 0;

            // List of errors
            List<String> subscriptionErrors = new ArrayList<String>();

            // Get subscriptions
            Subscriptions subscriptions = new Subscriptions();
            subscriptions.parse(subscriptionsFilePath);

            for (int i = 0, szi = subscriptions.getSubscriptionListSize(); i < szi; i++) {
                SimpleSubscription simpleSubscription = subscriptions.getSubscriptionList(i);

                for (int j = 0, szj = simpleSubscription.getUrlParametersSize(); j < szj; j++) {
                    try {
                        Feed feed = simpleSubscription.parse(j);

                        if (normalPrint) {
                            // Print feed to user

                            feed.prettyPrint();
                        } else {
                            // heuristic in use
                            Heuristic heur = new QuickHeuristic();


                            // computes the named entities for each article, saving all ne in their respective lists
                            for (Article article : feed.getArticleList()) {
                                article.computeNamedEntities(heur);
                                for (NamedEntity namedEntity : article.getNamedEntityList()) {
                                    System.out.println(namedEntity.getName());
                                    System.out.println(namedEntity.getFrequency());
                                    System.out.println(namedEntity.getCategory());
                                    System.out.println(namedEntity.getTheme());
                                    System.out.println(namedEntity.getClass().toString());
                                    System.out.println("-----------");
                                }
                            }
                        }

                    } catch (InvalidUrlTypeToFeedException e) {
                        subscriptionErrors.add(
                                    "Invalid URL Type to get feed in "
                                            + simpleSubscription.getFormattedUrlForParameter(j));
                    } catch (HttpRequestException e) {
                        subscriptionErrors.add(
                                    "Error in connection: " + e.getMessage() + " "
                                            + simpleSubscription.getFormattedUrlForParameter(j));
                    } catch (EmptyFeedException e) {
                        subscriptionErrors.add(
                                    "Empty Feed in "
                                            + simpleSubscription.getFormattedUrlForParameter(j));
                    } catch (MalformedURLException e) {
                        subscriptionErrors.add(
                                "Malformed URL exception en subscripcion "
                                        + simpleSubscription.getFormattedUrlForParameter(j));
                    } catch (IOException e) {
                        subscriptionErrors.add(
                                "IO exception en subscripcion " + simpleSubscription.getFormattedUrlForParameter(j));
                    } catch (ParserConfigurationException e) {
                        subscriptionErrors.add(
                                "Parse error in "
                                        + simpleSubscription.getFormattedUrlForParameter(j));
                    } catch (ParseException e) {
                        subscriptionErrors.add(
                                "Parse error in "
                                        + simpleSubscription.getFormattedUrlForParameter(j));
                    } catch (SAXException e) {
                        subscriptionErrors.add(
                                "SAX Exception in "
                                        + simpleSubscription.getFormattedUrlForParameter(j));
                    }
                }
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
    }
}
