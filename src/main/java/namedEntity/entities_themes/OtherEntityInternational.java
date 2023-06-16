package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.OtherEntity;
import namedEntity.themes.themes_classes.politicsSubclass.International;

public class OtherEntityInternational extends OtherEntity implements International, Serializable {

    public OtherEntityInternational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OtherEntityInternational() {
        super();
        setCategory("OtherEntity");
        setTheme("International");
    }

}

