package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Product;
import namedEntity.themes.themes_classes.OtherThemes;

public class ProductOtherThemes extends Product implements OtherThemes, Serializable {

    public ProductOtherThemes(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public ProductOtherThemes() {
        super();
        setCategory("Product");
        setTheme("OtherThemes");
    }

}

