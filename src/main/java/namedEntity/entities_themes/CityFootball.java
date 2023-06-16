package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.placeSubclass.City;
import namedEntity.themes.themes_classes.sportSubclass.Football;

public class CityFootball extends City implements Football, Serializable {

    public CityFootball(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CityFootball() {
        super();
        setCategory("City");
        setTheme("Football");
    }

}

