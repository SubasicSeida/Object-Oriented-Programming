package Practice.DatabaseConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

enum Category {
    ELECTRONICS,
    GROCERY,
    CLOTHING
}

class Product {
    private int productId;
    private String productName;
    private double productPrice;
    private Category productCategory;

    public Product(int productId, String productName, double productPrice, Category productCategory) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productCategory=" + productCategory +
                '}';
    }
}

class ProductInventory {
    private List<Product> products = new ArrayList<>();

    public ProductInventory(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProductsByCategory(Category category){
        return products.stream()
                .filter(product -> product.getProductCategory() == category)
                .collect(Collectors.toList());
    }

    public List<Product> getProductsWithGreaterPrice(double price){
        return products.stream()
                .filter(product -> product.getProductPrice() >= price)
                .collect(Collectors.toList());
    }

    public void printProductDetails(){
        products.forEach(product -> System.out.println(product));
    }
}

class DbConnect1 {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/tasksdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "hFW5yLylRpx!=2)";

    private Connection connection = null;

    public DbConnect1(){
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public List<Product> getProductsFromDb() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM productinventory";

        try(Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                int id = rs.getInt("id");
                String productName = rs.getString("product_name");
                double productPrice = rs.getDouble("product_price");
                Category productCategory = Category.valueOf(rs.getString("product_category"));

                products.add(new Product(id, productName, productPrice, productCategory));
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return products;
    }

    public void updateProductName(int id, String name) {
        String query = "UPDATE productinventory SET product_name = ? WHERE id = ?";

        try(PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setString(1, name);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void updateProductCategory(int id, Category category) {
        String query = "UPDATE productinventory SET product_category = ? WHERE id = ?";

        try(PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, category.name());
            pstmt.setInt(2, id);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
