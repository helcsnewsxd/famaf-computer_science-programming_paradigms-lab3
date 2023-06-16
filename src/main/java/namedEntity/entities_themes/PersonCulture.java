package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Person;
import namedEntity.themes.themes_classes.Culture;

public class PersonCulture extends Person implements Culture, Serializable {

    public PersonCulture(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PersonCulture() {
        super();
        setCategory("Person");
        setTheme("Culture");
    }

}

