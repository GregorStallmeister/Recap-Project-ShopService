import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    //        OrderListRepo orderRepo = new OrderListRepo();
    static OrderMapRepo orderRepo = new OrderMapRepo();
    static ShopService shopService = new ShopService(orderRepo);

    public static void main(String[] args) {
        String userInput = "";

//        HashMap<Long, Integer> orderItems = new HashMap<Long, Integer>();
//        orderItems.put(Long.valueOf("4023222992300"), 3);
//        orderItems.put(Long.valueOf("1023222992300"), 1);
//        orderItems.put(Long.valueOf("4005245310198"), 12);
//
//        shopService.placeOrder(0, orderItems);
//
//        System.out.println(orderRepo.getAllOrders().toString().replaceAll(",", ",\n"));

        System.out.println("Welcome to George's model railway shop!");

        while (!userInput.equals("q"))
        {
            System.out.println();
            System.out.println("What do you want to do? You have uncountable possibilities in here!");
            System.out.println("Input p to see all products, o to place an order, s to see all orders, m to modify an order, r to remove an order.");
            System.out.println("And if you really really want: input q to quit.");

            Scanner scanner = new Scanner(System.in);
            userInput = scanner.nextLine();

            switch (userInput) {
                case "q":
                    break;
                case "p":
                    printAllProducts();
                    break;
                default:
            }
        }

        System.out.println();
        System.out.println("Goodbye!");
    }

    static void printAllProducts() {
        System.out.println(shopService.productRepo.getAllProducts().toString().replaceAll("\\[", "\n["));
    }

    static void placeOrder() {
        String productEan = "";
        int quantity = 0;
        Scanner userInputScanner = new Scanner(System.in);
        HashMap<Product, Integer> productIntegerHashMap = new HashMap<Product, Integer>();

        while (!productEan.equals(("f"))) {
            System.out.println();
            System.out.println("Please enter the EAN of a product you want to order or f to finish your order");
            productEan = userInputScanner.nextLine();
            System.out.println("Please enter the quantity you want to order");
            quantity = Integer.valueOf(userInputScanner.nextLine());

            if (productEan != "" && quantity > 0) {
                Optional<Product> product = shopService.productRepo.getProduct(Long.valueOf(productEan));

                if (product.isPresent())
                    productIntegerHashMap.put(product.get(), quantity);                }
                else
                    System.out.println("Product does not exist!");
            }

        shopService.placeOrder(shopService.orderRepo.size(), productIntegerHashMap);
        }
    }

