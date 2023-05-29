package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Person;
import namedEntity.themes.themes_classes.sportSubclass.Football;

public class PersonFootball extends Person implements Football {

    public PersonFootball(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PersonFootball() {
        super();
        setCategory("Person");
        setTheme("Football");
    }

}

