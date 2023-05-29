package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Lastname;
import namedEntity.themes.themes_classes.cultureSubclass.Cinema;

public class LastnameCinema extends Lastname implements Cinema {

    public LastnameCinema(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public LastnameCinema() {
        super();
        setCategory("Lastname");
        setTheme("Cinema");
    }

}

