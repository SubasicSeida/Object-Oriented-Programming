package Week5;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        vehicles.add(new Car("Toyota", 60000, 80));
        vehicles.add(new Truck("Ford", 85000, 90));
        vehicles.add(new Motorcycle("Yamaha", 15000, 70));

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.service());
            vehicle.simulateYear();

            if (vehicle instanceof Car) {
                Car car = (Car) vehicle;
                car.drive(100);
                System.out.println("Car driven. Current mileage: " + car.getMileage() +
                        ", Health: " + car.getHealth());
            } else if (vehicle instanceof Truck) {
                Truck truck = (Truck) vehicle;
                truck.haul(6000);
                System.out.println("Truck hauled. Current health: " + truck.getHealth());
            } else if (vehicle instanceof Motorcycle) {
                Motorcycle motorcycle = (Motorcycle) vehicle;
                motorcycle.race(150);
                System.out.println("Motorcycle raced. Current mileage: " + motorcycle.getMileage() +
                        ", Health: " + motorcycle.getHealth());
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the number of the vehicle: ");
            int choice = scanner.nextInt();

            if (choice > 0 && choice <= vehicles.size()) {
                Vehicle selectedVehicle = vehicles.get(choice - 1);
                selectedVehicle.performMaintenance(selectedVehicle);
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}
