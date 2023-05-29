package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Event;
import namedEntity.themes.themes_classes.sportSubclass.Football;

public class EventFootball extends Event implements Football {

    public EventFootball(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public EventFootball() {
        super();
        setCategory("Event");
        setTheme("Football");
    }

}

