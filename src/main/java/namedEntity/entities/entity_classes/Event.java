package namedEntity.entities.entity_classes;

import namedEntity.entities.NamedEntity;

public class Event extends NamedEntity {

    private String date;
    private Boolean recurrent;

    public Event(String name, String category, int frequency) {
        super(name, category, frequency);
        date = "";
        recurrent = true;
    }

    public Event() {
        super();
        date = "";
        recurrent = false;
    }
    
    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getRecurrent() {
        return this.recurrent;
    }

    public void setRecurrent(Boolean recurrent) {
        this.recurrent = recurrent;
    }
    
}
