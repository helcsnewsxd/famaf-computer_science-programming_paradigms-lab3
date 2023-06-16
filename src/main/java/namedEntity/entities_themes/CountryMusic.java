package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.placeSubclass.Country;
import namedEntity.themes.themes_classes.cultureSubclass.Music;

public class CountryMusic extends Country implements Music, Serializable {

    public CountryMusic(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CountryMusic() {
        super();
        setCategory("Country");
        setTheme("Music");
    }

}

