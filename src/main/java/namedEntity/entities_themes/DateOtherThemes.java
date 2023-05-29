package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Date;
import namedEntity.themes.themes_classes.OtherThemes;

public class DateOtherThemes extends Date implements OtherThemes {

    public DateOtherThemes(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public DateOtherThemes() {
        super();
        setCategory("Date");
        setTheme("OtherThemes");
    }

}

