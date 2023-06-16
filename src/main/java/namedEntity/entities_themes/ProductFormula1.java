package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Product;
import namedEntity.themes.themes_classes.sportSubclass.Formula1;

public class ProductFormula1 extends Product implements Formula1, Serializable {

    public ProductFormula1(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public ProductFormula1() {
        super();
        setCategory("Product");
        setTheme("Formula1");
    }

}

