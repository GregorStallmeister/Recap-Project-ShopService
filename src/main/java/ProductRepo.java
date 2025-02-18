import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepo {

    private final List<Product> products;

    public ProductRepo() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        if (getProduct(product.ean()).isEmpty())
            products.add(product);
    }

    public void addProduct(String eanListEntry) {
        String[] listEntryParts = eanListEntry.split(",");

        if (getProduct(Long.parseLong(listEntryParts[0])).isPresent())
            return;

        long ean = Long.parseLong(listEntryParts[0]);
        String productName = listEntryParts[1].replace("\"", "").trim();
        String manufacturer = listEntryParts[2].replace("\"", "").trim();
        String scale = listEntryParts[3].replace("\"", "").trim();
        String itemType = listEntryParts[4].replace("\"", "").trim();
        String description = listEntryParts[5].replace("\"", "").trim();
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(listEntryParts[6].replace("\"", "").trim()));
        int releaseYear = Integer.parseInt(listEntryParts[7].replace("\"", "").trim());

        Product product = new Product (ean, productName, manufacturer, scale, itemType, description, price, releaseYear);
        products.add(product);
    }

    public Optional<Product> getProduct(long ean) {
        for (Product product : products) {
            if (product.ean() == ean) {
                return Optional.of(product);
            }
        }
       return  Optional.empty();
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public void removeProduct(long ean) {
        Optional<Product> productToBeRemoved = getProduct(ean);

        productToBeRemoved.ifPresent(product -> products.remove(product));
    }

    public int size() {
        return products.size();
    }
}
