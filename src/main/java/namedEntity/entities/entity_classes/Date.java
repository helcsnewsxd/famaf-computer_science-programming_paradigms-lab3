package namedEntity.entities.entity_classes;

import namedEntity.entities.NamedEntity;

public class Date extends NamedEntity {

    private String date;

    public Date(String name, String category, int frequency) {
        super(name, category, frequency);
        date = "";
    }

    public Date() {
        super();
        date = "";
    }
    
    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
