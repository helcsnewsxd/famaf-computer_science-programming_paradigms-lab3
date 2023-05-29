package namedEntity.entities.entity_classes.placeSubclass;

import namedEntity.entities.entity_classes.Place;

public class Country extends Place {
    private int population;
    private String language;

    public Country(String name, String category, int frequency) {
        super(name, category, frequency);
        population = 0;
        language = "";
    }

    public Country() {
        super();
        population = 0;
        language = "";
    }

    public int getPopulation() {
        return this.population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
