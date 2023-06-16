package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Firstname;
import namedEntity.themes.themes_classes.sportSubclass.Formula1;

public class FirstnameFormula1 extends Firstname implements Formula1 {

    public FirstnameFormula1(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public FirstnameFormula1() {
        super();
        setCategory("Firstname");
        setTheme("Formula1");
    }

}

