import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    //        OrderListRepo orderRepo = new OrderListRepo();
    static OrderMapRepo orderRepo = new OrderMapRepo();
    static ShopService shopService = new ShopService(orderRepo);

    public static void main(String[] args) {
        String userInput = "";

        System.out.println("Welcome to George's tiny model railway shop!");

        while (!userInput.equals("q")) {
            System.out.println();
            System.out.println("What do you want to do? You have uncountable opportunities in here!");
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
                case "o":
                    placeOrder();
                    break;
                case "s":
                    seeAllOrders();
                    break;
                case "m":
                    System.out.println("""
                            Not implemented - coming later.\
                            
                            Until then please use the workaround: Remove your oder and create a new one with your modifications.\
                            
                            We are sorry for this inconvenience.""");
                    break;
                case "r":
                    deleteOrder();
                    break;
                default:
            }
        }

        System.out.println();
        System.out.println("Goodbye soon!");
    }

    static void printAllProducts() {
        System.out.println(shopService.productRepo.getAllProducts().toString().replaceAll("\\[", "\n["));
    }

    static void placeOrder() {
        String productEan;
        int quantity = 0;
        Scanner userInputScanner = new Scanner(System.in);
        HashMap<Long, Integer> productIntegerHashMap = new HashMap<>();

        while (true) {
            System.out.println();
            System.out.println("Please enter the EAN of a product you want to order or f to finish your order");
            productEan = userInputScanner.nextLine();

            if (productEan.equals("f"))
                break;

            System.out.println("Please enter the quantity you want to order");
            try {
                quantity = Integer.parseInt(userInputScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: " + e.getMessage()
                    + "\nQuantity will be set to 0");
            }

            if (!productEan.isEmpty() && quantity > 0) {
                try {
                    Optional<Product> product = shopService.productRepo.getProduct(Long.parseLong(productEan));

                    if (product.isPresent())
                        productIntegerHashMap.put(product.get().ean(), quantity);

                } catch (NumberFormatException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            else if (quantity == 0)
                System.out.println("Nothing ordered");
            else
                System.out.println("Product does not exist! We are sorry and hope you find another good choice.");
        }

        if (!productIntegerHashMap.isEmpty()) {
            int id = shopService.placeOrder(productIntegerHashMap);
            System.out.println();
            System.out.println("Your order has been placed successfully. Your oder id is: " + id);
        }
        else {
            System.out.println();
            System.out.println("Nothing ordered.");
        }
    }

    public static void seeAllOrders() {
        System.out.println(shopService.orderRepo.getAllOrders().toString().replaceAll("\\[", "\n["));
    }

    public static void deleteOrder() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        System.out.println("Please enter the ID of the order you would like to remove.");
        userInput = scanner.nextLine();

        try {
            shopService.removeOrder(Integer.parseInt(userInput));
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

