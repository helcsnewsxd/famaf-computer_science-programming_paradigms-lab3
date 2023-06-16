package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Date;
import namedEntity.themes.themes_classes.sportSubclass.Basket;

public class DateBasket extends Date implements Basket, Serializable {

    public DateBasket(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public DateBasket() {
        super();
        setCategory("Date");
        setTheme("Basket");
    }

}

