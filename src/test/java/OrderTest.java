import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    public void OrderExpectedCorrectValuesForProductAndQuantityWhenInitialized() {
        // given
        Product product = new Product(Long.valueOf("4023222992300"), "Märklin Electric Locomotive",
                "Märklin", "HO", "Locomotive",
                "Electric locomotive with sound", BigDecimal.valueOf(349.99), 2022);
        int expectedQuantity = 12;

        // when
        Order order = new Order(product, expectedQuantity);

        // then
        assertEquals(product, order.product());
        assertEquals(expectedQuantity, order.quantity());
    }
}
