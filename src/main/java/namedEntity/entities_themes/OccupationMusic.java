package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.personSubclass.Occupation;
import namedEntity.themes.themes_classes.cultureSubclass.Music;

public class OccupationMusic extends Occupation implements Music, Serializable {

    public OccupationMusic(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OccupationMusic() {
        super();
        setCategory("Occupation");
        setTheme("Music");
    }

}

