package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Company;
import namedEntity.themes.themes_classes.Sport;

public class CompanySport extends Company implements Sport {

    public CompanySport(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CompanySport() {
        super();
        setCategory("Company");
        setTheme("Sport");
    }

}

