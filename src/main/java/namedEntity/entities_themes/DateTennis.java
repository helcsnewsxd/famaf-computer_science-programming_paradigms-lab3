package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Date;
import namedEntity.themes.themes_classes.sportSubclass.Tennis;

public class DateTennis extends Date implements Tennis, Serializable {

    public DateTennis(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public DateTennis() {
        super();
        setCategory("Date");
        setTheme("Tennis");
    }

}

