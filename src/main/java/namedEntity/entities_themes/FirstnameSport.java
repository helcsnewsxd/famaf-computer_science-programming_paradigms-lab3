package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Firstname;
import namedEntity.themes.themes_classes.Sport;

public class FirstnameSport extends Firstname implements Sport {

    public FirstnameSport(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public FirstnameSport() {
        super();
        setCategory("Firstname");
        setTheme("Sport");
    }

}

