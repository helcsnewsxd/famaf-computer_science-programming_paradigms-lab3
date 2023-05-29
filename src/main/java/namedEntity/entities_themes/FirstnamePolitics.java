package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Firstname;
import namedEntity.themes.themes_classes.Politics;

public class FirstnamePolitics extends Firstname implements Politics {

    public FirstnamePolitics(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public FirstnamePolitics() {
        super();
        setCategory("Firstname");
        setTheme("Politics");
    }

}

