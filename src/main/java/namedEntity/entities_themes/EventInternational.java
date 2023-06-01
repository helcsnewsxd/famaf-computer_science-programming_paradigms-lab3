package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Event;
import namedEntity.themes.themes_classes.politicsSubclass.International;

public class EventInternational extends Event implements International {

    public EventInternational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public EventInternational() {
        super();
        setCategory("Event");
        setTheme("International");
    }

}

