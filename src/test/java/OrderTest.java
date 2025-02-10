import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    public void OrderExpectedCorrectValuesForProductAndQuantityWhenInitialized() {
        // given
        int orderID = 0;
        Product product = new Product(Long.valueOf("4023222992300"), "Märklin Electric Locomotive",
                "Märklin", "HO", "Locomotive",
                "Electric locomotive with sound", BigDecimal.valueOf(349.99), 2022);
        int quantity = 12;

        // when
        Order order = new Order(orderID, product, quantity);

        // then
        assertEquals(orderID, order.id());
        assertEquals(product, order.product());
        assertEquals(quantity, order.quantity());
    }
}
