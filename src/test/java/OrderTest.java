import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;

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
        HashMap<Product, Integer> productIntegerHashMap = new HashMap<Product, Integer>();
        productIntegerHashMap.put(product, quantity);

        // when
        Order order = new Order(orderID, productIntegerHashMap);

        // then
        assertEquals(orderID, order.id());
        assertEquals(productIntegerHashMap, order.productQuantityHashMap());
    }
}
