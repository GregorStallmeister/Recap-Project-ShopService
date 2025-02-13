import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductRepoTest {

    @Test
    public void ProductRepoExpectedProductContainedWhenAdded() {
        // given
        ProductRepo productRepo = new ProductRepo();
        Product product = new Product(Long.valueOf("4023222992300"), "Märklin Electric Locomotive",
                "Märklin", "HO", "Locomotive",
                "Electric locomotive with sound", BigDecimal.valueOf(349.99), 2022);

        // when
        productRepo.addProduct(product);
        Optional<Product> actual = productRepo.getProduct(Long.valueOf("4023222992300"));

        // then
        assertEquals(product, actual.get());
    }

    @Test
    public void ProductRepoExpectedSize1WhenSameProductAddedTwice() {
        // given
        ProductRepo productRepo = new ProductRepo();
        Product product = new Product(Long.valueOf("4023222992300"), "Märklin Electric Locomotive",
                "Märklin", "HO", "Locomotive",
                "Electric locomotive with sound", BigDecimal.valueOf(349.99), 2022);
        int expectedSize = 1;

        // when
        productRepo.addProduct(product);
        productRepo.addProduct(product);
        int actualSize = productRepo.size();

        // then
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void ProductRepoExpectedProductContainedWhenAddedByEanEntryString() {
        // given
        ProductRepo productRepo = new ProductRepo();
        String eanListEntry = "4023222992300, \"Märklin Electric Locomotive\", \"Märklin\", \"HO\", \"Locomotive\", " +
                "\"Electric locomotive with sound\", 349.99, 2022";
        Product product = new Product(Long.valueOf("4023222992300"), "Märklin Electric Locomotive",
                "Märklin", "HO", "Locomotive",
                "Electric locomotive with sound", BigDecimal.valueOf(349.99), 2022);

        // when
        productRepo.addProduct(eanListEntry);
        Optional<Product> actual = productRepo.getProduct(Long.valueOf("4023222992300"));

        // then
        assertEquals(product, actual.get());
    }

    @Test
    public void ProductRepoExpectedSize1WhenSameProductAddedTwiceByEanEntryString() {
        // given
        ProductRepo productRepo = new ProductRepo();
        String eanListEntry = "4023222992300, \"Märklin Electric Locomotive\", \"Märklin\", \"HO\", \"Locomotive\", " +
                "\"Electric locomotive with sound\", 349.99, 2022";
        int expectedSize = 1;

        // when
        productRepo.addProduct(eanListEntry);
        productRepo.addProduct(eanListEntry);
        int actualSize = productRepo.size();

        // then
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void ProductRepoExpectedSizeFirst1Then0WhenSameProductAddedByEanEntryStringAndRemovedByEan() {
        // given
        ProductRepo productRepo = new ProductRepo();
        String eanListEntry = "4023222992300, \"Märklin Electric Locomotive\", \"Märklin\", \"HO\", \"Locomotive\", " +
                "\"Electric locomotive with sound\", 349.99, 2022";
        int expectedSize = 1;

        // when
        productRepo.addProduct(eanListEntry);
        int actualSize = productRepo.size();

        // then
        assertEquals(expectedSize, actualSize);

        // when
        productRepo.removeProduct(Long.valueOf("4023222992300"));
        expectedSize = 0;
        actualSize = productRepo.size();

        // then
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public  void ProductRepoExpectedProductsContainedInListByGetAllProductsWhenAdded() {
        // given
        ProductRepo productRepo = new ProductRepo();
        String eanListEntry1 = "4023222992300, \"Märklin Electric Locomotive\", \"Märklin\", \"HO\", \"Locomotive\", " +
                "\"Electric locomotive with sound\", 349.99, 2022";
        String eanListEntry2 = "4005245310198, \"Märklin Starter Set\", \"Märklin\", \"HO\", \"Train Set\", " +
                "\"Complete starter set with controller and track\", 399.99, 2023";
        List<Product> actualProductList;
        Optional<Product> product1;
        Optional<Product> product2;

        // when
        productRepo.addProduct(eanListEntry1);
        productRepo.addProduct(eanListEntry2);
        product1 = productRepo.getProduct(Long.valueOf("4023222992300"));
        product2 = productRepo.getProduct(Long.valueOf("4005245310198"));
        actualProductList = productRepo.getAllProducts();

        // then
        assertTrue(actualProductList.contains(product1.get()));
        assertTrue(actualProductList.contains(product2.get()));
    }
}
