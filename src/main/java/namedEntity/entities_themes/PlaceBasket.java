package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Place;
import namedEntity.themes.themes_classes.sportSubclass.Basket;

public class PlaceBasket extends Place implements Basket {

    public PlaceBasket(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public PlaceBasket() {
        super();
        setCategory("Place");
        setTheme("Basket");
    }

}

