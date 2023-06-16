package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Company;
import namedEntity.themes.themes_classes.sportSubclass.Tennis;

public class CompanyTennis extends Company implements Tennis {

    public CompanyTennis(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CompanyTennis() {
        super();
        setCategory("Company");
        setTheme("Tennis");
    }

}

