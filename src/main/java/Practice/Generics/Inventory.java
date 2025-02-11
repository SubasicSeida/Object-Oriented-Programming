package Practice.Generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

abstract class Product<T> {
    private int id;
    private String name;
    private T category;

    public Product(int id, String name, T category){
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public T getCategory(){
        return category;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public abstract double getPrice();

    @Override
    public String toString(){
        return "id : " + id + ", name : " + name + ", category : " + category;
    }
}

class ElectronicsProduct extends Product<String>{
    private int warrantyMonths;
    private double price;

    public ElectronicsProduct(int id, String name, String category, int warrantyMonths, double price){
        super(id, name, category);
        this.warrantyMonths = warrantyMonths;
        this.price = price;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getPrice(){
        return price;
    }

    public boolean hasWarranty(){
        return warrantyMonths != 0;
    }
}

class FurnitureProduct extends Product<String>{
    private double price;
    private String material;

    public FurnitureProduct(int id, String name, String category, double price, String material){
        super(id, name, category);
        this.price = price;
        this.material = material;
    }

    @Override
    public double getPrice(){
        return price;
    }
}

class Inventory<T extends Product<?>>{
    private List<T> products = new ArrayList<>();

    public Inventory(List<T> products){
        this.products = products;
    }

    public void addProduct(T product){
        products.add(product);
    }

    public T getProduct(int id){
        for(T product : products){
            if(product.getId() == id) return product;
        }
        return null;
    }

    public List<T> getAllProducts(){
        return products;
    }

    public List<T> sortByPrice(){
        List<T> sorted = new ArrayList<>(products);
        Collections.sort(sorted, (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        return sorted;
    }

    public List<T> filterByCategory(String category){
        List<T> productsOfCategory = new ArrayList<>();
        for(T product : products){
            if(product.getCategory() == category){
                productsOfCategory.add(product);
            }
        }
        return productsOfCategory;
    }

    public T getMostExpensiveProduct(){
        double maxPrice = -1;
        T expensiveProduct = null;
        for(T product : products){
            if(maxPrice < product.getPrice()){
                maxPrice = product.getPrice();
                expensiveProduct = product;
            }
        }
        return expensiveProduct;
    }

    public double getTotalInventoryValue(){
        double total = 0.0;
        for(T product : products){
            total += product.getPrice();
        }
        return total;
    }

    public List<T> sortProductsByName(){
        List<T> sorted = new ArrayList<>(products);
        Collections.sort(sorted, (p1, p2) -> CharSequence.compare(p1.getName(), p2.getName()));
        return sorted;
    }

    public List<T> searchProductsByNameKeyword(String keyword){
        List<T> wantedProducts = new ArrayList<>();
        for(T product : products){
            if(product.getName().contains(keyword)){
                wantedProducts.add(product);
            }
        }
        return wantedProducts;
    }

    public void removeProductsBelowPrice(double price){
        products.removeIf(product -> product.getPrice() < price);
    }

    public boolean containsProduct(int id){
        for(T product : products){
            if(product.getId() == id) return true;
        }
        return false;
    }

    public List<ElectronicsProduct> getProductsWithWarranty(){
        return products.stream()
                .filter(product -> product instanceof ElectronicsProduct)
                .map(product -> (ElectronicsProduct) product)
                .filter(product -> product.hasWarranty())
                .toList();
    }

    public List<T> sortByName(){
        return products.stream()
                .sorted((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()))
                .toList();
    }

    public static void main(String[] args) {
        ElectronicsProduct laptop = new ElectronicsProduct(1, "Laptop", "Electronics", 12, 999.99);
        FurnitureProduct chair = new FurnitureProduct(2, "Chair", "Furniture", 49.99, "Wood");
        ElectronicsProduct phone = new ElectronicsProduct(3, "Phone", "Electronics", 6, 499.99);

        List<Product<?>> productList = new ArrayList<>();
        productList.add(laptop);
        productList.add(chair);
        productList.add(phone);

        Inventory<Product<?>> inventory = new Inventory<>(productList);

        System.out.println("Most expensive product: " + inventory.getMostExpensiveProduct());
        System.out.println("Total inventory value: " + inventory.getTotalInventoryValue());
        System.out.println("Products in 'Electronics' category: " + inventory.filterByCategory("Electronics"));
        System.out.println("Products sorted by name: " + inventory.sortByName());
        System.out.println("Products with warranty: " + inventory.getProductsWithWarranty());
    }
}
