package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Product;
import namedEntity.themes.themes_classes.Culture;

public class ProductCulture extends Product implements Culture {

    public ProductCulture(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public ProductCulture() {
        super();
        setCategory("Product");
        setTheme("Culture");
    }

}

