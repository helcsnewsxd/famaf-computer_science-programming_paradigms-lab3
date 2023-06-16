package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.Address;
import namedEntity.themes.themes_classes.sportSubclass.Tennis;

public class AddressTennis extends Address implements Tennis {

    public AddressTennis(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public AddressTennis() {
        super();
        setCategory("Address");
        setTheme("Tennis");
    }

}

