import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        OrderListRepo orderRepo = new OrderListRepo();
        OrderMapRepo orderRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(orderRepo);
        String userInput = "";

//        HashMap<Long, Integer> orderItems = new HashMap<Long, Integer>();
//        orderItems.put(Long.valueOf("4023222992300"), 3);
//        orderItems.put(Long.valueOf("1023222992300"), 1);
//        orderItems.put(Long.valueOf("4005245310198"), 12);
//
//        shopService.placeOrder(0, orderItems);
//
//        System.out.println(orderRepo.getAllOrders().toString().replaceAll(",", ",\n"));

        System.out.println("Welcome to George's tiny model railway shop!");

        while (!userInput.equals("q"))
        {
            System.out.println();
            System.out.println("What do you want to do? You have uncountable possibilities in here!");
            System.out.println("Input p to see all products, o to place an order, s to see all orders,m to modify an order, r to remove an order.");
            System.out.println("And if you really really want: input q to quit.");

            Scanner scanner = new Scanner(System.in);
            userInput = scanner.nextLine();

            switch (userInput) {
                case "q":
                    break;
                default:
                    continue;
            }
        }

        System.out.println();
        System.out.println("Goodbye!");
    }
}
