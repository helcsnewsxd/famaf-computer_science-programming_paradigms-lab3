package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.Company;
import namedEntity.themes.themes_classes.cultureSubclass.Music;

public class CompanyMusic extends Company implements Music, Serializable {

    public CompanyMusic(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CompanyMusic() {
        super();
        setCategory("Company");
        setTheme("Music");
    }

}

