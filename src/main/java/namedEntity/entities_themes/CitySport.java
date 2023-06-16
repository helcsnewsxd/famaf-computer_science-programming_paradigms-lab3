package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.placeSubclass.City;
import namedEntity.themes.themes_classes.Sport;

public class CitySport extends City implements Sport, Serializable {

    public CitySport(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CitySport() {
        super();
        setCategory("City");
        setTheme("Sport");
    }

}

