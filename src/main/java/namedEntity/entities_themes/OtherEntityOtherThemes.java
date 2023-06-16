package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.OtherEntity;
import namedEntity.themes.themes_classes.OtherThemes;

public class OtherEntityOtherThemes extends OtherEntity implements OtherThemes {

    public OtherEntityOtherThemes(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OtherEntityOtherThemes() {
        super();
        setCategory("OtherEntity");
        setTheme("OtherThemes");
    }

}

