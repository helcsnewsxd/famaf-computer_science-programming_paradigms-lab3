package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.City;
import namedEntity.themes.themes_classes.Politics;

public class CityPolitics extends City implements Politics {

    public CityPolitics(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CityPolitics() {
        super();
        setCategory("City");
        setTheme("Politics");
    }

}

