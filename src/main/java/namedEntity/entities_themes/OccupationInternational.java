package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Occupation;
import namedEntity.themes.themes_classes.politicsSubclass.International;

public class OccupationInternational extends Occupation implements International {

    public OccupationInternational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OccupationInternational() {
        super();
        setCategory("Occupation");
        setTheme("International");
    }

}

