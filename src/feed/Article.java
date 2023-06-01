package feed;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import namedEntity.entities.NamedEntity;
import namedEntity.entities_themes.OtherEntityOtherThemes;
import namedEntity.heuristic.Heuristic;
import scala.Tuple2;
import subscriptions.SparkContextHolder;

public class Article implements Serializable{
    private String title;
    private String text;
    private Date publicationDate;
    private String link;

    private List<NamedEntity> namedEntityList = new ArrayList<>();

    public List<NamedEntity> getNamedEntityList () {
        return this.namedEntityList;
    }

    public Article(String title, String text, Date publicationDate, String link) {
        super();
        this.title = title;
        this.text = text;
        this.publicationDate = publicationDate;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Article [title=" + title + ", text=" + text + ", publicationDate=" + publicationDate + ", link=" + link
                + "]";
    }

    public NamedEntity getNamedEntity(String namedEntity) {
        for (NamedEntity n : namedEntityList) {
            if (n.getName().compareTo(namedEntity) == 0) {
                return n;
            }
        }
        return null;
    }

    public void computeNamedEntities(Heuristic h, SparkContextHolder sparkHolder) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        
        JavaSparkContext sparkContext = sparkHolder.getSparkContext();

        String text = this.getTitle() + " " + this.getText();

        String charsToRemove = ".,;:()'!?\n";
        for (char c : charsToRemove.toCharArray()) {
            text = text.replace(String.valueOf(c), "");
        }

        JavaRDD<String> wordsRDD = sparkContext.parallelize(Arrays.asList(text.split(" ")));
        
        JavaPairRDD<String, Integer> entityWordsRDD = wordsRDD
            .filter(s -> h.isEntity(s))
            .mapToPair(s -> new Tuple2<>(s, 1))
            .reduceByKey((freq1, freq2) -> freq1 + freq2);

            JavaRDD<NamedEntity> namedEntitiesRDD = entityWordsRDD.map(tuple -> {
            String name = tuple._1();
            NamedEntity ne = this.getNamedEntity(name);

            if (ne == null){
                Class<? extends NamedEntity> categoryClass = h.getCategory(name);
                if(categoryClass == null) {
                    categoryClass = OtherEntityOtherThemes.class;
                }
                ne = categoryClass.getDeclaredConstructor().newInstance();
                ne.setFrequency(tuple._2());
                ne.setName(name);
            }
            return ne;
        });

        List<NamedEntity> namedEntities = namedEntitiesRDD.collect();
        this.namedEntityList.addAll(namedEntities);
    }

    public void prettyPrint() {
        System.out.println(
                "**********************************************************************************************");
        System.out.println("Title: " + this.getTitle());
        System.out.println("Publication Date: " + this.getPublicationDate());
        System.out.println("Link: " + this.getLink());
        System.out.println("Text: " + this.getText());
        System.out.println(
                "**********************************************************************************************");

    }

    public static void main(String[] args) {
        Article a = new Article("This Historically Black University Created Its Own Tech Intern Pipeline",
                "A new program at Bowie State connects computing students directly with companies, bypassing an often harsh Silicon Valley vetting process",
                new Date(),
                "https://www.nytimes.com/2023/04/05/technology/bowie-hbcu-tech-intern-pipeline.html");

        a.prettyPrint();
    }

}