import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Product actual = productRepo.getProduct(Long.valueOf("4023222992300"));

        // then
        assertEquals(product, actual);
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
}
