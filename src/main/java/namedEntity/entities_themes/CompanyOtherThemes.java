package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Company;
import namedEntity.themes.themes_classes.OtherThemes;

public class CompanyOtherThemes extends Company implements OtherThemes {

    public CompanyOtherThemes(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CompanyOtherThemes() {
        super();
        setCategory("Company");
        setTheme("OtherThemes");
    }

}

