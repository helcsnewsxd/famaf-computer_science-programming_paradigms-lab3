package namedEntity.entities.entity_classes.personSubclass;

import namedEntity.entities.entity_classes.Person;

public class Occupation extends Person {
    public Occupation(String name, String category, int frequency) {
        super(name, category, frequency);
    }

    public Occupation() {
        super();
    }
}
