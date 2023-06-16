package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.Country;
import namedEntity.themes.themes_classes.sportSubclass.Tennis;

public class CountryTennis extends Country implements Tennis {

    public CountryTennis(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CountryTennis() {
        super();
        setCategory("Country");
        setTheme("Tennis");
    }

}

