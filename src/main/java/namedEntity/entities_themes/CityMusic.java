package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.placeSubclass.City;
import namedEntity.themes.themes_classes.cultureSubclass.Music;

public class CityMusic extends City implements Music, Serializable {

    public CityMusic(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CityMusic() {
        super();
        setCategory("City");
        setTheme("Music");
    }

}

