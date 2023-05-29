package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.City;
import namedEntity.themes.themes_classes.sportSubclass.Formula1;

public class CityFormula1 extends City implements Formula1 {

    public CityFormula1(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CityFormula1() {
        super();
        setCategory("City");
        setTheme("Formula1");
    }

}

