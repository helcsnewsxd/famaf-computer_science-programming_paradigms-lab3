package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Firstname;
import namedEntity.themes.themes_classes.cultureSubclass.Cinema;

public class FirstnameCinema extends Firstname implements Cinema {

    public FirstnameCinema(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public FirstnameCinema() {
        super();
        setCategory("Firstname");
        setTheme("Cinema");
    }

}

