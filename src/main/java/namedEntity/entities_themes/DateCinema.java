package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Date;
import namedEntity.themes.themes_classes.cultureSubclass.Cinema;

public class DateCinema extends Date implements Cinema {

    public DateCinema(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public DateCinema() {
        super();
        setCategory("Date");
        setTheme("Cinema");
    }

}

