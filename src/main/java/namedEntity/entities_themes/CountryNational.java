package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.placeSubclass.Country;
import namedEntity.themes.themes_classes.politicsSubclass.National;

public class CountryNational extends Country implements National, Serializable {

    public CountryNational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CountryNational() {
        super();
        setCategory("Country");
        setTheme("National");
    }

}

