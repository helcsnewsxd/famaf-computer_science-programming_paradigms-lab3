package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Occupation;
import namedEntity.themes.themes_classes.Politics;

public class OccupationPolitics extends Occupation implements Politics {

    public OccupationPolitics(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OccupationPolitics() {
        super();
        setCategory("Occupation");
        setTheme("Politics");
    }

}

