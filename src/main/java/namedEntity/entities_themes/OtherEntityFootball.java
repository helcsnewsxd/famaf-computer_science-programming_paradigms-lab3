package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.OtherEntity;
import namedEntity.themes.themes_classes.sportSubclass.Football;

public class OtherEntityFootball extends OtherEntity implements Football {

    public OtherEntityFootball(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OtherEntityFootball() {
        super();
        setCategory("OtherEntity");
        setTheme("Football");
    }

}

