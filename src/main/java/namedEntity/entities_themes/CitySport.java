package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.City;
import namedEntity.themes.themes_classes.Sport;

public class CitySport extends City implements Sport {

    public CitySport(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CitySport() {
        super();
        setCategory("City");
        setTheme("Sport");
    }

}

