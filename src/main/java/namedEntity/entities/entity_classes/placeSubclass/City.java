package namedEntity.entities.entity_classes.placeSubclass;

import namedEntity.entities.entity_classes.Place;

public class City extends Place {
    private String country;
    private String capital;
    private int population;

    public City(String name, String category, int frequency) {
        super(name, category, frequency);
        population = 0;
        country = "";
        capital = "";
    }

    public City() {
        super();
        population = 0;
        country = "";
        capital = "";
    }

    public int getPopulation() {
        return this.population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCapital() {
        return this.capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
    
}
