package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.placeSubclass.City;
import namedEntity.themes.themes_classes.politicsSubclass.National;

public class CityNational extends City implements National, Serializable {

    public CityNational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CityNational() {
        super();
        setCategory("City");
        setTheme("National");
    }

}

