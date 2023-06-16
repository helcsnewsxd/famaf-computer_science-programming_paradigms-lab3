package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Person;
import namedEntity.themes.themes_classes.sportSubclass.Basket;

public class PersonBasket extends Person implements Basket, Serializable {

    public PersonBasket(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PersonBasket() {
        super();
        setCategory("Person");
        setTheme("Basket");
    }

}

