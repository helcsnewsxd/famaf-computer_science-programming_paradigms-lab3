package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.Country;
import namedEntity.themes.themes_classes.sportSubclass.Formula1;

public class CountryFormula1 extends Country implements Formula1 {

    public CountryFormula1(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CountryFormula1() {
        super();
        setCategory("Country");
        setTheme("Formula1");
    }

}

