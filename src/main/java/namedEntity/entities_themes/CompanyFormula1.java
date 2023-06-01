package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Company;
import namedEntity.themes.themes_classes.sportSubclass.Formula1;

public class CompanyFormula1 extends Company implements Formula1 {

    public CompanyFormula1(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CompanyFormula1() {
        super();
        setCategory("Company");
        setTheme("Formula1");
    }

}

