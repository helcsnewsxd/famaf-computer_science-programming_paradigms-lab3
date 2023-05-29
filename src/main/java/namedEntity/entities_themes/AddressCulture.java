package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.Address;
import namedEntity.themes.themes_classes.Culture;

public class AddressCulture extends Address implements Culture {

    public AddressCulture(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public AddressCulture() {
        super();
        setCategory("Address");
        setTheme("Culture");
    }

}

