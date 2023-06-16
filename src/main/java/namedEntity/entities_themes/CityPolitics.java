package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.placeSubclass.City;
import namedEntity.themes.themes_classes.Politics;

public class CityPolitics extends City implements Politics, Serializable {

    public CityPolitics(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CityPolitics() {
        super();
        setCategory("City");
        setTheme("Politics");
    }

}

