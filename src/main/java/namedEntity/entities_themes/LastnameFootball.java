package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Lastname;
import namedEntity.themes.themes_classes.sportSubclass.Football;

public class LastnameFootball extends Lastname implements Football {

    public LastnameFootball(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public LastnameFootball() {
        super();
        setCategory("Lastname");
        setTheme("Football");
    }

}

