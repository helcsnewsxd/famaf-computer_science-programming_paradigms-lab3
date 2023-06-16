package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Product;
import namedEntity.themes.themes_classes.sportSubclass.Football;

public class ProductFootball extends Product implements Football {

    public ProductFootball(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public ProductFootball() {
        super();
        setCategory("Product");
        setTheme("Football");
    }

}

