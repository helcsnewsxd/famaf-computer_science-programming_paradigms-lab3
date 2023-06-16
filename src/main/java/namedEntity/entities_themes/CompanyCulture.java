package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Company;
import namedEntity.themes.themes_classes.Culture;

public class CompanyCulture extends Company implements Culture, Serializable {

    public CompanyCulture(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CompanyCulture() {
        super();
        setCategory("Company");
        setTheme("Culture");
    }

}

