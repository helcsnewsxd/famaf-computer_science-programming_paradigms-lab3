package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Place;
import namedEntity.themes.themes_classes.Sport;

public class PlaceSport extends Place implements Sport {

    public PlaceSport(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PlaceSport() {
        super();
        setCategory("Place");
        setTheme("Sport");
    }

}

