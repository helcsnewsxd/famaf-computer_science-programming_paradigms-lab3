package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.placeSubclass.City;
import namedEntity.themes.themes_classes.sportSubclass.Basket;

public class CityBasket extends City implements Basket, Serializable {

    public CityBasket(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CityBasket() {
        super();
        setCategory("City");
        setTheme("Basket");
    }

}

