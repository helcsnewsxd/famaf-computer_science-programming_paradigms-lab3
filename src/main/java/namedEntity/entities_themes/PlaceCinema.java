package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Place;
import namedEntity.themes.themes_classes.cultureSubclass.Cinema;

public class PlaceCinema extends Place implements Cinema, Serializable {

    public PlaceCinema(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PlaceCinema() {
        super();
        setCategory("Place");
        setTheme("Cinema");
    }

}

