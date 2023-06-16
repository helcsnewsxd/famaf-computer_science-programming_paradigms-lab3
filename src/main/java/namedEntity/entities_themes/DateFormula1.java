package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Date;
import namedEntity.themes.themes_classes.sportSubclass.Formula1;

public class DateFormula1 extends Date implements Formula1, Serializable {

    public DateFormula1(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public DateFormula1() {
        super();
        setCategory("Date");
        setTheme("Formula1");
    }

}

