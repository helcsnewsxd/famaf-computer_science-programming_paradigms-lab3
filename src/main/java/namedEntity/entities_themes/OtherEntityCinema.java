package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.OtherEntity;
import namedEntity.themes.themes_classes.cultureSubclass.Cinema;

public class OtherEntityCinema extends OtherEntity implements Cinema, Serializable {

    public OtherEntityCinema(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OtherEntityCinema() {
        super();
        setCategory("OtherEntity");
        setTheme("Cinema");
    }

}

