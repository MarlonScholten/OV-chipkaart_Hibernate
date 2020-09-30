package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    private int product_nummer;
    private String naam;
    private String beschrijving;
    private double prijs;
    @ManyToMany(mappedBy = "producten")
    private List<OVChipkaart> ovkaarten = new ArrayList<>();

    public Product(int product_nummer, String naam, String beschrijving, double prijs) {
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public Product(int product_nummer, String naam, String beschrijving, double prijs, List<OVChipkaart> ovkaarten) {
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        this.ovkaarten = ovkaarten;
    }

    public Product(){

    }

    public int getProduct_nummer() {
        return product_nummer;
    }

    public void setProduct_nummer(int product_nummer) {
        this.product_nummer = product_nummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public List<OVChipkaart> getOvkaarten() {
        return ovkaarten;
    }

    public boolean addOVkaart(OVChipkaart ovkaart){
        if(!this.getOvkaarten().contains(ovkaart)){
            this.getOvkaarten().add(ovkaart);
            return true;
        } else{
            return false;
        }
    }

    public boolean removeOVkaart(OVChipkaart ovkaart){
        if(this.getOvkaarten().contains(ovkaart)){
            this.getOvkaarten().remove(ovkaart);
            return true;
        } else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_nummer=" + product_nummer +
                ", naam='" + naam + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", prijs=" + prijs +
                '}';
    }
}
