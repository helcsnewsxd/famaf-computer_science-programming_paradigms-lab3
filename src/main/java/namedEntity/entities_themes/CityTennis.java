package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.City;
import namedEntity.themes.themes_classes.sportSubclass.Tennis;

public class CityTennis extends City implements Tennis {

    public CityTennis(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CityTennis() {
        super();
        setCategory("City");
        setTheme("Tennis");
    }

}

