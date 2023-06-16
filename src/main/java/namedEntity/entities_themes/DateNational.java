package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Date;
import namedEntity.themes.themes_classes.politicsSubclass.National;

public class DateNational extends Date implements National, Serializable {

    public DateNational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public DateNational() {
        super();
        setCategory("Date");
        setTheme("National");
    }

}

