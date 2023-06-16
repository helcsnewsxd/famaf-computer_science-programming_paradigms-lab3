package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Person;
import namedEntity.themes.themes_classes.sportSubclass.Formula1;

public class PersonFormula1 extends Person implements Formula1, Serializable {

    public PersonFormula1(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PersonFormula1() {
        super();
        setCategory("Person");
        setTheme("Formula1");
    }

}

