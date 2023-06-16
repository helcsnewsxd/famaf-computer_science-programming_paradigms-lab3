package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.personSubclass.Lastname;
import namedEntity.themes.themes_classes.sportSubclass.Tennis;

public class LastnameTennis extends Lastname implements Tennis, Serializable {

    public LastnameTennis(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public LastnameTennis() {
        super();
        setCategory("Lastname");
        setTheme("Tennis");
    }

}

