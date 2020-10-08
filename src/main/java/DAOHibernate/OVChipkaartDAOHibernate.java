package DAOHibernate;

import DAOInterfaces.OVChipkaartDAO;
import DAOInterfaces.ReizigerDAO;
import domain.OVChipkaart;
import domain.Reiziger;

import java.util.List;

public class OVChipkaartDAOHibernate implements OVChipkaartDAO {
    @Override
    public boolean save(OVChipkaart ovkaart) {
        return false;
    }

    @Override
    public boolean update(OVChipkaart ovkaart) {
        return false;
    }

    @Override
    public boolean delete(OVChipkaart ovkaart) {
        return false;
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        return null;
    }

    @Override
    public OVChipkaart findByKaartNummer(int kaartnummer) {
        return null;
    }

    @Override
    public List<OVChipkaart> findAll() {
        return null;
    }

    @Override
    public ReizigerDAO getRdao() {
        return null;
    }
}
