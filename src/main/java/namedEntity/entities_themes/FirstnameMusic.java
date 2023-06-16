package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.personSubclass.Firstname;
import namedEntity.themes.themes_classes.cultureSubclass.Music;

public class FirstnameMusic extends Firstname implements Music, Serializable {

    public FirstnameMusic(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public FirstnameMusic() {
        super();
        setCategory("Firstname");
        setTheme("Music");
    }

}

