package DAOInterfaces;

import domain.OVChipkaart;
import domain.Product;

import java.util.ArrayList;

public interface ProductDAO {
    boolean save(Product product);
    boolean update(Product product);
    boolean delete(Product product);
    ArrayList<Product> findByOVChipkaart(OVChipkaart ovkaart);
    ArrayList<Product> findAll();
}
