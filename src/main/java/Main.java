import DAOHibernate.AdresDAOHibernate;
import DAOHibernate.OVChipkaartDAOHibernate;
import DAOHibernate.ProductDAOHibernate;
import DAOHibernate.ReizigerDAOHibernate;
import DAOInterfaces.AdresDAO;
import DAOInterfaces.OVChipkaartDAO;
import DAOInterfaces.ProductDAO;
import DAOInterfaces.ReizigerDAO;
import domain.Adres;
import domain.OVChipkaart;
import domain.Product;
import domain.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Testklasse - deze klasse test alle andere klassen in deze package.
 *
 * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
 *
 * @author tijmen.muller@hu.nl
 */
public class Main {
    // Creëer een factory voor Hibernate sessions.
    private static final SessionFactory factory;

    static {
        try {
            // Create a Hibernate session factory
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retouneer een Hibernate session.
     *
     * @return Hibernate session
     * @throws HibernateException
     */
    private static Session getSession() throws HibernateException {
        return factory.openSession();
    }

    public static void main(String[] args) throws SQLException {
//        testFetchAll();
        testDAOHibernate();
    }

    /**
     * P6. Haal alle (geannoteerde) entiteiten uit de database.
     */
    private static void testFetchAll() {
        Session session = getSession();
        try {
            Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                Query query = session.createQuery("from " + entityType.getName());

                System.out.println("[Test] Alle objecten van type " + entityType.getName() + " uit database:");
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
                System.out.println();
            }
        } finally {
            session.close();
        }
    }

    private static void testDAOHibernate(){
        Session session = getSession();

        ReizigerDAO rdao = new ReizigerDAOHibernate(session);
        AdresDAO adao = new AdresDAOHibernate(session);
        OVChipkaartDAO odao = new OVChipkaartDAOHibernate(session);
        ProductDAO pdao = new ProductDAOHibernate(session);

        adao.setRdao(rdao);
        odao.setRdao(rdao);

        testReizigerDAOHibernate(rdao);
        testAdresDAOHibernate(adao);
        testOVChipkaartDAOHibernate(odao);
        testProductDAOHibernate(pdao);

        session.close();
    }

    public static void testReizigerDAOHibernate(ReizigerDAO rdao){
        // Test ReizigerDAO.findAll()
        System.out.println("[Test] ReizigerDAO.findAll()");
        List<Reiziger> reizigers = rdao.findAll();
        for(Reiziger r : reizigers){
            System.out.println(r);
        }
        System.out.println();

        // Test ReizigerDAO.findByGbdatum()
        System.out.println("[Test] ReizigerDAO.findByGbdatum(2002-10-22)");
        List<Reiziger> reizigersGbdatum = rdao.findByGbdatum("2002-10-22");
        for(Reiziger r : reizigersGbdatum){
            System.out.println(r);
        }
        System.out.println();

        // Test ReizigerDAO.findById()
        System.out.println("[Test] ReizigerDAO.findById(1)");
        System.out.println(rdao.findById(1));
        System.out.println();

        // Test ReizigerDAO.save()
        System.out.println("[Test] ReizigerDAO.save()");
        System.out.println(String.format("ReizigerDAO.findAll() geeft %d resultaten", rdao.findAll().size()));
        Reiziger r1 = new Reiziger(30, "E", "", "Broekhuizen", Date.valueOf("2000-08-23"));
        rdao.save(r1);
        System.out.println(String.format("Na ReizigerDAO.save() %d resultaten", rdao.findAll().size()));
        System.out.println();

        // Test ReizigerDAO.update()
        System.out.println("[Test] ReizigerDAO.update()");
        System.out.println("Reiziger #30 gegevens vóór ReizigerDAO.update(): ");
        Reiziger target = rdao.findById(30);
        System.out.println(target);
        System.out.println();
        System.out.println("Reiziger #30 gegevens na ReizigerDAO.update(): ");
        target.setVoorletters("B");
        target.setTussenvoegsel("van der");
        target.setAchternaam("Sloot");
        target.setGeboortedatum(Date.valueOf("1998-03-12"));
        rdao.update(target);
        System.out.println(rdao.findById(30));
        System.out.println();

        // Test ReizigerDAO.delete()
        System.out.println("[Test] ReizigerDAO.delete()");
        System.out.println(String.format("ReizigerDAO.findAll() geeft %d resultaten", rdao.findAll().size()));
        rdao.delete(rdao.findById(30));
        System.out.println(String.format("Na ReizigerDAO.delete() %d resultaten", rdao.findAll().size()));
        System.out.println();
    }

    public static void testAdresDAOHibernate(AdresDAO adao){
        // Test AdresDAO.findAll()
        System.out.println("[Test] AdresDAO.findAll()");
        List<Adres> adressen = adao.findAll();
        for(Adres a : adressen){
            System.out.println(a);
        }
        System.out.println();

        // Test AdresDAO.findByReiziger()
        System.out.println("[Test] AdresDAO.findByReiziger(1)");
        System.out.println(adao.findByReiziger(adao.getRdao().findById(1)));
        System.out.println();

        // Test AdresDAO.save()
        System.out.println("[Test] AdresDAO.save()");
        System.out.println(String.format("AdresDAO.findAll() geeft %d resultaten", adao.findAll().size()));
        Reiziger reiziger = adao.getRdao().findById(20);
        Adres a1 = new Adres(reiziger.getId(), "3812EG", "55", "Groningerstraat", "Amersfoort", reiziger);
        adao.save(a1);
        System.out.println(String.format("Na AdresDAO.save() %d resultaten", adao.findAll().size()));
        System.out.println();

        // Test AdresDAO.update()
        System.out.println("[Test] AdresDAO.update()");
        System.out.println("Adres #20 gegevens vóór AdresDAO.update(): ");
        Adres target = adao.findByReiziger(adao.getRdao().findById(20));
        System.out.println(target);
        System.out.println();
        System.out.println("Adres #20 gegevens na ReizigerDAO.update(): ");
        target.setHuisnummer("80");
        target.setPostcode("3134GG");
        target.setStraat("Hogestraat");
        target.setWoonplaats("Soest");
        adao.update(target);
        System.out.println(adao.findByReiziger(adao.getRdao().findById(20)));
        System.out.println();

        // Test AdresDAO.delete()
        System.out.println("[Test] AdresDAO.delete()");
        System.out.println(String.format("AdresDAO.findAll() geeft %d resultaten", adao.findAll().size()));
        adao.delete(adao.findByReiziger(adao.getRdao().findById(20)));
        System.out.println(String.format("Na AdresDAO.delete() %d resultaten", adao.findAll().size()));
        System.out.println();
    }

    public static void testOVChipkaartDAOHibernate(OVChipkaartDAO odao){
        // Haal alle OVChipkaarten op uit de database
        List<OVChipkaart> kaarten = odao.findAll();
        System.out.println("[Test] OVChipkaartDAO.findAll() geeft de volgende kaarten:");
        for (OVChipkaart k : kaarten) {
            System.out.println(k);
        }
        System.out.println();

        // Maak een nieuwe OVChipkaart aan en persisteer deze in de database
        OVChipkaart ovkaart = new OVChipkaart(12345,Date.valueOf("2021-07-09"),2,250,odao.getRdao().findById(1));
        System.out.print("[Test] Eerst " + kaarten.size() + " ov-kaarten, na OVChipkaart.save() ");
        odao.save(ovkaart);
        kaarten = odao.findAll();
        System.out.println(kaarten.size() + " kaarten\n");

        // Update de eerder gemaakte ov-kaart
        System.out.println("[Test] Kaart gegevens voor update:\n"+ovkaart.toString()+"\n");
        ovkaart.setSaldo(999);
        ovkaart.setKlasse(1);
        ovkaart.setGeldig_tot(Date.valueOf("2077-02-04"));
        odao.update(ovkaart);
        System.out.println("Kaart gegevens na update:\n"+ovkaart.toString()+"\n");

        // Verwijder eerder gemaakte adres uit de database
        System.out.print("[Test] Eerst " + kaarten.size() + " kaarten, na OVChipkaart.delete() ");
        odao.delete(ovkaart);
        kaarten = odao.findAll();
        System.out.println(kaarten.size() + " kaarten\n");
    }

    public static void testProductDAOHibernate(ProductDAO pdao){
        // Haal alle Producten op uit de database
        List<Product> producten = pdao.findAll();
        System.out.println("[Test] ProductDAO.findAll() geeft de volgende producten:");
        for (Product p : producten) {
            System.out.println(p);
        }
        System.out.println();

        // Maak een nieuw Product aan en persisteer deze in de database
        System.out.print("[Test] Eerst " + producten.size() + " producten, na ProductDAO.save() ");
        Product p1 = new Product(7,"Senioren","Gratis of met korting reizen voor 60-plussers",5.00);
        pdao.save(p1);
        producten = pdao.findAll();
        System.out.println(producten.size() + " producten\n");

        // Update het eerder gemaakte product
        System.out.println("[Test] Product gegevens voor update:\n"+p1.toString()+"\n");
        p1.setNaam("Oudjes");
        p1.setBeschrijving("voor ouderen");
        p1.setPrijs(10.00);
        pdao.update(p1);
        System.out.println("Kaart gegevens na update:\n"+p1.toString()+"\n");

        // Verwijder eerder gemaakt product
        System.out.print("[Test] Eerst " + producten.size() + " producten, na ProductDAO.delete() ");
        pdao.delete(p1);
        producten = pdao.findAll();
        System.out.println(producten.size() + " producten\n");
    }
}