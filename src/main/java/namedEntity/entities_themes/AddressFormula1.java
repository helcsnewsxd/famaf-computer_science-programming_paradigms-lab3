package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.placeSubclass.Address;
import namedEntity.themes.themes_classes.sportSubclass.Formula1;

public class AddressFormula1 extends Address implements Formula1, Serializable {

    public AddressFormula1(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public AddressFormula1() {
        super();
        setCategory("Address");
        setTheme("Formula1");
    }

}

