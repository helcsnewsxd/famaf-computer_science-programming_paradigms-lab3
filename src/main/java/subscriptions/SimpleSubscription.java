package subscriptions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import feed.Feed;
import httpRequest.HTTPRequester;
import httpRequest.HttpRequestException;
import httpRequest.InvalidUrlTypeToFeedException;
import webPageParser.EmptyFeedException;
import webPageParser.GeneralParser;

public class SimpleSubscription {
    private String url;
    private List<String> urlParameters;
    private String urlType;
    private GeneralParser parser;

    // INITIALIZATION
    
    public SimpleSubscription() {
        super();
        this.url = this.urlType = null;
        this.urlParameters = new ArrayList<String>();
    }
    
    public SimpleSubscription(String url, List<String> urlParameters, String urlType) {
        super();
        this.url = url;
        this.urlType = urlType;
        
        if (urlParameters == null)
        this.urlParameters = new ArrayList<String>();
        else
        this.urlParameters = urlParameters;
    }

    // GET
    
    public String getUrl() {
        return url;
    }
    
    public String getUrlType() {
        return urlType;
    }
    
    public int getUrlParametersSize() {
        return urlParameters.size();
    }
    
    public List<String> getUrlParameters() {
        return urlParameters;
    }
    
    public String getUrlParameters(int index) {
        return urlParameters.get(index);
    }

    public String getFormattedUrlForParameter(int index) {
        return String.format(url, urlParameters.get(index));
    }
    
    // SET

    public void setParser(GeneralParser parser) {
        this.parser = parser;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public void addUrlParameter(String urlParameter) {
        this.urlParameters.add(urlParameter);
    }

    public Feed parse(int paramIndex) throws MalformedURLException, InvalidUrlTypeToFeedException, IOException, HttpRequestException, ParserConfigurationException, SAXException, ParseException, EmptyFeedException {
        HTTPRequester httpRequester = new HTTPRequester();
        String feedText = httpRequester.getFeed(this.getFormattedUrlForParameter(paramIndex),
                this.getUrlType());

        Feed feed = this.parser.parse(feedText);
        return feed;

    }

    // PRINT

    @Override
    public String toString() {
        return "{url=" + getUrl() + ", ulrParams=" + getUrlParameters().toString() + ", urlType=" + getUrlType() + "}";
    }

    public void prettyPrint() {
        System.out.println(this.toString());
    }

    // MAIN

    public static void main(String[] args) {
        System.out.println("SingleSubscriptionClass");
        SimpleSubscription s = new SimpleSubscription("https://rss.nytimes.com/services/xml/rss/nyt/%s.xml", null,
                "rss");
        s.addUrlParameter("Business");
        s.addUrlParameter("Technology");
        System.out.println(s.getFormattedUrlForParameter(0));
        s.prettyPrint();
    }
}
