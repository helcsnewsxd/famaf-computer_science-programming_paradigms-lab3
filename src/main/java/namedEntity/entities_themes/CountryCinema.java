package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.Country;
import namedEntity.themes.themes_classes.cultureSubclass.Cinema;

public class CountryCinema extends Country implements Cinema {

    public CountryCinema(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CountryCinema() {
        super();
        setCategory("Country");
        setTheme("Cinema");
    }

}

