package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Event;
import namedEntity.themes.themes_classes.sportSubclass.Tennis;

public class EventTennis extends Event implements Tennis {

    public EventTennis(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public EventTennis() {
        super();
        setCategory("Event");
        setTheme("Tennis");
    }

}

