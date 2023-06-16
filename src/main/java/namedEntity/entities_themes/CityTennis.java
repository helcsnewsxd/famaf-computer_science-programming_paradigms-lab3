package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.placeSubclass.City;
import namedEntity.themes.themes_classes.sportSubclass.Tennis;

public class CityTennis extends City implements Tennis, Serializable {

    public CityTennis(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CityTennis() {
        super();
        setCategory("City");
        setTheme("Tennis");
    }

}

