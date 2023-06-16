package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.personSubclass.Lastname;
import namedEntity.themes.themes_classes.cultureSubclass.Music;

public class LastnameMusic extends Lastname implements Music, Serializable {

    public LastnameMusic(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public LastnameMusic() {
        super();
        setCategory("Lastname");
        setTheme("Music");
    }

}

