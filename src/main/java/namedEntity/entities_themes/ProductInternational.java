package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Product;
import namedEntity.themes.themes_classes.politicsSubclass.International;

public class ProductInternational extends Product implements International {

    public ProductInternational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public ProductInternational() {
        super();
        setCategory("Product");
        setTheme("International");
    }

}

