import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ProductSalesApplication extends JFrame {

    private final JTextArea txtSalesReport;
    private final JLabel lblYearsProcessed;

    private ProductSales productSales;

    public ProductSalesApplication() {

        setTitle("Product Sales Application");
        setSize(460, 390);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        productSales = new ProductSales();

        JPanel mainPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        );

        // Buttons
        JButton btnLoad = new JButton("Load Product Data");

        JButton btnSave = new JButton("Save Product Data");

        JPanel buttonPanel = new JPanel(
                new GridLayout(2, 1, 8, 8)
        );

        buttonPanel.add(btnLoad);
        buttonPanel.add(btnSave);

        // Text area
        txtSalesReport = new JTextArea(7, 30);

        txtSalesReport.setEditable(false);

        txtSalesReport.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        txtSalesReport.setBorder(
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        );

        JScrollPane scrollPane = new JScrollPane(txtSalesReport);

        // Years processed label
        lblYearsProcessed = new JLabel("Years Processed: ");

        JPanel bottomPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT)
        );

        bottomPanel.add(lblYearsProcessed);

        // Add components
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Menu bar
        setJMenuBar(createMenuBar());

        // Button actions
        btnLoad.addActionListener(e -> loadProductData());

        btnSave.addActionListener(e -> saveProductData());

        add(mainPanel);

        setVisible(true);
    }

    // Create menu bar
    private JMenuBar createMenuBar() {

        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");

        JMenuItem exitItem = new JMenuItem("Exit");

        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(exitItem);

        // Tools menu
        JMenu toolsMenu = new JMenu("Tools");

        JMenuItem loadItem = new JMenuItem("Load Product Data");

        loadItem.addActionListener(e -> loadProductData());

        JMenuItem saveItem = new JMenuItem("Save Product Data");

        saveItem.addActionListener(e -> saveProductData());

        JMenuItem clearItem = new JMenuItem("Clear");

        clearItem.addActionListener(e -> clearForm());

        toolsMenu.add(loadItem);
        toolsMenu.add(saveItem);
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);

        return menuBar;
    }

    // Load product data
    private void loadProductData() {

        productSales = new ProductSales();

        String report = String.format(
                "Total Sales: %d%n" +
                        "Average Sales: %.0f%n" +
                        "Sales over limit: %d%n" +
                        "Sales under limit: %d",

                productSales.GetTotalSales(),
                productSales.GetAverageSales(),
                productSales.GetSalesOverLimit(),
                productSales.GetSalesUnderLimit()
        );

        txtSalesReport.setText(report);

        lblYearsProcessed.setText(
                "Years Processed: " +
                        productSales.GetProductsProcessed()
        );
    }

    // Save product data to file
    private void saveProductData() {

        if (txtSalesReport.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please load the product data before saving.",
                    "No Data",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String separator = "************************************";

        try (
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("data.txt")
                )
        ) {

            writer.write("DATA LOG");
            writer.newLine();

            writer.write(separator);
            writer.newLine();

            writer.write(txtSalesReport.getText());
            writer.newLine();

            writer.write(separator);

            JOptionPane.showMessageDialog(
                    this,
                    "Product data saved successfully to data.txt.",
                    "Save Complete",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "An error occurred while saving the file: "
                            + e.getMessage(),
                    "File Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Clear form
    private void clearForm() {

        txtSalesReport.setText("");

        lblYearsProcessed.setText("Years Processed: ");
    }

    // Main method
    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                ProductSalesApplication::new
        );
    }
}

