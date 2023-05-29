package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Product;
import namedEntity.themes.themes_classes.Politics;

public class ProductPolitics extends Product implements Politics {

    public ProductPolitics(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public ProductPolitics() {
        super();
        setCategory("Product");
        setTheme("Politics");
    }

}

