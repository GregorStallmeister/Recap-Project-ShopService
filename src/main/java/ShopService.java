
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ShopService {

    private OrderRepo orderRepo;
    private final ProductRepo productRepo;

    public ShopService() {
        this.productRepo = new ProductRepo();
        fillProductRepo();
    }

    public ShopService(OrderRepo orderRepo) {
        this();

        this.orderRepo = orderRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.getAllProducts();
    }

    public Optional<Product> getProduct(long ean) {
        return productRepo.getProduct(ean);
    }

    public int placeOrder(HashMap<Long, Integer> productEanQuantity) {
        HashMap<Product, Integer> productIntegerHashMap = new HashMap<>();

        for (Long productEan : productEanQuantity.keySet()) {
//            Product product = null;
            Optional<Product> product = productRepo.getProduct(productEan);

            if (product.isPresent()) {
                productIntegerHashMap.put(product.get(), productEanQuantity.get(productEan));
            }
            else {
                System.out.println("Product " + productEan + " does not exist!");
            }
        }

        int id = Integer.parseInt("" + LocalDateTime.now().getDayOfYear() + LocalDateTime.now().getHour()
                + LocalDateTime.now().getMinute() + LocalDateTime.now().getSecond());

        Order order = new Order(id, productIntegerHashMap, OrderStatus.PROCESSING, Instant.now());
        orderRepo.add(order);

        return id;
    }

    public List<Order> getAllOrders() {
        return orderRepo.getAllOrders();
    }

    public List<Order> getOrdersByStatus(OrderStatus orderStatus) {
        return getAllOrders()
                .stream()
                .filter(order -> order.orderStatus().equals(orderStatus))
                .toList();
    }

    public Order getOrder(int id) {
        return orderRepo.getOrder(id);
    }

    public void removeOrder(int orderID) {
        orderRepo.removeOrder(orderID);
    }

    private void fillProductRepo() {
        String[] eanListEntriesFromChatGPT = new String[12];
        eanListEntriesFromChatGPT[0] = "4005279537016, \"Br 219 Diesel Locomotive\", \"Fleischmann\", \"HO\", \"Locomotive\", \"A diesel locomotive in red color\", 159.99, 2021";
        eanListEntriesFromChatGPT[1] = "4015615365285, \"Märklin Tanker Car Set\", \"Märklin\", \"HO\", \"Freight Car\", \"Set of 4 tankers with different designs\", 99.99, 2022";
        eanListEntriesFromChatGPT[2] = "4053085005343, \"Bachmann Signal Bridge\", \"Bachmann\", \"N\", \"Accessory\", \"Signal bridge for N-scale layout\", 49.99, 2023";
        eanListEntriesFromChatGPT[3] = "4001883685027, \"Piko Freight Train Set\", \"Piko\", \"HO\", \"Train Set\", \"Complete freight train set with 4 cars\", 229.99, 2020";
        eanListEntriesFromChatGPT[4] = "4010115512301, \"Roco Digital Controller\", \"Roco\", \"HO\", \"Accessory\", \"Digital controller for HO scale layouts\", 199.99, 2023";
        eanListEntriesFromChatGPT[5] = "4005265181907, \"Fleischmann Passenger Car\", \"Fleischmann\", \"HO\", \"Passenger Car\", \"Coaches for passenger trains in blue\", 59.99, 2021";
        eanListEntriesFromChatGPT[6] = "4023222992300, \"Märklin Electric Locomotive\", \"Märklin\", \"HO\", \"Locomotive\", \"Electric locomotive with sound\", 349.99, 2022";
        eanListEntriesFromChatGPT[7] = "4056231348903, \"Bachmann Train Station\", \"Bachmann\", \"N\", \"Building\", \"A detailed N-scale train station\", 89.99, 2023";
        eanListEntriesFromChatGPT[8] = "4019655942579, \"Piko Track Set\", \"Piko\", \"HO\", \"Accessory\", \"Track set for building layouts\", 49.99, 2021";
        eanListEntriesFromChatGPT[9] = "4055086012498, \"Roco Freight Wagon\", \"Roco\", \"HO\", \"Freight Car\", \"A detailed freight wagon in red\", 69.99, 2022";
        eanListEntriesFromChatGPT[10] = "4005971320239, \"Fleischmann Electric Railcar\", \"Fleischmann\", \"HO\", \"Railcar\", \"Electric railcar in vintage livery\", 119.99, 2020";
        eanListEntriesFromChatGPT[11] = "4005245310198, \"Märklin Starter Set\", \"Märklin\", \"HO\", \"Train Set\", \"Complete starter set with controller and track\", 399.99, 2023";

        for (String eanListEntry : eanListEntriesFromChatGPT) {
            productRepo.addProduct(eanListEntry);
        }
    }
}
