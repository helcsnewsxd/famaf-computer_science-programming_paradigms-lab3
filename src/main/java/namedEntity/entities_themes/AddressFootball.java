package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.Address;
import namedEntity.themes.themes_classes.sportSubclass.Football;

public class AddressFootball extends Address implements Football {

    public AddressFootball(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public AddressFootball() {
        super();
        setCategory("Address");
        setTheme("Football");
    }

}

