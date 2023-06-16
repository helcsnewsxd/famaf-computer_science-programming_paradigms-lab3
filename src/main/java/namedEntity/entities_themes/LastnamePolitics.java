package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Lastname;
import namedEntity.themes.themes_classes.Politics;

public class LastnamePolitics extends Lastname implements Politics {

    public LastnamePolitics(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public LastnamePolitics() {
        super();
        setCategory("Lastname");
        setTheme("Politics");
    }

}

