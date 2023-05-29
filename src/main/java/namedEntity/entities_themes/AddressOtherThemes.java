package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.Address;
import namedEntity.themes.themes_classes.OtherThemes;

public class AddressOtherThemes extends Address implements OtherThemes {

    public AddressOtherThemes(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public AddressOtherThemes() {
        super();
        setCategory("Address");
        setTheme("OtherThemes");
    }

}

