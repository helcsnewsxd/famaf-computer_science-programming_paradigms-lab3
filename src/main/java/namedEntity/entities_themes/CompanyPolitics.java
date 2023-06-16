package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Company;
import namedEntity.themes.themes_classes.Politics;

public class CompanyPolitics extends Company implements Politics, Serializable {

    public CompanyPolitics(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CompanyPolitics() {
        super();
        setCategory("Company");
        setTheme("Politics");
    }

}

