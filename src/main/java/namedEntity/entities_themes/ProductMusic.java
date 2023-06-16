package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Product;
import namedEntity.themes.themes_classes.cultureSubclass.Music;

public class ProductMusic extends Product implements Music {

    public ProductMusic(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public ProductMusic() {
        super();
        setCategory("Product");
        setTheme("Music");
    }

}

