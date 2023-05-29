package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Firstname;
import namedEntity.themes.themes_classes.politicsSubclass.International;

public class FirstnameInternational extends Firstname implements International {

    public FirstnameInternational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public FirstnameInternational() {
        super();
        setCategory("Firstname");
        setTheme("International");
    }

}

