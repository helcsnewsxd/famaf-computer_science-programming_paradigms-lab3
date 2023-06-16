package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Product;
import namedEntity.themes.themes_classes.sportSubclass.Basket;

public class ProductBasket extends Product implements Basket, Serializable {

    public ProductBasket(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public ProductBasket() {
        super();
        setCategory("Product");
        setTheme("Basket");
    }

}

