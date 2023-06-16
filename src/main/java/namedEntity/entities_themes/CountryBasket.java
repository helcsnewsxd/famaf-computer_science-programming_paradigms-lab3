package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.Country;
import namedEntity.themes.themes_classes.sportSubclass.Basket;

public class CountryBasket extends Country implements Basket {

    public CountryBasket(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CountryBasket() {
        super();
        setCategory("Country");
        setTheme("Basket");
    }

}

