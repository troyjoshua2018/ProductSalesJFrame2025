public class ProductSales implements IProductSales {

    public static final int SALES_LIMIT = 500;

    private final int[][] productSales;

    // Default constructor
    public ProductSales() {

        productSales = new int[][] {
                {300, 150, 700},
                {250, 200, 600}
        };
    }

    // Constructor for supplying different sales data
    public ProductSales(int[][] productSales) {

        this.productSales = new int[productSales.length][];

        for (int i = 0; i < productSales.length; i++) {
            this.productSales[i] = productSales[i].clone();
        }
    }

    @Override
    public int[][] GetProductSales() {

        int[][] copy = new int[productSales.length][];

        for (int i = 0; i < productSales.length; i++) {
            copy[i] = productSales[i].clone();
        }

        return copy;
    }

    @Override
    public int GetTotalSales() {

        int total = 0;

        for (int[] year : productSales) {
            for (int sale : year) {
                total += sale;
            }
        }

        return total;
    }

    @Override
    public int GetSalesOverLimit() {

        int count = 0;

        for (int[] year : productSales) {
            for (int sale : year) {

                if (sale > SALES_LIMIT) {
                    count++;
                }
            }
        }

        return count;
    }

    @Override
    public int GetSalesUnderLimit() {

        int count = 0;

        for (int[] year : productSales) {
            for (int sale : year) {

                if (sale < SALES_LIMIT) {
                    count++;
                }
            }
        }

        return count;
    }

    @Override
    public int GetProductsProcessed() {

        return productSales.length;
    }

    @Override
    public double GetAverageSales() {

        int count = 0;

        for (int[] year : productSales) {
            count += year.length;
        }

        if (count == 0) {
            return 0.0;
        }

        return (double) GetTotalSales() / count;
    }
}

