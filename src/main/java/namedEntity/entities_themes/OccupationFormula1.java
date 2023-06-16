package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Occupation;
import namedEntity.themes.themes_classes.sportSubclass.Formula1;

public class OccupationFormula1 extends Occupation implements Formula1 {

    public OccupationFormula1(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OccupationFormula1() {
        super();
        setCategory("Occupation");
        setTheme("Formula1");
    }

}

