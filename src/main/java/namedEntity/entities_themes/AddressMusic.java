package namedEntity.entities_themes;

import namedEntity.entities.entity_classes.placeSubclass.Address;
import namedEntity.themes.themes_classes.cultureSubclass.Music;

public class AddressMusic extends Address implements Music {

    public AddressMusic(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public AddressMusic() {
        super();
        setCategory("Address");
        setTheme("Music");
    }

}

