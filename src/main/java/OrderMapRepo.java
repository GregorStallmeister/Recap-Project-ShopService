import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMapRepo implements OrderRepo {

    protected Map<Integer, Order> orders;

    public OrderMapRepo() {
        this.orders = new HashMap<Integer, Order>();
    }

    @Override
    public void add(Order order) {
        if (!orders.containsKey(order.id()))
            orders.put(order.id(), order);
    }

    @Override
    public Order getOrder(int id) {
        return orders.get(id);
    }

    @Override
    public List<Order> getAllOrders() {
//        return (List<Order>) orders.values();
        return new ArrayList<Order>(orders.values());
    }

    @Override
    public void removeOrder(int id) {
        orders.remove(id);
    }

    @Override
    public int size() {
        return orders.size();
    }
}
