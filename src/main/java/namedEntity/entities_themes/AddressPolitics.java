package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.Address;
import namedEntity.themes.themes_classes.Politics;

public class AddressPolitics extends Address implements Politics {

    public AddressPolitics(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public AddressPolitics() {
        super();
        setCategory("Address");
        setTheme("Politics");
    }

}

