package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Event;
import namedEntity.themes.themes_classes.politicsSubclass.National;

public class EventNational extends Event implements National {

    public EventNational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public EventNational() {
        super();
        setCategory("Event");
        setTheme("National");
    }

}

