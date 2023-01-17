package example;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import org.jboss.ejb3.annotation.SecurityDomain;

@Stateless
@SecurityDomain("other")
@PermitAll // need to use any security annotation to obtain caller principal
public class ProductsBean implements Products {

    private static List<Product> products = new ArrayList<>();
    static {
        products.add(new Product("Hat", 10.99, 25));
        products.add(new Product("Jersey", 22.95, 50));
        products.add(new Product("Bat", 18.99, 75));
        products.add(new Product("Basketball", 15.95, 100));
    }

    @Resource
    private SessionContext ejbContext;

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    @RolesAllowed({"admin"})
    public void addProduct(String name, double price, int numInStock) {
        products.add(new Product(name, price, numInStock));
    }

    @Override
    @RolesAllowed({"admin"})
    public void removeProduct(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                products.remove(product);
            }
        }
    }
}
