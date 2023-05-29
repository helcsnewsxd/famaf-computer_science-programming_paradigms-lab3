package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Lastname;
import namedEntity.themes.themes_classes.politicsSubclass.International;

public class LastnameInternational extends Lastname implements International {

    public LastnameInternational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public LastnameInternational() {
        super();
        setCategory("Lastname");
        setTheme("International");
    }

}

