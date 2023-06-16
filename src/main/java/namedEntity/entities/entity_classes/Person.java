package namedEntity.entities.entity_classes;

import namedEntity.entities.NamedEntity;

public class Person extends NamedEntity {

    private String ID;

    public Person(String name, String category, int frequency) {
        super(name, category, frequency);
        ID = "";
    }

    public Person() {
        super();
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    
}
