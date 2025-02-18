import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderMapRepoTest {
    @Test
    public void OrderMapRepoExpectedOrderExistsWhenAdded() {
        // given
        OrderMapRepo orderMapRepo = new OrderMapRepo();
        int orderID = 0;
        Product product = new Product(Long.valueOf("4023222992300"), "Märklin Electric Locomotive",
                "Märklin", "HO", "Locomotive",
                "Electric locomotive with sound", BigDecimal.valueOf(349.99), 2022);
        int quantity = 12;
        HashMap<Product, Integer> productIntegerHashMap = new HashMap<Product, Integer>();
        productIntegerHashMap.put(product, quantity);
        Order order = new Order(orderID, productIntegerHashMap, OrderStatus.PROCESSING, Instant.now());

        // when
        orderMapRepo.add(order);
        Order orderActual = orderMapRepo.getOrder(orderID);

        // then
        assertEquals(order, orderActual);
    }

    @Test
    public void OrderMapRepoExpectedSize1WhenSameOrderAddedTwice() {
        // given
        OrderMapRepo orderMapRepo = new OrderMapRepo();
        Product product = new Product(Long.valueOf("4023222992300"), "Märklin Electric Locomotive",
                "Märklin", "HO", "Locomotive",
                "Electric locomotive with sound", BigDecimal.valueOf(349.99), 2022);
        int quantity = 12;
        HashMap<Product, Integer> productIntegerHashMap = new HashMap<Product, Integer>();
        productIntegerHashMap.put(product, quantity);
        Order order = new Order(0, productIntegerHashMap, OrderStatus.PROCESSING, Instant.now());
        int expectedSize = 1;

        // when
        orderMapRepo.add(order);
        orderMapRepo.add(order);
        int actualSize = orderMapRepo.size();

        // then
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void OrderMapRepoExpectedOrderContainedInListByGetAllOrders() {
        // given
        OrderMapRepo orderMapRepo = new OrderMapRepo();
        Product product = new Product(Long.valueOf("4023222992300"), "Märklin Electric Locomotive",
                "Märklin", "HO", "Locomotive",
                "Electric locomotive with sound", BigDecimal.valueOf(349.99), 2022);
        int quantity = 12;
        HashMap<Product, Integer> productIntegerHashMap = new HashMap<Product, Integer>();
        productIntegerHashMap.put(product, quantity);
        Order order = new Order(0, productIntegerHashMap, OrderStatus.PROCESSING, Instant.now());
        orderMapRepo.add(order);

        // when
        List<Order> allOrders = orderMapRepo.getAllOrders();

        // then
        assertTrue(allOrders.contains(order));
    }

    @Test
    public void OrderMapRepoExpectedSize1WhenOrderAdded0WhenOrderRemoved() {
        // given
        OrderMapRepo orderMapRepo = new OrderMapRepo();
        int orderID = 0;
        Product product = new Product(Long.valueOf("4023222992300"), "Märklin Electric Locomotive",
                "Märklin", "HO", "Locomotive",
                "Electric locomotive with sound", BigDecimal.valueOf(349.99), 2022);
        int quantity = 12;
        HashMap<Product, Integer> productIntegerHashMap = new HashMap<Product, Integer>();
        productIntegerHashMap.put(product, quantity);
        Order order = new Order(orderID, productIntegerHashMap, OrderStatus.PROCESSING, Instant.now());
        int expectedSize = 1;

        // when
        orderMapRepo.add(order);
        int actualSize = orderMapRepo.size();

        // then
        assertEquals(expectedSize, actualSize);

        // given
        expectedSize = 0;

        // when
        orderMapRepo.removeOrder(orderID);
        actualSize = orderMapRepo.size();

        // then
        assertEquals(expectedSize, actualSize);
    }
}
