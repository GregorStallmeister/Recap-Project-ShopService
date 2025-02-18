import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
