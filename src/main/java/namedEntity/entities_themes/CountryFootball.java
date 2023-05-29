package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.Country;
import namedEntity.themes.themes_classes.sportSubclass.Football;

public class CountryFootball extends Country implements Football {

    public CountryFootball(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CountryFootball() {
        super();
        setCategory("Country");
        setTheme("Football");
    }

}

