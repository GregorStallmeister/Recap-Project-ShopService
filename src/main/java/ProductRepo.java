import java.util.ArrayList;
import java.util.List;

public class ProductRepo {

    protected List<Product> products;

    public ProductRepo() {
        products = new ArrayList<Product>();
    }

    public void addProduct(Product product) {
        if (getProduct(product.ean()) == null)
            products.add(product);
    }

    public Product getProduct(long ean) {
        Product returnProduct = null;

        for (Product product : products) {
            if (product.ean() == ean) {
                returnProduct = product;
                break;
            }
        }

        return returnProduct;
    }

    public int size() {
        return products.size();
    }
}
