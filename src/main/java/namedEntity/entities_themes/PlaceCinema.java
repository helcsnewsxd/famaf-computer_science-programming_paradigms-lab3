package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Place;
import namedEntity.themes.themes_classes.cultureSubclass.Cinema;

public class PlaceCinema extends Place implements Cinema {

    public PlaceCinema(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PlaceCinema() {
        super();
        setCategory("Place");
        setTheme("Cinema");
    }

}

