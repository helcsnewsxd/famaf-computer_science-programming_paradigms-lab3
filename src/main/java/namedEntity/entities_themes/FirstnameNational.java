package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.personSubclass.Firstname;
import namedEntity.themes.themes_classes.politicsSubclass.National;

public class FirstnameNational extends Firstname implements National {

    public FirstnameNational(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public FirstnameNational() {
        super();
        setCategory("Firstname");
        setTheme("National");
    }

}

