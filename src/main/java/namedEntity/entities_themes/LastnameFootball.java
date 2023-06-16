package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.personSubclass.Lastname;
import namedEntity.themes.themes_classes.sportSubclass.Football;

public class LastnameFootball extends Lastname implements Football, Serializable {

    public LastnameFootball(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public LastnameFootball() {
        super();
        setCategory("Lastname");
        setTheme("Football");
    }

}

