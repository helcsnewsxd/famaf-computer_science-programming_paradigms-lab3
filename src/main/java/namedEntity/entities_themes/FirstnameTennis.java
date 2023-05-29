package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Firstname;
import namedEntity.themes.themes_classes.sportSubclass.Tennis;

public class FirstnameTennis extends Firstname implements Tennis {

    public FirstnameTennis(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public FirstnameTennis() {
        super();
        setCategory("Firstname");
        setTheme("Tennis");
    }

}

