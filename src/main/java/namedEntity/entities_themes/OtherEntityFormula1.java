package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.OtherEntity;
import namedEntity.themes.themes_classes.sportSubclass.Formula1;

public class OtherEntityFormula1 extends OtherEntity implements Formula1 {

    public OtherEntityFormula1(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OtherEntityFormula1() {
        super();
        setCategory("OtherEntity");
        setTheme("Formula1");
    }

}

