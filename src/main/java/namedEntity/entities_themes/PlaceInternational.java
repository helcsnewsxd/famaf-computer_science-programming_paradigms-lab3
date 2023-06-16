package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Place;
import namedEntity.themes.themes_classes.politicsSubclass.International;

public class PlaceInternational extends Place implements International {

    public PlaceInternational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PlaceInternational() {
        super();
        setCategory("Place");
        setTheme("International");
    }

}

