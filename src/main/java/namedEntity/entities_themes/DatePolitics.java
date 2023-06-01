package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Date;
import namedEntity.themes.themes_classes.Politics;

public class DatePolitics extends Date implements Politics {

    public DatePolitics(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public DatePolitics() {
        super();
        setCategory("Date");
        setTheme("Politics");
    }

}

