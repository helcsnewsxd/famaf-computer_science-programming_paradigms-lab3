package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.personSubclass.Firstname;
import namedEntity.themes.themes_classes.Sport;

public class FirstnameSport extends Firstname implements Sport, Serializable {

    public FirstnameSport(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public FirstnameSport() {
        super();
        setCategory("Firstname");
        setTheme("Sport");
    }

}

