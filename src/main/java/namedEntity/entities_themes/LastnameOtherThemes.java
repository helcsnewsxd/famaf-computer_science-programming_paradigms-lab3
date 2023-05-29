package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Lastname;
import namedEntity.themes.themes_classes.OtherThemes;

public class LastnameOtherThemes extends Lastname implements OtherThemes {

    public LastnameOtherThemes(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public LastnameOtherThemes() {
        super();
        setCategory("Lastname");
        setTheme("OtherThemes");
    }

}

