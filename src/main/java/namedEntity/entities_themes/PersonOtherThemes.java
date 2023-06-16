package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Person;
import namedEntity.themes.themes_classes.OtherThemes;

public class PersonOtherThemes extends Person implements OtherThemes, Serializable {

    public PersonOtherThemes(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PersonOtherThemes() {
        super();
        setCategory("Person");
        setTheme("OtherThemes");
    }

}

