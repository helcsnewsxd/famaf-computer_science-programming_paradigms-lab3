package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.OtherEntity;
import namedEntity.themes.themes_classes.sportSubclass.Basket;

public class OtherEntityBasket extends OtherEntity implements Basket {

    public OtherEntityBasket(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OtherEntityBasket() {
        super();
        setCategory("OtherEntity");
        setTheme("Basket");
    }

}

