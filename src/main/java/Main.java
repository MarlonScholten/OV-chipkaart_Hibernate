import DAOHibernate.ReizigerDAOHibernate;
import DAOInterfaces.ReizigerDAO;
import domain.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.SQLException;
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

        session.close();
    }
}