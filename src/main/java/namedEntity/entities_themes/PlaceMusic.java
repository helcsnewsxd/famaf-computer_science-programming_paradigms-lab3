package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Place;
import namedEntity.themes.themes_classes.cultureSubclass.Music;

public class PlaceMusic extends Place implements Music, Serializable {

    public PlaceMusic(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PlaceMusic() {
        super();
        setCategory("Place");
        setTheme("Music");
    }

}

