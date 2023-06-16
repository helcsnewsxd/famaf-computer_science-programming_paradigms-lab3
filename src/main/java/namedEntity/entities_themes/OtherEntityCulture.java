package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.OtherEntity;
import namedEntity.themes.themes_classes.Culture;

public class OtherEntityCulture extends OtherEntity implements Culture {

    public OtherEntityCulture(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OtherEntityCulture() {
        super();
        setCategory("OtherEntity");
        setTheme("Culture");
    }

}

