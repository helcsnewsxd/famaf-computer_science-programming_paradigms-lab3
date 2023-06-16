package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Event;
import namedEntity.themes.themes_classes.Culture;

public class EventCulture extends Event implements Culture, Serializable {

    public EventCulture(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public EventCulture() {
        super();
        setCategory("Event");
        setTheme("Culture");
    }

}

