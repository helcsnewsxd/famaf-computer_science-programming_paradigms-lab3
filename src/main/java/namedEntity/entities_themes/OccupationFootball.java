package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.personSubclass.Occupation;
import namedEntity.themes.themes_classes.sportSubclass.Football;

public class OccupationFootball extends Occupation implements Football, Serializable {

    public OccupationFootball(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OccupationFootball() {
        super();
        setCategory("Occupation");
        setTheme("Football");
    }

}

