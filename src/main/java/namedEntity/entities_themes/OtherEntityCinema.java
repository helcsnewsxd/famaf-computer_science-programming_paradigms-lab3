package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.OtherEntity;
import namedEntity.themes.themes_classes.cultureSubclass.Cinema;

public class OtherEntityCinema extends OtherEntity implements Cinema {

    public OtherEntityCinema(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OtherEntityCinema() {
        super();
        setCategory("OtherEntity");
        setTheme("Cinema");
    }

}

