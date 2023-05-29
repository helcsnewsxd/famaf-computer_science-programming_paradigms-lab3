package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.City;
import namedEntity.themes.themes_classes.cultureSubclass.Cinema;

public class CityCinema extends City implements Cinema {

    public CityCinema(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CityCinema() {
        super();
        setCategory("City");
        setTheme("Cinema");
    }

}

