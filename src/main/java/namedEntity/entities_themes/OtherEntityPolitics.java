package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.OtherEntity;
import namedEntity.themes.themes_classes.Politics;

public class OtherEntityPolitics extends OtherEntity implements Politics {

    public OtherEntityPolitics(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OtherEntityPolitics() {
        super();
        setCategory("OtherEntity");
        setTheme("Politics");
    }

}

