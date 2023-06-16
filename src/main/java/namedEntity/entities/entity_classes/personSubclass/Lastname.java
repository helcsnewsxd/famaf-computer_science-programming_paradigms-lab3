package namedEntity.entities.entity_classes.personSubclass;

import namedEntity.entities.entity_classes.Person;

public class Lastname extends Person {

    private String origin;

    public Lastname(String name, String category, int frequency) {
        super(name, category, frequency);
        origin = "";
    }

    public Lastname() {
        super();
        origin = "";
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

}