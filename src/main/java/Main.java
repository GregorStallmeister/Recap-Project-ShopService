import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
//        OrderListRepo orderRepo = new OrderListRepo();
        OrderMapRepo orderRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(orderRepo);

        HashMap<Long, Integer> orderItems = new HashMap<Long, Integer>();
        orderItems.put(Long.valueOf("4023222992300"), 3);
        orderItems.put(Long.valueOf("1023222992300"), 1);
        orderItems.put(Long.valueOf("4005245310198"), 12);

        shopService.placeOrder(0, orderItems);

        System.out.println(orderRepo.getAllOrders().toString().replaceAll(",", ",\n"));
    }
}
