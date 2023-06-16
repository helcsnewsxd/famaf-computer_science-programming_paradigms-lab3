package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Date;
import namedEntity.themes.themes_classes.politicsSubclass.International;

public class DateInternational extends Date implements International, Serializable {

    public DateInternational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public DateInternational() {
        super();
        setCategory("Date");
        setTheme("International");
    }

}

