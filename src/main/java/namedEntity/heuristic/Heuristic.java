package namedEntity.heuristic;

import java.util.Map;
import static java.util.Map.entry;

import namedEntity.entities.NamedEntity;
import namedEntity.entities_themes.AddressCinema;
import namedEntity.entities_themes.CityNational;
import namedEntity.entities_themes.CompanyOtherThemes;
import namedEntity.entities_themes.CountryInternational;
import namedEntity.entities_themes.DateOtherThemes;
import namedEntity.entities_themes.FirstnameFootball;
import namedEntity.entities_themes.FirstnameMusic;
import namedEntity.entities_themes.FirstnameOtherThemes;
import namedEntity.entities_themes.LastnameCinema;
import namedEntity.entities_themes.LastnameCulture;
import namedEntity.entities_themes.LastnameFootball;
import namedEntity.entities_themes.LastnameInternational;
import namedEntity.entities_themes.LastnamePolitics;
import namedEntity.entities_themes.LastnameTennis;
import namedEntity.entities_themes.OtherEntityCinema;
import namedEntity.entities_themes.OtherEntityNational;

public abstract class Heuristic {

    private static Map<String, Class<? extends NamedEntity>> categoryMap = Map.ofEntries(
            entry("Dybala", LastnameFootball.class),
            entry("Apple", CompanyOtherThemes.class),
            entry("Google", CompanyOtherThemes.class),
            entry("Musk", LastnameCulture.class),
            entry("Biden", LastnameInternational.class),
            entry("Trump", LastnameInternational.class),
            entry("Lionel", FirstnameFootball.class),
            entry("Messi", LastnameFootball.class),
            entry("Federer", LastnameTennis.class),
            entry("USA", CountryInternational.class),
            entry("Russia", CountryInternational.class),
            entry("Twitter", CompanyOtherThemes.class),
            entry("FBI", OtherEntityNational.class),
            entry("Pitt", LastnameCinema.class),
            entry("Reynolds", LastnameCinema.class),
            entry("Franchella", LastnameCinema.class),
            entry("IBM", CompanyOtherThemes.class),
            entry("Tini", FirstnameMusic.class),
            entry("Tiktok", CompanyOtherThemes.class),
            entry("Beijing", CityNational.class),
            entry("Washington", CityNational.class),
            entry("Taiwan", CountryInternational.class),
            entry("Sam", FirstnameOtherThemes.class),
            entry("Transformers", OtherEntityCinema.class),
            entry("Texas", CityNational.class),
            entry("Tesla", CompanyOtherThemes.class),
            entry("Hollywood", AddressCinema.class),
            entry("May", DateOtherThemes.class),
            entry("Linda", FirstnameOtherThemes.class),
            entry("China", CountryInternational.class),
            entry("Yaccarino", LastnamePolitics.class)
            );

    public Class<? extends NamedEntity> getCategory(String entity) {
        return categoryMap.get(entity);
    }

    public abstract boolean isEntity(String word);

}
