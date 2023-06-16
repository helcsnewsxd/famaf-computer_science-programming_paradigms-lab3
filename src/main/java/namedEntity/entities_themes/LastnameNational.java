package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Lastname;
import namedEntity.themes.themes_classes.politicsSubclass.National;

public class LastnameNational extends Lastname implements National {

    public LastnameNational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public LastnameNational() {
        super();
        setCategory("Lastname");
        setTheme("National");
    }

}

