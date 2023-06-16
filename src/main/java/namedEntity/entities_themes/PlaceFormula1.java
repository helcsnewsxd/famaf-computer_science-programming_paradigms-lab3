package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Place;
import namedEntity.themes.themes_classes.sportSubclass.Formula1;

public class PlaceFormula1 extends Place implements Formula1, Serializable {

    public PlaceFormula1(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PlaceFormula1() {
        super();
        setCategory("Place");
        setTheme("Formula1");
    }

}

