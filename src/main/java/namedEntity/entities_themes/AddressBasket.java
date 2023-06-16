package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.placeSubclass.Address;
import namedEntity.themes.themes_classes.sportSubclass.Basket;

public class AddressBasket extends Address implements Basket, Serializable {

    public AddressBasket(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public AddressBasket() {
        super();
        setCategory("Address");
        setTheme("Basket");
    }

}

