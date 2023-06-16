package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Place;
import namedEntity.themes.themes_classes.sportSubclass.Football;

public class PlaceFootball extends Place implements Football, Serializable {

    public PlaceFootball(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PlaceFootball() {
        super();
        setCategory("Place");
        setTheme("Football");
    }

}

