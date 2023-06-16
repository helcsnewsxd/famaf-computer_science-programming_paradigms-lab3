package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Company;
import namedEntity.themes.themes_classes.sportSubclass.Football;

public class CompanyFootball extends Company implements Football {

    public CompanyFootball(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CompanyFootball() {
        super();
        setCategory("Company");
        setTheme("Football");
    }

}

