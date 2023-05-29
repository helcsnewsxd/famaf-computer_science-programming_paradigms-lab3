package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Person;
import namedEntity.themes.themes_classes.Politics;

public class PersonPolitics extends Person implements Politics {

    public PersonPolitics(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PersonPolitics() {
        super();
        setCategory("Person");
        setTheme("Politics");
    }

}

