package webPageParser;

import feed.Article;
import feed.Feed;
import org.json.JSONTokener;

import java.io.StringReader;
import java.sql.Date;

import org.json.JSONArray;
import org.json.JSONObject;


public class RedditParser extends GeneralParser{
    public Feed parse(String content) throws EmptyFeedException {
        // return null;
        Feed feed = new Feed("Unnamed Feed");
        StringReader reader = new StringReader(content);
        JSONTokener tokener = new JSONTokener(reader);
        JSONObject listing = new JSONObject(tokener);
        JSONObject data = listing.getJSONObject("data");
        JSONArray articles = data.getJSONArray("children");

        if(articles.length() == 0) {
            throw new EmptyFeedException("Feed vacio");
        } else {
            JSONObject firstArticle = articles.getJSONObject(0).getJSONObject("data");
            feed.setSiteName(firstArticle.getString("subreddit"));
        }

        for (int i = 0; i < articles.length(); i++) {
            JSONObject article = articles.getJSONObject(i).getJSONObject("data");

            String title = article.getString("title");
            Number timestamp = article.getNumber("created");
            String text = article.getString("selftext");
            String link = article.getString("url");

            Date date = new Date(timestamp.longValue());

            Article art = new Article(title, text, date, link);

            feed.addArticle(art);
        }


        return feed;
    }
}
