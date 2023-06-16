package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Lastname;
import namedEntity.themes.themes_classes.Sport;

public class LastnameSport extends Lastname implements Sport {

    public LastnameSport(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public LastnameSport() {
        super();
        setCategory("Lastname");
        setTheme("Sport");
    }

}

