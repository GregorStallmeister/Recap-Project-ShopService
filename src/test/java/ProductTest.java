import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    public void ProductExpectedCorrectValuesByGettersWhenInitializedWithDistinctParams() {
        // given
        Product product;
        long expectedEan = Long.valueOf("4023222992300");
        String expectedProductName = "Märklin Electric Locomotive";
        String expectedManufacturer = "Märklin";
        String expectedScale = "HO";
        String expectedItemType = "Locomotive";
        String expectedDescription = "Electric locomotive with sound";
        BigDecimal expectedPrice = BigDecimal.valueOf(349.99);
        int expectedReleaseYear = 2022;

        // when
        product = new Product(Long.valueOf("4023222992300"), "Märklin Electric Locomotive",
                "Märklin", "HO", "Locomotive",
                "Electric locomotive with sound", BigDecimal.valueOf(349.99), 2022);

        // then
        assertEquals(expectedEan, product.ean());
        assertEquals(expectedProductName, product.productName());
        assertEquals(expectedManufacturer, product.manufacturer());
        assertEquals(expectedScale, product.scale());
        assertEquals(expectedItemType, product.itemType());
        assertEquals(expectedDescription, product.description());
        assertEquals(expectedPrice, product.price());
        assertEquals(expectedReleaseYear, product.releaseYear());
    }

//    @Test
//    public void ProductExpectedCorrectValuesByGettersWhenInitializedWithEanListEntry() {
//        // given
//        Product product;
//        String eanListEntry = "4023222992300, \"Märklin Electric Locomotive\", \"Märklin\", \"HO\", \"Locomotive\", " +
//                "\"Electric locomotive with sound\", 349.99, 2022";
//        long expectedEan = Long.valueOf("4023222992300");
//        String expectedProductName = "Märklin Electric Locomotive";
//        String expectedManufacturer = "Märklin";
//        String expectedScale = "HO";
//        String expectedItemType = "Locomotive";
//        String expectedDescription = "Electric locomotive with sound";
//        BigDecimal expectedPrice = BigDecimal.valueOf(349.99);
//        int expectedReleaseYear = 2022;
//
//        // when
//        product = new Product(eanListEntry);
//
//        // then
//        assertEquals(expectedEan, product.ean());
//        assertEquals(expectedProductName, product.productName());
//        assertEquals(expectedManufacturer, product.manufacturer());
//        assertEquals(expectedScale, product.scale());
//        assertEquals(expectedItemType, product.itemType());
//        assertEquals(expectedDescription, product.description());
//        assertEquals(expectedPrice, product.price());
//        assertEquals(expectedReleaseYear, product.releaseYear());
//    }
}
