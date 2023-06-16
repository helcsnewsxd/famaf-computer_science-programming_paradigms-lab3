package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Company;
import namedEntity.themes.themes_classes.sportSubclass.Basket;

public class CompanyBasket extends Company implements Basket, Serializable {

    public CompanyBasket(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CompanyBasket() {
        super();
        setCategory("Company");
        setTheme("Basket");
    }

}

