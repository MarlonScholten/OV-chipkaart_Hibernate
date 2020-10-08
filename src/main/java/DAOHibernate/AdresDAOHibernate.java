package DAOHibernate;

import DAOInterfaces.AdresDAO;
import DAOInterfaces.ReizigerDAO;
import domain.Adres;
import domain.Reiziger;
import org.hibernate.Session;

import java.util.List;

public class AdresDAOHibernate implements AdresDAO {
    private Session session;

    public AdresDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Adres adres) {
        return false;
    }

    @Override
    public boolean update(Adres adres) {
        return false;
    }

    @Override
    public boolean delete(Adres adres) {
        return false;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        return null;
    }

    @Override
    public List<Adres> findAll() {
        return null;
    }

    @Override
    public ReizigerDAO getRdao() {
        return null;
    }
}
