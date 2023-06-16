package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Place;
import namedEntity.themes.themes_classes.politicsSubclass.National;

public class PlaceNational extends Place implements National {

    public PlaceNational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PlaceNational() {
        super();
        setCategory("Place");
        setTheme("National");
    }

}

