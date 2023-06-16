package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Product;
import namedEntity.themes.themes_classes.sportSubclass.Tennis;

public class ProductTennis extends Product implements Tennis {

    public ProductTennis(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public ProductTennis() {
        super();
        setCategory("Product");
        setTheme("Tennis");
    }

}

