package webPageParser;

import feed.Feed;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public abstract class GeneralParser implements Serializable {
    public abstract Feed parse(String content) throws EmptyFeedException, ParserConfigurationException, IOException, SAXException, ParseException;
}
