package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Firstname;
import namedEntity.themes.themes_classes.OtherThemes;

public class FirstnameOtherThemes extends Firstname implements OtherThemes {

    public FirstnameOtherThemes(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public FirstnameOtherThemes() {
        super();
        setCategory("Firstname");
        setTheme("OtherThemes");
    }

}

