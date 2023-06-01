package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.Company;
import namedEntity.themes.themes_classes.cultureSubclass.Music;

public class CompanyMusic extends Company implements Music {

    public CompanyMusic(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public CompanyMusic() {
        super();
        setCategory("Company");
        setTheme("Music");
    }

}

