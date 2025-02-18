import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
