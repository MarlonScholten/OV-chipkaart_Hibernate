package DAOHibernate;

import DAOInterfaces.ProductDAO;
import domain.OVChipkaart;
import domain.Product;

import java.util.ArrayList;

public class ProductDAOHibernate implements ProductDAO {
    @Override
    public boolean save(Product product) {
        return false;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public boolean delete(Product product) {
        return false;
    }

    @Override
    public ArrayList<Product> findByOVChipkaart(OVChipkaart ovkaart) {
        return null;
    }

    @Override
    public ArrayList<Product> findAll() {
        return null;
    }
}
