import lombok.With;

import java.time.Instant;
import java.util.HashMap;

@With
public record Order(
        int id,
        HashMap<Product, Integer> productQuantityHashMap,
        OrderStatus orderStatus,
        Instant orderDate
) {
}
