package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.OtherEntity;
import namedEntity.themes.themes_classes.cultureSubclass.Music;

public class OtherEntityMusic extends OtherEntity implements Music, Serializable {

    public OtherEntityMusic(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OtherEntityMusic() {
        super();
        setCategory("OtherEntity");
        setTheme("Music");
    }

}

