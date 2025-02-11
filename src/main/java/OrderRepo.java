import java.util.List;

public interface OrderRepo {
    void add(Order order);

    Order getOrder(int id);

    List<Order> getAllOrders();

    void removeOrder(int id);

    int size();
}
