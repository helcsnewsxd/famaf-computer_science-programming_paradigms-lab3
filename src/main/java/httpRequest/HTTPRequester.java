package httpRequest;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPRequester {
    public String getFeed(String urlFeed, String urlType) throws InvalidUrlTypeToFeedException, IOException, HttpRequestException {
        if (urlType.equals("rss"))
            return getFeedRss(urlFeed);
        else if (urlType.equals("reddit"))
            return getFeedReedit(urlFeed);
        else
            throw new InvalidUrlTypeToFeedException("invalid urlType");
    }

    private String getFeedRss(String urlFeed) throws IOException, HttpRequestException {
        return getResponse(urlFeed);
    }

    private static String getFeedReedit(String urlFeed) throws IOException, HttpRequestException{
        return getResponse(urlFeed);
    }

    private static String getResponse(String url) throws IOException, HttpRequestException {
        StringBuilder feedRssXml = new StringBuilder();

        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        con.addRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");
        con.setRequestMethod("GET");
        con.setInstanceFollowRedirects(true);

        int status = con.getResponseCode();

        BufferedReader streamReader;

        boolean reqError;

        if (status > 299) {
            reqError = true;
            streamReader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        } else {
            reqError = false;
            streamReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        }
        String inputLine;

        while ((inputLine = streamReader.readLine()) != null) {
            feedRssXml.append(inputLine);
        }

        if (reqError) {
            throw new HttpRequestException(feedRssXml.toString()); 
        }

        return feedRssXml.toString();

    }
}
