package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Product;
import namedEntity.themes.themes_classes.Sport;

public class ProductSport extends Product implements Sport, Serializable {

    public ProductSport(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public ProductSport() {
        super();
        setCategory("Product");
        setTheme("Sport");
    }

}

