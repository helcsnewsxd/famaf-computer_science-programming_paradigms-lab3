package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.OtherEntity;
import namedEntity.themes.themes_classes.politicsSubclass.International;

public class OtherEntityInternational extends OtherEntity implements International {

    public OtherEntityInternational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OtherEntityInternational() {
        super();
        setCategory("OtherEntity");
        setTheme("International");
    }

}

