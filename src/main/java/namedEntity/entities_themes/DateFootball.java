package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Date;
import namedEntity.themes.themes_classes.sportSubclass.Football;

public class DateFootball extends Date implements Football, Serializable {

    public DateFootball(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public DateFootball() {
        super();
        setCategory("Date");
        setTheme("Football");
    }

}

