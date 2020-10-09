package DAOHibernate;

import DAOInterfaces.OVChipkaartDAO;
import DAOInterfaces.ProductDAO;
import DAOInterfaces.ReizigerDAO;
import domain.Adres;
import domain.OVChipkaart;
import domain.Product;
import domain.Reiziger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class OVChipkaartDAOHibernate implements OVChipkaartDAO {
    private Session session;
    private ReizigerDAO rdao;

    public OVChipkaartDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(OVChipkaart ovkaart) {
        try{
            session.beginTransaction();
            session.save(ovkaart);
            session.getTransaction().commit();
            return true;
        } catch(Exception sqlException){
            System.err.println(sqlException.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(OVChipkaart ovkaart) {
        try{
            session.beginTransaction();
            session.saveOrUpdate(ovkaart);
            session.getTransaction().commit();
            return true;
        } catch(Exception sqlException){
            System.err.println(sqlException.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(OVChipkaart ovkaart) {
        try{
            session.beginTransaction();
            session.delete(ovkaart);
            session.getTransaction().commit();
            return true;
        } catch(Exception sqlException){
            System.err.println(sqlException.getMessage());
            return false;
        }
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        String hql = "SELECT a FROM OVChipkaart a WHERE reiziger_id = :reiziger_id";
        Query query = session.createQuery(hql);
        query.setParameter("reiziger_id", reiziger.getId());
        return query.getResultList();
    }

    @Override
    public OVChipkaart findByKaartNummer(int kaartnummer) {
        String hql = "SELECT a FROM OVChipkaart a WHERE kaart_nummer = :kaart_nummer";
        Query query = session.createQuery(hql);
        query.setParameter("kaart_nummer", kaartnummer);
        return (OVChipkaart) query.getSingleResult();
    }

    @Override
    public List<OVChipkaart> findAll() {
        return session.createQuery("SELECT a FROM OVChipkaart a", OVChipkaart.class).getResultList();
    }

    @Override
    public ReizigerDAO getRdao() {
        return rdao;
    }

    @Override
    public void setRdao(ReizigerDAO rdao) {
        this.rdao = rdao;
    }

}
