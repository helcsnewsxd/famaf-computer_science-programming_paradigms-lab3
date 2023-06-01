package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.Country;
import namedEntity.themes.themes_classes.politicsSubclass.International;

public class CountryInternational extends Country implements International {

    public CountryInternational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CountryInternational() {
        super();
        setCategory("Country");
        setTheme("International");
    }

}

