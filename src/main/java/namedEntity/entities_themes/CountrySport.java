package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.Country;
import namedEntity.themes.themes_classes.Sport;

public class CountrySport extends Country implements Sport {

    public CountrySport(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CountrySport() {
        super();
        setCategory("Country");
        setTheme("Sport");
    }

}

