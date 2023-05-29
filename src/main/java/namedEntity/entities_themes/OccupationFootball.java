package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Occupation;
import namedEntity.themes.themes_classes.sportSubclass.Football;

public class OccupationFootball extends Occupation implements Football {

    public OccupationFootball(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OccupationFootball() {
        super();
        setCategory("Occupation");
        setTheme("Football");
    }

}

