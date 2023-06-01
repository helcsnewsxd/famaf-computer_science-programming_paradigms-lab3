package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.City;
import namedEntity.themes.themes_classes.politicsSubclass.International;

public class CityInternational extends City implements International {

    public CityInternational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CityInternational() {
        super();
        setCategory("City");
        setTheme("International");
    }

}

