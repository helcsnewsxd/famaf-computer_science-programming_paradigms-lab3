package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Lastname;
import namedEntity.themes.themes_classes.sportSubclass.Formula1;

public class LastnameFormula1 extends Lastname implements Formula1 {

    public LastnameFormula1(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public LastnameFormula1() {
        super();
        setCategory("Lastname");
        setTheme("Formula1");
    }

}

