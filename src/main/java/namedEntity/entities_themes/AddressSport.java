package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.Address;
import namedEntity.themes.themes_classes.Sport;

public class AddressSport extends Address implements Sport {

    public AddressSport(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public AddressSport() {
        super();
        setCategory("Address");
        setTheme("Sport");
    }

}

