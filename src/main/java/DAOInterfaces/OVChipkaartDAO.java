package DAOInterfaces;

import domain.OVChipkaart;
import domain.Reiziger;

import java.util.List;

public interface OVChipkaartDAO {
    boolean save(OVChipkaart ovkaart);
    boolean update(OVChipkaart ovkaart);
    boolean delete(OVChipkaart ovkaart);
    List<OVChipkaart> findByReiziger(Reiziger reiziger);
    OVChipkaart findByKaartNummer(int kaartnummer);
    List<OVChipkaart> findAll();
    ReizigerDAO getRdao();
    void setRdao(ReizigerDAO rdao);
}
