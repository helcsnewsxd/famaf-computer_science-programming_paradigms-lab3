package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.personSubclass.Lastname;
import namedEntity.themes.themes_classes.Politics;

public class LastnamePolitics extends Lastname implements Politics, Serializable {

    public LastnamePolitics(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public LastnamePolitics() {
        super();
        setCategory("Lastname");
        setTheme("Politics");
    }

}

