package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.personSubclass.Firstname;
import namedEntity.themes.themes_classes.sportSubclass.Football;

public class FirstnameFootball extends Firstname implements Football, Serializable {

    public FirstnameFootball(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public FirstnameFootball() {
        super();
        setCategory("Firstname");
        setTheme("Football");
    }

}

