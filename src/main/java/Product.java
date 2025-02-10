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
}
