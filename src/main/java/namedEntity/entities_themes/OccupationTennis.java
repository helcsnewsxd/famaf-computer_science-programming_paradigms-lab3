package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Occupation;
import namedEntity.themes.themes_classes.sportSubclass.Tennis;

public class OccupationTennis extends Occupation implements Tennis {

    public OccupationTennis(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OccupationTennis() {
        super();
        setCategory("Occupation");
        setTheme("Tennis");
    }

}

