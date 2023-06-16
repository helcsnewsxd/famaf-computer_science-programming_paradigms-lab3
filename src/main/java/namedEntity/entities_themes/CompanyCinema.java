package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Company;
import namedEntity.themes.themes_classes.cultureSubclass.Cinema;

public class CompanyCinema extends Company implements Cinema, Serializable {

    public CompanyCinema(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CompanyCinema() {
        super();
        setCategory("Company");
        setTheme("Cinema");
    }

}

