package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Date;
import namedEntity.themes.themes_classes.Culture;

public class DateCulture extends Date implements Culture, Serializable {

    public DateCulture(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public DateCulture() {
        super();
        setCategory("Date");
        setTheme("Culture");
    }

}

