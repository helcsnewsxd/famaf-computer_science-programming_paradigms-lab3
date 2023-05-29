package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Firstname;
import namedEntity.themes.themes_classes.cultureSubclass.Music;

public class FirstnameMusic extends Firstname implements Music {

    public FirstnameMusic(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public FirstnameMusic() {
        super();
        setCategory("Firstname");
        setTheme("Music");
    }

}

