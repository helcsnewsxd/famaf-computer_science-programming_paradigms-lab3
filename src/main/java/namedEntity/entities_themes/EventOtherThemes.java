package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Event;
import namedEntity.themes.themes_classes.OtherThemes;

public class EventOtherThemes extends Event implements OtherThemes {

    public EventOtherThemes(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public EventOtherThemes() {
        super();
        setCategory("Event");
        setTheme("OtherThemes");
    }

}

