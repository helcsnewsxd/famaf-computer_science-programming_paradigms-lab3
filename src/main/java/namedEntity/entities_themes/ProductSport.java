package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Product;
import namedEntity.themes.themes_classes.Sport;

public class ProductSport extends Product implements Sport {

    public ProductSport(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public ProductSport() {
        super();
        setCategory("Product");
        setTheme("Sport");
    }

}

