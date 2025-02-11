package Practice.Optionals;

import java.util.*;

enum CuisineType {
    ITALIAN,
    CHINESE,
    MEXICAN,
    INDIAN,
    FRENCH
}

interface Billable {
    double applyDiscount(double discountRate);
    String getDescription();
}

abstract class MenuItem implements Billable {
    private String code;
    private String name;
    private double price;

    public MenuItem(String code, String name, double price){
        this.code = code;
        this.name = name;
        this.price = price;
    }

    @Override
    public double applyDiscount(double discountRate){
        return price - price * discountRate;
    }

    @Override
    public String getDescription(){
        return "Code : " + code + ", Name : " + name + ", Price + " + price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

class Dish extends MenuItem {
    private CuisineType cuisineType;
    private String mainIngredient;

    public Dish(String code, String name, double price, CuisineType cuisineType, String mainIngredient){
        super(code, name, price);
        this.cuisineType = cuisineType;
        this.mainIngredient = mainIngredient;
    }

    public CuisineType getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(CuisineType cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getMainIngredient() {
        return mainIngredient;
    }

    public void setMainIngredient(String mainIngredient) {
        this.mainIngredient = mainIngredient;
    }

    @Override
    public String getDescription(){
        return "Code : " + getCode() + ", Name : " + getName() + ", Price + " + getPrice() +
                ", Cuisine Type : " + cuisineType + ", Main Ingredient : " + mainIngredient;
    }
}

class Drink extends MenuItem {
    private double volume;

    public Drink(String code, String name, double price, double volume){
        super(code, name, price);
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public String getDescription(){
        return "Code : " + getCode() + ", Name : " + getName() + ", Price + " + getPrice() +
                ", Volume : " + volume;
    }
}

class TableReservation<T extends MenuItem> {
    private String reservationId;
    private Date reservationDate;
    private HashMap<T, Integer> orderedItems = new HashMap<>();
    private int tableNumber;

    public TableReservation(String reservationId, Date reservationDate, int tableNumber){
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
        this.tableNumber = tableNumber;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public HashMap<T, Integer> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(HashMap<T, Integer> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void addItemToOrder(T item, int quantity){
        if(orderedItems.containsKey(item)){
            orderedItems.put(item, orderedItems.get(item) + quantity);
        } else orderedItems.put(item, quantity);
    }

    public double calculateTotal(double discountRate){
        double total = 0.0;
        for(HashMap.Entry<T, Integer> entry : orderedItems.entrySet()){
            total += entry.getKey().applyDiscount(discountRate) * entry.getValue();
        }
        return total;
    }
}

class Customer {
    private String customerId;
    private String customerName;
    private String phoneNumber;
    List<TableReservation<? extends MenuItem>> reservations = new ArrayList<>();

    public Customer(String customerId, String customerName, String phoneNumber){
        this.customerId = customerId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void addReservation(TableReservation<? extends MenuItem> tableReservation){
        reservations.add(tableReservation);
    }

    public List<TableReservation<? extends MenuItem>> getReservations(){
        return reservations;
    }
}

class Restaurant {
    private String restaurantName;
    private List<Customer> customers = new ArrayList<>();
    private Map<String, MenuItem> menuItems = new HashMap<>();

    public Restaurant(String restaurantName){
        this.restaurantName = restaurantName;
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void addMenuItem(MenuItem menuItem){
        if(menuItem instanceof Dish){
            menuItems.put("Dish", menuItem);
        } else if(menuItem instanceof Drink){
            menuItems.put("Drink", menuItem);
        } else System.out.println("MenuItem invalid.");
    }

    public Optional<Customer> getCustomerById(String id){
        for(Customer customer : customers){
            if(customer.getCustomerId().equals(id)) return Optional.of(customer);
        }
        return Optional.empty();
    }

    public void displayMenu(){
        for(HashMap.Entry<String, MenuItem> entry : menuItems.entrySet()){
            System.out.println(entry.getValue().getDescription());
        }
    }

    public double calculateTotalSales(double discountRate){
        double total = 0.0;
        for(Customer customer : customers){
            for(TableReservation<? extends MenuItem> reservation : customer.getReservations()){
                total += reservation.calculateTotal(discountRate);
            }
        }
        return total;
    }
}

class MainApplication {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant("Gourmet Delight");

        Dish pasta = new Dish("D001", "Pasta Carbonara", 12.99, CuisineType.ITALIAN, "Pasta");
        Dish tacos = new Dish("D002", "Chicken Tacos", 9.99, CuisineType.MEXICAN, "Chicken");
        Dish curry = new Dish("D003", "Butter Chicken", 14.99, CuisineType.INDIAN, "Chicken");

        Drink cola = new Drink("DR001", "Cola", 2.99, 500);
        Drink wine = new Drink("DR002", "Red Wine", 7.99, 250);

        restaurant.addMenuItem(pasta);
        restaurant.addMenuItem(tacos);
        restaurant.addMenuItem(curry);
        restaurant.addMenuItem(cola);
        restaurant.addMenuItem(wine);

        System.out.println("\n--- Restaurant Menu ---");
        restaurant.displayMenu();

        Customer customer = new Customer("CUST001", "John Doe", "123-456-7890");

        TableReservation<Dish> dishReservation = new TableReservation<>("R001", new Date(), 5);
        dishReservation.addItemToOrder(pasta, 2);
        dishReservation.addItemToOrder(tacos, 3);

        TableReservation<Drink> drinkReservation = new TableReservation<>("R002", new Date(), 6);
        drinkReservation.addItemToOrder(cola, 2);
        drinkReservation.addItemToOrder(wine, 1);

        customer.addReservation(dishReservation);
        customer.addReservation(drinkReservation);

        restaurant.addCustomer(customer);

        System.out.println("\n--- Customer Reservations ---");
        for (TableReservation<? extends MenuItem> reservation : customer.getReservations()) {
            System.out.println("Reservation ID: " + reservation.getReservationId());
            System.out.println("Table Number: " + reservation.getTableNumber());
            System.out.println("Total Amount: $" + reservation.calculateTotal(.2));
            System.out.println("--------------------------------");
        }

        System.out.println("\n--- Total Sales ---");
        System.out.println("Total Sales: $" + restaurant.calculateTotalSales(0.2));
    }
}