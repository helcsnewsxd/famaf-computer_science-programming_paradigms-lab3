package namedEntity.entities.entity_classes.placeSubclass;

import namedEntity.entities.entity_classes.Place;

public class Address extends Place {
    private String city;

    public Address(String name, String category, int frequency) {
        super(name, category, frequency);
        city = "";
    }

    public Address() {
        super();
        city = "";
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
