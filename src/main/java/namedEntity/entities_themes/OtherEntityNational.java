package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.OtherEntity;
import namedEntity.themes.themes_classes.politicsSubclass.National;

public class OtherEntityNational extends OtherEntity implements National {

    public OtherEntityNational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OtherEntityNational() {
        super();
        setCategory("OtherEntity");
        setTheme("National");
    }

}

