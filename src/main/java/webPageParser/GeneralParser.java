package webPageParser;

import feed.Feed;

import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public abstract class GeneralParser {
    public abstract Feed parse(String content) throws EmptyFeedException, ParserConfigurationException, IOException, SAXException, ParseException;
}
