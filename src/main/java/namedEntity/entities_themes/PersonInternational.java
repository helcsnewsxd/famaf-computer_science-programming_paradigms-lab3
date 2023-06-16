package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Person;
import namedEntity.themes.themes_classes.politicsSubclass.International;

public class PersonInternational extends Person implements International {

    public PersonInternational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PersonInternational() {
        super();
        setCategory("Person");
        setTheme("International");
    }

}

