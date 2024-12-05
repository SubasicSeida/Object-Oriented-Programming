package MidtermPrep;

import java.util.*;

enum CarType {
    SEDAN,
    SUV,
    TRUCK,
    VAN,
    SPORTS
}

interface Rentable {
    public double applyDiscount(double discountRate);
    public String getDescription();
}

abstract class Vehicle implements Rentable {
    private String vehicleId;
    private String name;
    private double rentalRate;
    private HashMap<Date, Integer> kilometersRecord;

    public Vehicle (String vehicleId, String name, double rentalRate){
        this.vehicleId = vehicleId;
        this.name = name;
        this.rentalRate = rentalRate;
        kilometersRecord = new HashMap<>();
        kilometersRecord.put(new Date(), 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public double applyDiscount(double discountRate){
        return rentalRate * (1 - discountRate);
    }

    @Override
    public String getDescription(){
        return "ID : " + vehicleId + ", Name : " + name + ", Rental Rate : " + rentalRate;
    }

    public void addKilometers(Date rentalDate, int kilometers){
        kilometersRecord.put(rentalDate, kilometers);
    }

    public int getTotalKilometers(){
        int totalKm = 0;
        for(HashMap.Entry<Date, Integer> entry : kilometersRecord.entrySet()){
            totalKm += entry.getValue();
        }
        return totalKm;
    }
}

class Car extends Vehicle {
    private CarType carType;
    private String engineType;

    public Car (String vehicleId, String name, double rentalRate, CarType carType, String engineType){
        super(vehicleId, name, rentalRate);
        this.carType = carType;
        this.engineType = engineType;
    }

    @Override
    public String getDescription(){
        return "ID : " + getVehicleId() + ", Name : " + getName() + ", Rental Rate : " + getRentalRate()
                + ", Car type : " + carType + ", Engine type : " + engineType;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }
}

class Motorcycle extends Vehicle {
    private double engineCapacity;

    public Motorcycle(String vehicleId, String name, double rentalRate, double engineCapacity){
        super(vehicleId, name, rentalRate);
        this.engineCapacity = engineCapacity;
    }

    @Override
    public String getDescription(){
        return "ID : " + getVehicleId() + ", Name : " + getName() + ", Rental Rate : " + getRentalRate()
                + ", Engine Capacity : " + engineCapacity;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }
}

class RentalTransaction <T extends Vehicle & Rentable> {
    private String transactionId;
    private Date rentalDate;
    private HashMap<T, Integer> rentedVehicles;
    private int customerId;

    public RentalTransaction (String transactionId, Date rentalDate, int customerId){
        this.transactionId = transactionId;
        this.rentalDate = rentalDate;
        this.customerId = customerId;
        rentedVehicles = new HashMap<>();
    }

    public void addVehicleToRental(T vehicle, int days){
        rentedVehicles.put(vehicle, days);
    }

    public double calculateTotalAmount(){
        double total = 0.0;
        for(HashMap.Entry<T, Integer> entry : rentedVehicles.entrySet()){
            total += entry.getKey().applyDiscount(0.0) * entry.getValue();
        }
        return total;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}

class RentalCustomer {
    private String customerId;
    private String name;
    private String phoneNumber;
    private List<RentalTransaction<? extends Vehicle>> rentalHistory;

    public RentalCustomer (String customerId, String name, String phoneNumber){
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        rentalHistory = new ArrayList<>();
    }

    public void addRentalTransaction(RentalTransaction<? extends Vehicle> transaction){
        rentalHistory.add(transaction);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

class RentalCompany {
    private String companyName;
    private List<Customer> customers;
    private Map<String, Vehicle> vehicleInventory;

    public RentalCompany(String companyName){
        this.companyName = companyName;
        customers = new ArrayList<>();
        vehicleInventory = new HashMap<>();
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void addVehicle(Vehicle vehicle){
        if(vehicle instanceof Car){
            Car car = (Car) vehicle;
            vehicleInventory.put("Car", car);
        }else {
            Motorcycle motorcycle = (Motorcycle) vehicle;
            vehicleInventory.put("Motorcycle", motorcycle);
        }
    }


//    public Customer getCustomer(String customerId){
//
//    }
}

class CarRental {

}
