package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.placeSubclass.Address;
import namedEntity.themes.themes_classes.Culture;

public class AddressCulture extends Address implements Culture, Serializable {

    public AddressCulture(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public AddressCulture() {
        super();
        setCategory("Address");
        setTheme("Culture");
    }

}

