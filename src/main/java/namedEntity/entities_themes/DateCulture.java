package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Date;
import namedEntity.themes.themes_classes.Culture;

public class DateCulture extends Date implements Culture {

    public DateCulture(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public DateCulture() {
        super();
        setCategory("Date");
        setTheme("Culture");
    }

}

