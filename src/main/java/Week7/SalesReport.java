package Week7;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

class Sale {
    private String productName;
    private String productCategory;
    private double saleAmount;
    private LocalDate saleDate;

    public Sale(String productName, String productCategory, double saleAmount, LocalDate saleDate) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.saleAmount = saleAmount;
        this.saleDate = saleDate;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public double getSaleAmount() {
        return saleAmount;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "productName='" + productName + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", saleAmount=" + saleAmount +
                ", saleDate=" + saleDate +
                '}';
    }
}

class SalesReport {
    public void generateReport(List<Sale> sales) {
        double totalRevenue = sales.stream().mapToDouble(Sale::getSaleAmount).sum();
        OptionalDouble avgSales = sales.stream().mapToDouble(Sale::getSaleAmount).average();

        System.out.println("Sales Report - Full:");
        System.out.println("Total Revenue: $" + totalRevenue);
        avgSales.ifPresent(avg -> System.out.println("Average Sale: $" + avg));
    }

    public void generateReport(List<Sale> sales, String productCategory) {
        List<Sale> filteredSales = sales.stream()
                .filter(sale -> sale.getProductCategory().equalsIgnoreCase(productCategory))
                .collect(Collectors.toList());

        double categoryRevenue = filteredSales.stream().mapToDouble(Sale::getSaleAmount).sum();
        OptionalDouble avgCategorySales = filteredSales.stream().mapToDouble(Sale::getSaleAmount).average();

        System.out.println("\nSales Report - Category: " + productCategory);
        System.out.println("Total Revenue: $" + categoryRevenue);
        avgCategorySales.ifPresent(avg -> System.out.println("Average Sale: $" + avg));
    }

    public void generateReport(List<Sale> sales, LocalDate startDate, LocalDate endDate) {
        List<Sale> filteredSales = sales.stream()
                .filter(sale -> !sale.getSaleDate().isBefore(startDate) && !sale.getSaleDate().isAfter(endDate))
                .collect(Collectors.toList());

        double dateRangeRevenue = filteredSales.stream().mapToDouble(Sale::getSaleAmount).sum();
        OptionalDouble avgDateRangeSales = filteredSales.stream().mapToDouble(Sale::getSaleAmount).average();

        System.out.println("\nSales Report - Date Range: " + startDate + " to " + endDate);
        System.out.println("Total Revenue: $" + dateRangeRevenue);
        avgDateRangeSales.ifPresent(avg -> System.out.println("Average Sale: $" + avg));
    }
}

class Main2 {
    public static void main(String[] args) {
        List<Sale> sales = Arrays.asList(
                new Sale("Laptop", "Electronics", 1200, LocalDate.of(2024, 1, 10)),
                new Sale("Shoes", "Clothing", 50, LocalDate.of(2024, 3, 1)),
                new Sale("Washing Machine", "Home Appliances", 1000, LocalDate.of(2024, 4, 20))
        );

        SalesReport salesReport = new SalesReport();
        salesReport.generateReport(sales);
        salesReport.generateReport(sales, "Electronics");

        LocalDate startDate = LocalDate.of(2024, 2, 1);
        LocalDate endDate = LocalDate.of(2024, 4, 1);
        salesReport.generateReport(sales, startDate, endDate);
    }
}
