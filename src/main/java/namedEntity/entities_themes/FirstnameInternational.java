package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.personSubclass.Firstname;
import namedEntity.themes.themes_classes.politicsSubclass.International;

public class FirstnameInternational extends Firstname implements International, Serializable {

    public FirstnameInternational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public FirstnameInternational() {
        super();
        setCategory("Firstname");
        setTheme("International");
    }

}

