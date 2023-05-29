package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Lastname;
import namedEntity.themes.themes_classes.sportSubclass.Tennis;

public class LastnameTennis extends Lastname implements Tennis {

    public LastnameTennis(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public LastnameTennis() {
        super();
        setCategory("Lastname");
        setTheme("Tennis");
    }

}

