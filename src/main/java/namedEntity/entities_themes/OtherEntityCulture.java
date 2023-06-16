package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.OtherEntity;
import namedEntity.themes.themes_classes.Culture;

public class OtherEntityCulture extends OtherEntity implements Culture, Serializable {

    public OtherEntityCulture(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OtherEntityCulture() {
        super();
        setCategory("OtherEntity");
        setTheme("Culture");
    }

}

