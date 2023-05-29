package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Date;
import namedEntity.themes.themes_classes.sportSubclass.Formula1;

public class DateFormula1 extends Date implements Formula1 {

    public DateFormula1(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public DateFormula1() {
        super();
        setCategory("Date");
        setTheme("Formula1");
    }

}

