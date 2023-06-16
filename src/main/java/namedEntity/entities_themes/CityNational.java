package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.City;
import namedEntity.themes.themes_classes.politicsSubclass.National;

public class CityNational extends City implements National {

    public CityNational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CityNational() {
        super();
        setCategory("City");
        setTheme("National");
    }

}

