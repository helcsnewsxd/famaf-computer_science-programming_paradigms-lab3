package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.placeSubclass.Country;
import namedEntity.themes.themes_classes.sportSubclass.Basket;

public class CountryBasket extends Country implements Basket, Serializable {

    public CountryBasket(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CountryBasket() {
        super();
        setCategory("Country");
        setTheme("Basket");
    }

}

