package namedEntity.entities.entity_classes.personSubclass;

import namedEntity.entities.entity_classes.Person;

public class Firstname extends Person {
    private String origin;
    private String alternativeForms;

    public Firstname(String name, String category, int frequency) {
        super(name, category, frequency);
        origin = alternativeForms = "";
    }

    public Firstname() {
        super();
        origin = alternativeForms = "";
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getAlternativeForms() {
        return this.alternativeForms;
    }

    public void setAlternativeForms(String alternativeForms) {
        this.alternativeForms = alternativeForms;
    }
}
