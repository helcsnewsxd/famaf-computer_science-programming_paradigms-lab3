package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Occupation;
import namedEntity.themes.themes_classes.politicsSubclass.National;

public class OccupationNational extends Occupation implements National {

    public OccupationNational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OccupationNational() {
        super();
        setCategory("Occupation");
        setTheme("National");
    }

}

