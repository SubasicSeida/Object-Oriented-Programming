package MidtermPrep;

import java.util.*;

enum CuisineType {
    ITALIAN,
    CHINESE,
    MEXICAN,
    INDIAN,
    FRENCH
}

interface Billable {
    public double applyDiscount(double discountRate);
    public String getDescription();
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
        return price * (1-discountRate);
    }

    @Override
    public String getDescription(){
        return "Code : " + code + ", Name : " + name + ", Price : " + price;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
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

    @Override
    public String getDescription(){
        return "Code : " + getCode() + ", Name : " + getName() + ", Price : " + getPrice() +
                ", Cuisine Type : " + cuisineType + ", Main Ingredient : " + mainIngredient;
    }

    public CuisineType getCuisineType(){
        return cuisineType;
    }

    public void setCuisineType(CuisineType cuisineType){
        this.cuisineType = cuisineType;
    }

    public String getMainIngredient(){
        return mainIngredient;
    }

    public void setMainIngredient(String mainIngredient){
        this.mainIngredient = mainIngredient;
    }
}

class Drink extends MenuItem {
    private double volume;

    public Drink(String code, String name, double price, double volume){
        super(code, name, price);
        this.volume = volume;
    }

    @Override
    public String getDescription(){
        return "Code : " + getCode() + ", Name : " + getName() + ", Price : " + getPrice() +
                ", Volume : " + volume;
    }

    public double getVolume(){
        return volume;
    }

    public void setVolume(double volume){
        this.volume = volume;
    }
}

class TableReservation <T extends MenuItem & Billable> {
    private String reservationId;
    private Date reservationDate;
    private HashMap<T, Integer> orderedItems;
    private int tableNumber;

    public TableReservation (String reservationId, Date reservationDate, int tableNumber){
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
        this.tableNumber = tableNumber;
        orderedItems = new HashMap<>();
    }

    public void addItemToOrder(T item, int quantity){
        orderedItems.put(item, quantity);
    }

    public double calculateTotalAmount(double discountRate){
        double total = 0.0;
        for(HashMap.Entry<T, Integer> entry : orderedItems.entrySet()){
            T item = entry.getKey();
            Integer quantity = entry.getValue();
            total += item.applyDiscount(discountRate) * quantity;
        }
        return total;
    }

    public String getReservationId(){
        return reservationId;
    }

    public void setReservationId(String reservationId){
        this.reservationId = reservationId;
    }

    public Date getReservationDate(){
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate){
        this.reservationDate = reservationDate;
    }

    public int getTableNumber(){
        return tableNumber;
    }

    public void setTableNumber(int tableNumber){
        this.tableNumber = tableNumber;
    }

    @Override
    public String toString(){
        return "ID : " + reservationId + ", Date : " + reservationDate + ", Table : " + tableNumber;
    }
}

class Customer {
    private String customerId;
    private String name;
    private String phoneNumber;
    private List<TableReservation<? extends MenuItem>> reservations;

    public Customer(String customerId, String name, String phoneNumber){
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        reservations = new ArrayList<>();
    }

    public void addReservation(TableReservation<? extends MenuItem> reservation){
        reservations.add(reservation);
    }

    public List<TableReservation<? extends MenuItem>> getReservations(){
        return reservations;
    }

    public String getCustomerId(){
        return customerId;
    }

    public void setCustomerId(String customerId){
        this.customerId = customerId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void getReservationHistory(){
        for(TableReservation<? extends MenuItem> reservation : reservations){
            System.out.println(reservation);
        }
    }
}

class Restaurant {
    private String restaurantName;
    private List<Customer> customers;
    private Map<String, MenuItem> menuItems;

    public Restaurant(String restaurantName){
        this.restaurantName = restaurantName;
        customers = new ArrayList<>();
        menuItems = new HashMap<>();
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void addMenuItem(MenuItem item){
        if(item instanceof Drink){
            Drink drink = (Drink) item;
            menuItems.put("Drink", drink);
        }else {
            Dish dish = (Dish) item;
            menuItems.put("Dish", dish);
        }
    }

    public Optional<Customer> getCustomer(String customerId){
        for(Customer customer : customers){
            if(customer.getCustomerId().equals(customerId)){
                return Optional.of(customer);
            }
        }
        return Optional.empty();
    }

    public void displayMenu(){
        for(HashMap.Entry<String, MenuItem> entry : menuItems.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue().getDescription());
        }
    }

    public double calculateTotalSales(){
        double total = 0.0;
        for(Customer customer : customers){
            for(TableReservation<? extends MenuItem> reservation : customer.getReservations()){
                total += reservation.calculateTotalAmount(0.0);
            }
        }
        return total;
    }
}

class Main {
    public static void main(String[] args){
        Dish pizza = new Dish("123", "pizza", 10.5, CuisineType.ITALIAN, "tomato sauce");
        Drink water = new Drink("222", "water", 2.0, 400);
        Restaurant mojRestoran = new Restaurant("moj restoran");
        mojRestoran.addMenuItem(pizza);
        mojRestoran.addMenuItem(water);
        TableReservation<Dish> reservation1 = new TableReservation<>("333", new Date(), 12);
        TableReservation<Drink> reservation2 = new TableReservation<>("334", new Date(), 13);
        reservation1.addItemToOrder(pizza, 2);
        reservation2.addItemToOrder(water, 3);
        Customer customer1 = new Customer("555", "New Customer", "4832904820");
        customer1.addReservation(reservation1);
        customer1.addReservation(reservation2);
        mojRestoran.addCustomer(customer1);
        customer1.getReservationHistory();
        System.out.println(mojRestoran.calculateTotalSales());
    }
}