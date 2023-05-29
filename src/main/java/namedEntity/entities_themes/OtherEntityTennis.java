package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.OtherEntity;
import namedEntity.themes.themes_classes.sportSubclass.Tennis;

public class OtherEntityTennis extends OtherEntity implements Tennis {

    public OtherEntityTennis(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OtherEntityTennis() {
        super();
        setCategory("OtherEntity");
        setTheme("Tennis");
    }

}

