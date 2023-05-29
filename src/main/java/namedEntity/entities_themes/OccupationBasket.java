package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Occupation;
import namedEntity.themes.themes_classes.sportSubclass.Basket;

public class OccupationBasket extends Occupation implements Basket {

    public OccupationBasket(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OccupationBasket() {
        super();
        setCategory("Occupation");
        setTheme("Basket");
    }

}

