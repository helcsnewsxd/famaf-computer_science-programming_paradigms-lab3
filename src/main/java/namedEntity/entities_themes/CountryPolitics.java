package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.placeSubclass.Country;
import namedEntity.themes.themes_classes.Politics;

public class CountryPolitics extends Country implements Politics, Serializable {

    public CountryPolitics(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CountryPolitics() {
        super();
        setCategory("Country");
        setTheme("Politics");
    }

}

