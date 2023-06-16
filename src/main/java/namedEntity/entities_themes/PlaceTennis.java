package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Place;
import namedEntity.themes.themes_classes.sportSubclass.Tennis;

public class PlaceTennis extends Place implements Tennis {

    public PlaceTennis(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PlaceTennis() {
        super();
        setCategory("Place");
        setTheme("Tennis");
    }

}

