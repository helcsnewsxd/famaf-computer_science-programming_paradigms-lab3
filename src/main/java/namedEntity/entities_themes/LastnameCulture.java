package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.personSubclass.Lastname;
import namedEntity.themes.themes_classes.Culture;

public class LastnameCulture extends Lastname implements Culture, Serializable {

    public LastnameCulture(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public LastnameCulture() {
        super();
        setCategory("Lastname");
        setTheme("Culture");
    }

}

