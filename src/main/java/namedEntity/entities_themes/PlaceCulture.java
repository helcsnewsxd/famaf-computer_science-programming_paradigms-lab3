package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Place;
import namedEntity.themes.themes_classes.Culture;

public class PlaceCulture extends Place implements Culture {

    public PlaceCulture(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PlaceCulture() {
        super();
        setCategory("Place");
        setTheme("Culture");
    }

}

