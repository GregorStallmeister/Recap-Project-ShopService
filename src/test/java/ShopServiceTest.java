import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ShopServiceTest {

    @Test
    public void placeOrder() {
        // given
        OrderMapRepo orderMapRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(orderMapRepo);
        HashMap<Long, Integer> productEanQuantity = new HashMap<>();
        productEanQuantity.put(Long.parseLong("4005245310198"), 12);

        // when
        int orderID = shopService.placeOrder(productEanQuantity);
        Order order = shopService.getOrder(orderID);

        // then
        assertEquals(orderID, order.id());
    }

    @Test
    public void getOrderByStatus() {
        // given
        OrderMapRepo orderMapRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(orderMapRepo);
        HashMap<Long, Integer> productEanQuantity = new HashMap<>();
        productEanQuantity.put(Long.parseLong("4005245310198"), 12);

        // when
        int orderID = shopService.placeOrder(productEanQuantity);
        List<Order> ordersProcessing = shopService.getOrdersByStatus(OrderStatus.PROCESSING);
        List<Order> ordersCompleted = shopService.getOrdersByStatus(OrderStatus.COMPLETED);

        // then
        assertEquals(orderID, ordersProcessing.getFirst().id());
        assertTrue(ordersCompleted.isEmpty());
    }

    @Test
    public void updateOrder() {
        // given
        OrderMapRepo orderMapRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(orderMapRepo);
        HashMap<Long, Integer> productEanQuantity = new HashMap<>();
        productEanQuantity.put(Long.parseLong("4005245310198"), 12);

        // when
        int orderID = shopService.placeOrder(productEanQuantity);
        shopService.updateOrder(orderID, OrderStatus.COMPLETED);
        List<Order> ordersProcessing = shopService.getOrdersByStatus(OrderStatus.PROCESSING);
        List<Order> ordersCompleted = shopService.getOrdersByStatus(OrderStatus.COMPLETED);

        // then
        assertTrue(ordersProcessing.isEmpty());
        assertEquals(orderID, ordersCompleted.getFirst().id());
    }

    @Test
    public void updateOrderExpectedNoSuchElementExceptionWhenCalledWithOrderIdWhichNotExists() {
        // given
        OrderMapRepo orderMapRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(orderMapRepo);

        // when and then
        assertThrows(NoSuchElementException.class, () -> shopService.updateOrder(41, OrderStatus.COMPLETED));
    }
}
