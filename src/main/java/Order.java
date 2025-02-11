import java.util.HashMap;

public record Order(
        int id,
        HashMap<Product, Integer> productQuantityHashMap
//        Product product,
//        int quantity
) {
}
