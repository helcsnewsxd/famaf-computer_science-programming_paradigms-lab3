package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.placeSubclass.Country;
import namedEntity.themes.themes_classes.OtherThemes;

public class CountryOtherThemes extends Country implements OtherThemes, Serializable {

    public CountryOtherThemes(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CountryOtherThemes() {
        super();
        setCategory("Country");
        setTheme("OtherThemes");
    }

}

