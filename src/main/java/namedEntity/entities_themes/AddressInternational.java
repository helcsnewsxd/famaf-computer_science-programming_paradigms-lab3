package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.Address;
import namedEntity.themes.themes_classes.politicsSubclass.International;

public class AddressInternational extends Address implements International {

    public AddressInternational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public AddressInternational() {
        super();
        setCategory("Address");
        setTheme("International");
    }

}

