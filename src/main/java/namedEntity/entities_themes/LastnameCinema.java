package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.personSubclass.Lastname;
import namedEntity.themes.themes_classes.cultureSubclass.Cinema;

public class LastnameCinema extends Lastname implements Cinema, Serializable {

    public LastnameCinema(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public LastnameCinema() {
        super();
        setCategory("Lastname");
        setTheme("Cinema");
    }

}

