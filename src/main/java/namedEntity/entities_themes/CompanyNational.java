package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Company;
import namedEntity.themes.themes_classes.politicsSubclass.National;

public class CompanyNational extends Company implements National {

    public CompanyNational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CompanyNational() {
        super();
        setCategory("Company");
        setTheme("National");
    }

}

