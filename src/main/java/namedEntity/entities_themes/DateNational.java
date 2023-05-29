package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Date;
import namedEntity.themes.themes_classes.politicsSubclass.National;

public class DateNational extends Date implements National {

    public DateNational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public DateNational() {
        super();
        setCategory("Date");
        setTheme("National");
    }

}

