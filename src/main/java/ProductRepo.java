import java.math.BigDecimal;
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

    public void addProduct(String eanListEntry) {
        String[] listEntryParts = eanListEntry.split(",");

        if (getProduct(Long.valueOf("" + listEntryParts[0])) != null)
            return;

        long ean = Long.valueOf("" + listEntryParts[0]);
        String productName = listEntryParts[1].replace("\"", "").trim();
        String manufacturer = listEntryParts[2].replace("\"", "").trim();
        String scale = listEntryParts[3].replace("\"", "").trim();
        String itemType = listEntryParts[4].replace("\"", "").trim();
        String description = listEntryParts[5].replace("\"", "").trim();
        BigDecimal price = BigDecimal.valueOf(Double.valueOf(listEntryParts[6].replace("\"", "").trim()));
        int releaseYear = Integer.valueOf(listEntryParts[7].replace("\"", "").trim());

        Product product = new Product (ean, productName, manufacturer, scale, itemType, description, price, releaseYear);
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

    public List<Product> getAllProducts() {
        return products;
    }

    public void removeProduct(long ean) {
        Product productToBeRemoved = null;

        for (Product product : products) {
            if (product.ean() == ean) {
                productToBeRemoved = product;
                break;
            }
        }

        if (productToBeRemoved != null)
            products.remove(productToBeRemoved);
    }

    public int size() {
        return products.size();
    }
}
