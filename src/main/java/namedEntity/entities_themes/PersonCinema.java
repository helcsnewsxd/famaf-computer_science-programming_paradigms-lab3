package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Person;
import namedEntity.themes.themes_classes.cultureSubclass.Cinema;

public class PersonCinema extends Person implements Cinema {

    public PersonCinema(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PersonCinema() {
        super();
        setCategory("Person");
        setTheme("Cinema");
    }

}

