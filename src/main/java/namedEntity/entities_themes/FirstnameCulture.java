package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Firstname;
import namedEntity.themes.themes_classes.Culture;

public class FirstnameCulture extends Firstname implements Culture {

    public FirstnameCulture(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public FirstnameCulture() {
        super();
        setCategory("Firstname");
        setTheme("Culture");
    }

}

