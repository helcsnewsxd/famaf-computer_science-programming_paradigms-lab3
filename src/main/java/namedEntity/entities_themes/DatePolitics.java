package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Date;
import namedEntity.themes.themes_classes.Politics;

public class DatePolitics extends Date implements Politics, Serializable {

    public DatePolitics(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public DatePolitics() {
        super();
        setCategory("Date");
        setTheme("Politics");
    }

}

