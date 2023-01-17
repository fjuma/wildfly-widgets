package example;

import jakarta.ejb.Remote;
import java.util.List;

@Remote
public interface Products {

    List<Product> getProducts();

    void addProduct(String name, double price, int numInStock);

    void removeProduct(String name);

}
