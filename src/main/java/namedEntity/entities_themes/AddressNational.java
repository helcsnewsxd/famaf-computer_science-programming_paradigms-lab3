package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.Address;
import namedEntity.themes.themes_classes.politicsSubclass.National;

public class AddressNational extends Address implements National {

    public AddressNational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public AddressNational() {
        super();
        setCategory("Address");
        setTheme("National");
    }

}

