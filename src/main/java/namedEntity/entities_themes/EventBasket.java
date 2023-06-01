package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Event;
import namedEntity.themes.themes_classes.sportSubclass.Basket;

public class EventBasket extends Event implements Basket {

    public EventBasket(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public EventBasket() {
        super();
        setCategory("Event");
        setTheme("Basket");
    }

}

