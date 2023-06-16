package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Occupation;
import namedEntity.themes.themes_classes.cultureSubclass.Cinema;

public class OccupationCinema extends Occupation implements Cinema {

    public OccupationCinema(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OccupationCinema() {
        super();
        setCategory("Occupation");
        setTheme("Cinema");
    }

}

