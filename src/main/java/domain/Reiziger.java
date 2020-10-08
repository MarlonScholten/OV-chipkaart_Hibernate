package domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Reiziger {
    @Id
    @Column(name="reiziger_id")
    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;
    @OneToOne(mappedBy = "reiziger")
    private Adres adres;
    @OneToMany(mappedBy = "reiziger")
    private List<OVChipkaart> chipKaarten;

    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel.equals("") ? null : tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    // Met chipkaarten
    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum, Adres adres, List<OVChipkaart> chipKaarten) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        this.adres = adres;
        this.chipKaarten = chipKaarten;
    }

    public Reiziger() {
        this.voorletters = null;
        this.tussenvoegsel = null;
        this.achternaam = null;
        this.geboortedatum = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getNaam(){
        return String.format("%s %s %s", getVoorletters(),getTussenvoegsel(),getAchternaam());
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public List<OVChipkaart> getChipKaarten() {
        return chipKaarten;
    }

    public void setChipKaarten(List<OVChipkaart> chipKaarten) {
        this.chipKaarten = chipKaarten;
    }

    @Override
    public String toString() {
        String adresString = (this.getAdres() == null) ? "Adres{}":String.format("Adres{#%d %s %s %s %s}",adres.getId(),adres.getPostcode(),adres.getHuisnummer(),adres.getStraat(),adres.getWoonplaats());
        return "Reiziger{" +
                "#" + id +
                " " + voorletters +
                " " + tussenvoegsel +
                " " + achternaam +
                ", geb. " + geboortedatum +
                ", "+ adresString +
                ", Chipkaarten{"+ chipKaarten +
                "}}";
    }
}
