package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Date;
import namedEntity.themes.themes_classes.Sport;

public class DateSport extends Date implements Sport, Serializable {

    public DateSport(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public DateSport() {
        super();
        setCategory("Date");
        setTheme("Sport");
    }

}

