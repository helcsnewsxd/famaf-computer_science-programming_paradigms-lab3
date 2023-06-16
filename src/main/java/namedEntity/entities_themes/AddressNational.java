package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.placeSubclass.Address;
import namedEntity.themes.themes_classes.politicsSubclass.National;

public class AddressNational extends Address implements National, Serializable {

    public AddressNational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public AddressNational() {
        super();
        setCategory("Address");
        setTheme("National");
    }

}

