package DAOHibernate;

import DAOInterfaces.AdresDAO;
import DAOInterfaces.ReizigerDAO;
import domain.Adres;
import domain.Reiziger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class AdresDAOHibernate implements AdresDAO {
    private Session session;
    private ReizigerDAO rdao;

    public AdresDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Adres adres) {
        try{
            session.beginTransaction();
            session.save(adres);
            session.getTransaction().commit();
            return true;
        } catch(Exception sqlException){
            System.err.println(sqlException.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        try{
            session.beginTransaction();
            session.saveOrUpdate(adres);
            session.getTransaction().commit();
            return true;
        } catch(Exception sqlException){
            System.err.println(sqlException.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Adres adres) {
        try{
            session.beginTransaction();
            session.delete(adres);
            session.getTransaction().commit();
            return true;
        } catch(Exception sqlException){
            System.err.println(sqlException.getMessage());
            return false;
        }
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        String hql = "SELECT a FROM Adres a WHERE reiziger_id = :reiziger_id";
        Query query = session.createQuery(hql);
        query.setParameter("reiziger_id", reiziger.getId());
        return (Adres) query.getSingleResult();
    }

    @Override
    public List<Adres> findAll() {
        return session.createQuery("SELECT a FROM Adres a", Adres.class).getResultList();
    }

    public void setRdao(ReizigerDAO rdao) {
        this.rdao = rdao;
    }

    @Override
    public ReizigerDAO getRdao() {
        return rdao;
    }
}
