import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderListRepo {

    protected List<Order> orders;

    public OrderListRepo() {
        orders = new ArrayList<Order>();
    }

    public void add(Order order) {
        boolean orderExists = false;

        for (Order existingOrder : orders) {
            if (order.id() == existingOrder.id()) {
                orderExists = true;
                break;
            }
        }

        if (!orderExists)
            orders.add(order);
    }

    public Order getOrder(int id) {
        Order returnOrder = null;

        for (Order existingOrder : orders) {
            if (id == existingOrder.id()) {
                returnOrder = existingOrder;
                break;
            }
        }

        return returnOrder;
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public void removeOrder(int id) {
        Order orderToBeRemoved = null;

        for (Order order : orders) {
            if (order.id() == id) {
                orderToBeRemoved = order;
                break;
            }
        }

        if (orderToBeRemoved != null)
            orders.remove(orderToBeRemoved);
    }

    public int size() {
        return orders.size();
    }
}
