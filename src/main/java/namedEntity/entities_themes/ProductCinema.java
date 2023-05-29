package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Product;
import namedEntity.themes.themes_classes.cultureSubclass.Cinema;

public class ProductCinema extends Product implements Cinema {

    public ProductCinema(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public ProductCinema() {
        super();
        setCategory("Product");
        setTheme("Cinema");
    }

}

