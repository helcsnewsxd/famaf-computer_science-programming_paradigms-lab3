package namedEntity.entities_themes;

import java.io.Serializable;

import namedEntity.entities.entity_classes.OtherEntity;
import namedEntity.themes.themes_classes.sportSubclass.Tennis;

public class OtherEntityTennis extends OtherEntity implements Tennis, Serializable {

    public OtherEntityTennis(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OtherEntityTennis() {
        super();
        setCategory("OtherEntity");
        setTheme("Tennis");
    }

}

