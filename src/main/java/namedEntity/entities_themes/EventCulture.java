package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Event;
import namedEntity.themes.themes_classes.Culture;

public class EventCulture extends Event implements Culture {

    public EventCulture(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public EventCulture() {
        super();
        setCategory("Event");
        setTheme("Culture");
    }

}

