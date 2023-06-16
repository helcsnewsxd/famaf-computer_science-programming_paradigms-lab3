package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Event;
import namedEntity.themes.themes_classes.Politics;

public class EventPolitics extends Event implements Politics {

    public EventPolitics(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public EventPolitics() {
        super();
        setCategory("Event");
        setTheme("Politics");
    }

}

