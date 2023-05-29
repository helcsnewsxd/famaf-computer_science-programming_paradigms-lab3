package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Place;
import namedEntity.themes.themes_classes.cultureSubclass.Music;

public class PlaceMusic extends Place implements Music {

    public PlaceMusic(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PlaceMusic() {
        super();
        setCategory("Place");
        setTheme("Music");
    }

}

