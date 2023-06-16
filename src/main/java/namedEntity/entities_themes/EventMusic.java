package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Event;
import namedEntity.themes.themes_classes.cultureSubclass.Music;

public class EventMusic extends Event implements Music {

    public EventMusic(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public EventMusic() {
        super();
        setCategory("Event");
        setTheme("Music");
    }

}

