package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Company;
import namedEntity.themes.themes_classes.Culture;

public class CompanyCulture extends Company implements Culture {

    public CompanyCulture(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CompanyCulture() {
        super();
        setCategory("Company");
        setTheme("Culture");
    }

}

