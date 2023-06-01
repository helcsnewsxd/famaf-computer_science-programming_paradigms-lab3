package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Occupation;
import namedEntity.themes.themes_classes.Sport;

public class OccupationSport extends Occupation implements Sport {

    public OccupationSport(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OccupationSport() {
        super();
        setCategory("Occupation");
        setTheme("Sport");
    }

}

