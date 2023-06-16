package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Occupation;
import namedEntity.themes.themes_classes.OtherThemes;

public class OccupationOtherThemes extends Occupation implements OtherThemes {

    public OccupationOtherThemes(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public OccupationOtherThemes() {
        super();
        setCategory("Occupation");
        setTheme("OtherThemes");
    }

}

