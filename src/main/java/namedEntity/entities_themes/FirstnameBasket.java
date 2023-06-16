package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.personSubclass.Firstname;
import namedEntity.themes.themes_classes.sportSubclass.Basket;

public class FirstnameBasket extends Firstname implements Basket, Serializable {

    public FirstnameBasket(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public FirstnameBasket() {
        super();
        setCategory("Firstname");
        setTheme("Basket");
    }

}

