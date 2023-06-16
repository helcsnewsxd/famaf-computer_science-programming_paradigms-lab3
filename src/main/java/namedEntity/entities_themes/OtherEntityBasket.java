package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.OtherEntity;
import namedEntity.themes.themes_classes.sportSubclass.Basket;

public class OtherEntityBasket extends OtherEntity implements Basket, Serializable {

    public OtherEntityBasket(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OtherEntityBasket() {
        super();
        setCategory("OtherEntity");
        setTheme("Basket");
    }

}

