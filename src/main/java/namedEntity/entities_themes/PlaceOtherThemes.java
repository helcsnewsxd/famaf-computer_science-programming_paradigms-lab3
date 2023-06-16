package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Place;
import namedEntity.themes.themes_classes.OtherThemes;

public class PlaceOtherThemes extends Place implements OtherThemes, Serializable {

    public PlaceOtherThemes(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PlaceOtherThemes() {
        super();
        setCategory("Place");
        setTheme("OtherThemes");
    }

}

