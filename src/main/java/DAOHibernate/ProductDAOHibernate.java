package DAOHibernate;

import DAOInterfaces.ProductDAO;
import domain.OVChipkaart;
import domain.Product;
import domain.Reiziger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class ProductDAOHibernate implements ProductDAO {
    private Session session;

    public ProductDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Product product) {
        try{
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            return true;
        } catch(Exception sqlException){
            System.err.println(sqlException.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Product product) {
        try{
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            return true;
        } catch(Exception sqlException){
            System.err.println(sqlException.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        try{
            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
            return true;
        } catch(Exception sqlException){
            System.err.println(sqlException.getMessage());
            return false;
        }
    }

    @Override
    public List<Product> findByOVChipkaart(OVChipkaart ovkaart) {
        String hql = "SELECT a FROM Product a WHERE :ovchipkaart IN a.ovkaarten";
        Query query = session.createQuery(hql);
        query.setParameter("ovchipkaart",ovkaart);
        return query.getResultList();
    }

    @Override
    public List<Product> findAll() {
        return session.createQuery("SELECT a FROM Product a", Product.class).getResultList();
    }
}
