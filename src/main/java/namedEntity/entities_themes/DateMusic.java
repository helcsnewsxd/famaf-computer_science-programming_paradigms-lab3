package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Date;
import namedEntity.themes.themes_classes.cultureSubclass.Music;

public class DateMusic extends Date implements Music {

    public DateMusic(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public DateMusic() {
        super();
        setCategory("Date");
        setTheme("Music");
    }

}

