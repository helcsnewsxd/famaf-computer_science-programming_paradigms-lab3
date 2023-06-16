package namedEntity.entities.entity_classes;

import namedEntity.entities.NamedEntity;

public class Product extends NamedEntity {

    private String manufacturer;
    private String merchandiser;

    public Product(String name, String category, int frequency) {
        super(name, category, frequency);
        manufacturer = "";
    }

    public Product() {
        super();
        manufacturer = "";
    }
    
    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMerchandiser(){
        return this.merchandiser;
    }
    
    public void setMerchandiser(String merchandiser){
        this.merchandiser = merchandiser;
    }

}
