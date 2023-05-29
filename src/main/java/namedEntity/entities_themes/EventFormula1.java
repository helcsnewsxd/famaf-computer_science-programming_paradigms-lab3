package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Event;
import namedEntity.themes.themes_classes.sportSubclass.Formula1;

public class EventFormula1 extends Event implements Formula1 {

    public EventFormula1(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public EventFormula1() {
        super();
        setCategory("Event");
        setTheme("Formula1");
    }

}

