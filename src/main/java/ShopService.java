import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class ShopService {

    protected OrderRepo orderRepo;
    protected ProductRepo productRepo;

    public ShopService() {
        this.productRepo = new ProductRepo();
        fillProductRepo();
    }

    public ShopService(OrderRepo orderRepo) {
        this();

        this.orderRepo = orderRepo;
    }

    public void placeOrder(int id, HashMap<Long, Integer> productEanQuantity) {
        HashMap<Product, Integer> productIntegerHashMap = new HashMap<Product, Integer>();

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

        Order order = new Order(id, productIntegerHashMap);
        orderRepo.add(order);
    }

    private void fillProductRepo() {
//        URL url = this.getClass().getResource("EAN-List_csv_headers_modified_shortened.txt");
//        File file = new File(String.valueOf(url));
//        File file = new File("EAN-List_csv_headers_modified_shortened.txt");
//
//        try {
//            Scanner fileReader = new Scanner(file);
//        } catch (FileNotFoundException e) {
//            System.out.println("Error: " + e.toString());
//        }

        String eanListFromChatGpt = "4005279537016, \"Br 219 Diesel Locomotive\", \"Fleischmann\", \"HO\", \"Locomotive\", \"A diesel locomotive in red color\", 159.99, 2021\n" +
                "4015615365285, \"Märklin Tanker Car Set\", \"Märklin\", \"HO\", \"Freight Car\", \"Set of 4 tankers with different designs\", 99.99, 2022\n" +
                "4053085005343, \"Bachmann Signal Bridge\", \"Bachmann\", \"N\", \"Accessory\", \"Signal bridge for N-scale layout\", 49.99, 2023\n" +
                "4001883685027, \"Piko Freight Train Set\", \"Piko\", \"HO\", \"Train Set\", \"Complete freight train set with 4 cars\", 229.99, 2020\n" +
                "4010115512301, \"Roco Digital Controller\", \"Roco\", \"HO\", \"Accessory\", \"Digital controller for HO scale layouts\", 199.99, 2023\n" +
                "4005265181907, \"Fleischmann Passenger Car\", \"Fleischmann\", \"HO\", \"Passenger Car\", \"Coaches for passenger trains in blue\", 59.99, 2021\n" +
                "4023222992300, \"Märklin Electric Locomotive\", \"Märklin\", \"HO\", \"Locomotive\", \"Electric locomotive with sound\", 349.99, 2022\n" +
                "4056231348903, \"Bachmann Train Station\", \"Bachmann\", \"N\", \"Building\", \"A detailed N-scale train station\", 89.99, 2023\n" +
                "4019655942579, \"Piko Track Set\", \"Piko\", \"HO\", \"Accessory\", \"Track set for building layouts\", 49.99, 2021\n" +
                "4055086012498, \"Roco Freight Wagon\", \"Roco\", \"HO\", \"Freight Car\", \"A detailed freight wagon in red\", 69.99, 2022\n" +
                "4005971320239, \"Fleischmann Electric Railcar\", \"Fleischmann\", \"HO\", \"Railcar\", \"Electric railcar in vintage livery\", 119.99, 2020\n" +
                "4005245310198, \"Märklin Starter Set\", \"Märklin\", \"HO\", \"Train Set\", \"Complete starter set with controller and track\", 399.99, 2023";
        String[] eanListEntries = eanListFromChatGpt.split("\n");

        for (String eanListEntry : eanListEntries) {
            productRepo.addProduct(eanListEntry);
        }
    }
}
