package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Person;
import namedEntity.themes.themes_classes.sportSubclass.Tennis;

public class PersonTennis extends Person implements Tennis {

    public PersonTennis(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PersonTennis() {
        super();
        setCategory("Person");
        setTheme("Tennis");
    }

}

