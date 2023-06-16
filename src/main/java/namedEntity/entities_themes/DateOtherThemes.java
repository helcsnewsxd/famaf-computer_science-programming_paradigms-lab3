package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Date;
import namedEntity.themes.themes_classes.OtherThemes;

public class DateOtherThemes extends Date implements OtherThemes, Serializable {

    public DateOtherThemes(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public DateOtherThemes() {
        super();
        setCategory("Date");
        setTheme("OtherThemes");
    }

}

