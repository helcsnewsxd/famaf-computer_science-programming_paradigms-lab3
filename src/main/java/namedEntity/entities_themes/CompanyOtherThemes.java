package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Company;
import namedEntity.themes.themes_classes.OtherThemes;

public class CompanyOtherThemes extends Company implements OtherThemes, Serializable {

    public CompanyOtherThemes(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CompanyOtherThemes() {
        super();
        setCategory("Company");
        setTheme("OtherThemes");
    }

}

