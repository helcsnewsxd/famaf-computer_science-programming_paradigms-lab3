package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Lastname;
import namedEntity.themes.themes_classes.sportSubclass.Basket;

public class LastnameBasket extends Lastname implements Basket {

    public LastnameBasket(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public LastnameBasket() {
        super();
        setCategory("Lastname");
        setTheme("Basket");
    }

}

