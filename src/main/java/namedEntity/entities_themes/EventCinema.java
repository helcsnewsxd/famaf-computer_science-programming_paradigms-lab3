package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Event;
import namedEntity.themes.themes_classes.cultureSubclass.Cinema;

public class EventCinema extends Event implements Cinema, Serializable {

    public EventCinema(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public EventCinema() {
        super();
        setCategory("Event");
        setTheme("Cinema");
    }

}

