package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Event;
import namedEntity.themes.themes_classes.cultureSubclass.Music;

public class EventMusic extends Event implements Music, Serializable {

    public EventMusic(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public EventMusic() {
        super();
        setCategory("Event");
        setTheme("Music");
    }

}

