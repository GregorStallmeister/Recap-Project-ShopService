import java.math.BigDecimal;

public record Product(
        long ean,
        String productName,
        String manufacturer,
        String scale,
        String itemType,
        String description,
        BigDecimal price,
        int releaseYear
) {

//    public Product(String eanListEntry) {
//
//
//        this(0, null, null, null, null, null, null, 0);
//
//
//        String[] listEntryParts = eanListEntry.split(",");
//
//    }
}
