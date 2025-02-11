import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderListRepoTest {

    @Test
    public void OrderListRepoExpectedOrderExistsWhenAdded() {
        // given
        OrderListRepo orderListRepo = new OrderListRepo();
        int orderID = 0;
        Product product = new Product(Long.valueOf("4023222992300"), "Märklin Electric Locomotive",
                "Märklin", "HO", "Locomotive",
                "Electric locomotive with sound", BigDecimal.valueOf(349.99), 2022);
        int quantity = 12;
        HashMap<Product, Integer> productIntegerHashMap = new HashMap<Product, Integer>();
        productIntegerHashMap.put(product, quantity);
        Order order = new Order(orderID, productIntegerHashMap);

        // when
        orderListRepo.add(order);
        Order orderActual = orderListRepo.getOrder(orderID);

        // then
        assertEquals(order, orderActual);
    }

    @Test
    public void OrderListRepoExpectedSize1WhenSameOrderAddedTwice() {
        // given
        OrderListRepo orderListRepo = new OrderListRepo();
        Product product = new Product(Long.valueOf("4023222992300"), "Märklin Electric Locomotive",
                "Märklin", "HO", "Locomotive",
                "Electric locomotive with sound", BigDecimal.valueOf(349.99), 2022);
        int quantity = 12;
        HashMap<Product, Integer> productIntegerHashMap = new HashMap<Product, Integer>();
        productIntegerHashMap.put(product, quantity);
        Order order = new Order(0, productIntegerHashMap);
        int expectedSize = 1;

        // when
        orderListRepo.add(order);
        orderListRepo.add(order);
        int actualSize = orderListRepo.size();

        // then
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void OrderListRepoExpectedOrderContainedInListByGetAllOrders() {
        // given
        OrderListRepo orderListRepo = new OrderListRepo();
        Product product = new Product(Long.valueOf("4023222992300"), "Märklin Electric Locomotive",
                "Märklin", "HO", "Locomotive",
                "Electric locomotive with sound", BigDecimal.valueOf(349.99), 2022);
        int quantity = 12;
        HashMap<Product, Integer> productIntegerHashMap = new HashMap<Product, Integer>();
        productIntegerHashMap.put(product, quantity);
        Order order = new Order(0, productIntegerHashMap);
        orderListRepo.add(order);

        // when
        List<Order> allOrders = orderListRepo.getAllOrders();

        // then
        assertTrue(allOrders.contains(order));
    }

    @Test
    public void OrderListRepoExpectedSize1WhenOrderAdded0WhenOrderRemoved() {
        // given
        OrderListRepo orderListRepo = new OrderListRepo();
        int orderID = 0;
        Product product = new Product(Long.valueOf("4023222992300"), "Märklin Electric Locomotive",
                "Märklin", "HO", "Locomotive",
                "Electric locomotive with sound", BigDecimal.valueOf(349.99), 2022);
        int quantity = 12;
        HashMap<Product, Integer> productIntegerHashMap = new HashMap<Product, Integer>();
        productIntegerHashMap.put(product, quantity);
        Order order = new Order(orderID, productIntegerHashMap);
        int expectedSize = 1;

        // when
        orderListRepo.add(order);
        int actualSize = orderListRepo.size();

        // then
        assertEquals(expectedSize, actualSize);

        // given
        expectedSize = 0;

        // when
        orderListRepo.removeOrder(orderID);
        actualSize = orderListRepo.size();

        // then
        assertEquals(expectedSize, actualSize);
    }
}
