package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Person;
import namedEntity.themes.themes_classes.cultureSubclass.Music;

public class PersonMusic extends Person implements Music {

    public PersonMusic(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PersonMusic() {
        super();
        setCategory("Person");
        setTheme("Music");
    }

}

