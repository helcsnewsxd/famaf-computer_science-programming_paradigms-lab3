package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Place;
import namedEntity.themes.themes_classes.Politics;

public class PlacePolitics extends Place implements Politics {

    public PlacePolitics(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PlacePolitics() {
        super();
        setCategory("Place");
        setTheme("Politics");
    }

}

