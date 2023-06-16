package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Date;
import namedEntity.themes.themes_classes.sportSubclass.Tennis;

public class DateTennis extends Date implements Tennis {

    public DateTennis(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public DateTennis() {
        super();
        setCategory("Date");
        setTheme("Tennis");
    }

}

