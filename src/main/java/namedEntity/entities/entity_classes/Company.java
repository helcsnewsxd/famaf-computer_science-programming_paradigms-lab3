package namedEntity.entities.entity_classes;

import namedEntity.entities.NamedEntity;

public class Company extends NamedEntity {

    private int numberOfMembers;
    private String companyType;

    public Company(String name, String category, int frequency) {
        super(name, category, frequency);
        numberOfMembers = 0;
    }

    public Company() {
        super();
        numberOfMembers = 0;
    }
    
    public String getCompanyType(){
        return this.companyType;
    }
    
    public void setCompanyType(String companyType){
        this.companyType = companyType;
    }

    public int getNumberOfMembers() {
        return this.numberOfMembers;
    }

    public void setNumberOfMembers(int numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }

}
