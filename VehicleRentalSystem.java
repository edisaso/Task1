package task1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Vehicle {
    protected String model;
    protected double value;

    public Vehicle(String model, double value) {
        this.model = model;
        this.value = value;
    }

    public abstract double calculateRentalCost(long rentalDays);

    public abstract double calculateInsuranceCost(long rentalDays);

    public abstract double getDailyRentalRate(long rentalDays);

    public abstract double getDailyInsuranceRate();
}

class Car extends Vehicle {
    public int safetyRating;

    public Car(String model, double value, int safetyRating) {
        super(model, value);
        this.safetyRating = safetyRating;
    }

    @Override
    public double calculateRentalCost(long rentalDays) {
        return getDailyRentalRate(rentalDays) * rentalDays;
    }

    @Override
    public double calculateInsuranceCost(long rentalDays) {
        double dailyInsurance = getDailyInsuranceRate();
        return dailyInsurance * rentalDays;
    }

    @Override
    public double getDailyRentalRate(long rentalDays) {
        return (rentalDays <= 7) ? 20 : 15;
    }

    @Override
    public double getDailyInsuranceRate() {
        double dailyInsurance = value * 0.0001;
        if (safetyRating >= 4) {
            dailyInsurance *= 0.9;
        }
        return dailyInsurance;
    }
}

class Motorcycle extends Vehicle {
    public int riderAge;

    public Motorcycle(String model, double value, int riderAge) {
        super(model, value);
        this.riderAge = riderAge;
    }

    @Override
    public double calculateRentalCost(long rentalDays) {
        return getDailyRentalRate(rentalDays) * rentalDays;
    }

    @Override
    public double calculateInsuranceCost(long rentalDays) {
        double dailyInsurance = getDailyInsuranceRate();
        return dailyInsurance * rentalDays;
    }

    @Override
    public double getDailyRentalRate(long rentalDays) {
        return (rentalDays <= 7) ? 15 : 10;
    }

    @Override
    public double getDailyInsuranceRate() {
        double dailyInsurance = value * 0.0002;
        if (riderAge < 25) {
            dailyInsurance *= 1.2;
        }
        return dailyInsurance;
    }
}

class CargoVan extends Vehicle {
    public int driverExperience;

    public CargoVan(String model, double value, int driverExperience) {
        super(model, value);
        this.driverExperience = driverExperience;
    }

    @Override
    public double calculateRentalCost(long rentalDays) {
        return getDailyRentalRate(rentalDays) * rentalDays;
    }

    @Override
    public double calculateInsuranceCost(long rentalDays) {
        double dailyInsurance = getDailyInsuranceRate();
        return dailyInsurance * rentalDays;
    }

    @Override
    public double getDailyRentalRate(long rentalDays) {
        return (rentalDays <= 7) ? 50 : 40;
    }

    @Override
    public double getDailyInsuranceRate() {
        double dailyInsurance = value * 0.0003;
        if (driverExperience > 5) {
            dailyInsurance *= 0.85;
        }
        return dailyInsurance;
    }
}

class Invoice {
    private String customerName;
    private Vehicle vehicle;
    private LocalDate reservationStart;
    private LocalDate reservationEnd;
    private LocalDate actualReturnDate;

    public Invoice(String customerName, Vehicle vehicle, LocalDate reservationStart, LocalDate reservationEnd, LocalDate actualReturnDate) {
        this.customerName = customerName;
        this.vehicle = vehicle;
        this.reservationStart = reservationStart;
        this.reservationEnd = reservationEnd;
        this.actualReturnDate = actualReturnDate;
    }

    public void printInvoice() {
        long reservedDays = ChronoUnit.DAYS.between(reservationStart, reservationEnd);
        long actualDays = ChronoUnit.DAYS.between(reservationStart, actualReturnDate);
        long DayDifference = reservedDays - actualDays;
        double dailyRentalRate = vehicle.getDailyRentalRate(actualDays);
        double dailyInsuranceRate = vehicle.getDailyInsuranceRate();
        double rentalCost = vehicle.calculateRentalCost(actualDays);
        double insuranceCost = vehicle.calculateInsuranceCost(actualDays);
        double earlyReturnRentalDiscount = 0.0;
        double earlyReturnInsuranceDiscount = 0.0;

        if (actualDays < reservedDays) {
            earlyReturnRentalDiscount = vehicle.getDailyRentalRate(reservedDays) * (reservedDays - actualDays) / 2;
            earlyReturnInsuranceDiscount = dailyInsuranceRate * (reservedDays - actualDays);
            rentalCost = rentalCost + vehicle.getDailyRentalRate(DayDifference) * 2;
        }

        System.out.printf("XXXXXXXXXXXXXXXXXXX\n");
        System.out.printf("Date: %s\n", LocalDate.now());
        System.out.printf("Customer Name: %s\n", customerName);
        System.out.printf("Rented Vehicle: %s\n", vehicle.model);
        System.out.printf("\n");
        System.out.printf("Reservation start date: %s\n", reservationStart);
        System.out.printf("Reservation end date: %s\n", reservationEnd);
        System.out.printf("Reserved rental days: %d days\n", reservedDays);
        System.out.printf("\n");
        System.out.printf("Actual return date: %s\n", actualReturnDate);
        System.out.printf("Actual rental days: %d days\n", actualDays);
        System.out.printf("\n");
        System.out.printf("Rental cost per day: $%.2f\n", dailyRentalRate);
        if (vehicle instanceof CargoVan){
        System.out.printf("Initial insurance per day: $%.2f\n", vehicle.value * 0.0003);
        }
        else if (vehicle instanceof Motorcycle){
        System.out.printf("Initial insurance per day: $%.2f\n", vehicle.value * 0.0002);
        }
        if (vehicle instanceof Car && ((Car) vehicle).safetyRating >= 4) {
            System.out.printf("Insurance discount per day: $%.2f\n", vehicle.value * 0.0001 * 0.1);
        } else if (vehicle instanceof Motorcycle && ((Motorcycle) vehicle).riderAge < 25) {
            System.out.printf("Insurance addition per day: $%.2f\n", dailyInsuranceRate - vehicle.value * 0.0002);
        } else if (vehicle instanceof CargoVan && ((CargoVan) vehicle).driverExperience > 5) {
            System.out.printf("Insurance discount per day: $%.2f\n", vehicle.value * 0.0003 -dailyInsuranceRate);
        }
        System.out.printf("Insurance per day: $%.2f\n", dailyInsuranceRate);
        System.out.printf("\n");
        if (actualDays < reservedDays) {
            System.out.printf("Early return discount for rent: $%.2f\n", earlyReturnRentalDiscount);
            System.out.printf("Early return discount for insurance: $%.2f\n", earlyReturnInsuranceDiscount);
            System.out.printf("\n");
        }
        System.out.printf("Total rent: $%.2f\n", rentalCost);
        System.out.printf("Total Insurance: $%.2f\n", insuranceCost);
        System.out.printf("Total: $%.2f\n", rentalCost + insuranceCost);
        System.out.printf("XXXXXXXXXXXXXXXXXXX\n");
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        /* Vehicle cargoVan = new CargoVan("Citroen Jumper", 20000.00, 8);
        Invoice invoice1 = new Invoice("John Markson", cargoVan, LocalDate.of(2024, 6, 3), LocalDate.of(2024, 6, 18), LocalDate.of(2024, 6, 13));
        invoice1.printInvoice(); */

/*         Vehicle motorcycle = new Motorcycle("Triumph Tiger Sport 660", 10000.00, 20);
        Invoice invoice2 = new Invoice("Mary Johnson", motorcycle, LocalDate.of(2024, 6, 3), LocalDate.of(2024, 6, 13), LocalDate.of(2024, 6, 13));
        invoice2.printInvoice(); */

        Vehicle car = new Car("Mitsubishi Mirage", 15000.00, 3);
        Invoice invoice1 = new Invoice("John Doe", car, LocalDate.of(2024, 6, 3), LocalDate.of(2024, 6, 13), LocalDate.of(2024, 6, 13));
        invoice1.printInvoice();
    }
}