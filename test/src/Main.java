import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductSalesTest {

    private final ProductSales sales = new ProductSales();

    @Test
    void GetSalesOverLimit_ReturnsNumberOfSales() {

        assertEquals(2, sales.GetSalesOverLimit());
    }

    @Test
    void GetSalesUnderLimit_ReturnsNumberOfSales() {

        assertEquals(4, sales.GetSalesUnderLimit());
    }
}
