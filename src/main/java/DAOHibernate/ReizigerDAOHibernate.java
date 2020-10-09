package DAOHibernate;

import DAOInterfaces.ReizigerDAO;
import domain.Reiziger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO {
    private Session session;

    public ReizigerDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        try{
            session.beginTransaction();
            session.save(reiziger);
            session.getTransaction().commit();
            return true;
        } catch(Exception sqlException){
            System.err.println(sqlException.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try{
            session.beginTransaction();
            session.saveOrUpdate(reiziger);
            session.getTransaction().commit();
            return true;
        } catch(Exception sqlException){
            System.err.println(sqlException.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try{
            session.beginTransaction();
            session.delete(reiziger);
            session.getTransaction().commit();
            return true;
        } catch(Exception sqlException){
            System.err.println(sqlException.getMessage());
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) {
        return session.load(Reiziger.class, id);
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        String hql = "SELECT a FROM Reiziger a WHERE geboortedatum = :gbdatum";
        Query query = session.createQuery(hql);
        query.setParameter("gbdatum",Date.valueOf(datum));
        return query.getResultList();
    }

    @Override
    public List<Reiziger> findAll() {
        return session.createQuery("SELECT a FROM Reiziger a", Reiziger.class).getResultList();
    }
}
