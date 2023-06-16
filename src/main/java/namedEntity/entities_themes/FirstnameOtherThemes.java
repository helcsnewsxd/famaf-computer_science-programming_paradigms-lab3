package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.personSubclass.Firstname;
import namedEntity.themes.themes_classes.OtherThemes;

public class FirstnameOtherThemes extends Firstname implements OtherThemes, Serializable {

    public FirstnameOtherThemes(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public FirstnameOtherThemes() {
        super();
        setCategory("Firstname");
        setTheme("OtherThemes");
    }

}

