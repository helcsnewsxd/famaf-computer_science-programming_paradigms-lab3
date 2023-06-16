package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.personSubclass.Occupation;
import namedEntity.themes.themes_classes.sportSubclass.Tennis;

public class OccupationTennis extends Occupation implements Tennis, Serializable {

    public OccupationTennis(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OccupationTennis() {
        super();
        setCategory("Occupation");
        setTheme("Tennis");
    }

}

