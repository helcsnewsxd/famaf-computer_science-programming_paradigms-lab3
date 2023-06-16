package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.Country;
import namedEntity.themes.themes_classes.Culture;

public class CountryCulture extends Country implements Culture {

    public CountryCulture(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CountryCulture() {
        super();
        setCategory("Country");
        setTheme("Culture");
    }

}

