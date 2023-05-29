package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Person;
import namedEntity.themes.themes_classes.Sport;

public class PersonSport extends Person implements Sport {

    public PersonSport(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PersonSport() {
        super();
        setCategory("Person");
        setTheme("Sport");
    }

}

