# Vehicle Rental System

This Java package provides a simple vehicle rental system, supporting various types of vehicles including cars, motorcycles, and cargo vans. It allows for the calculation of rental and insurance costs based on the type of vehicle, rental duration, and specific attributes of the vehicle.

## Classes and Structure

The package consists of the following classes:

1. **Vehicle (abstract)**
   - Fields: `model`, `value`
   - Abstract Methods:
     - `calculateRentalCost(long rentalDays)`
     - `calculateInsuranceCost(long rentalDays)`
     - `getDailyRentalRate(long rentalDays)`
     - `getDailyInsuranceRate()`

2. **Car (extends Vehicle)**
   - Fields: `safetyRating`
   - Method Overrides:
     - `calculateRentalCost(long rentalDays)`
     - `calculateInsuranceCost(long rentalDays)`
     - `getDailyRentalRate(long rentalDays)`
     - `getDailyInsuranceRate()`

3. **Motorcycle (extends Vehicle)**
   - Fields: `riderAge`
   - Method Overrides:
     - `calculateRentalCost(long rentalDays)`
     - `calculateInsuranceCost(long rentalDays)`
     - `getDailyRentalRate(long rentalDays)`
     - `getDailyInsuranceRate()`

4. **CargoVan (extends Vehicle)**
   - Fields: `driverExperience`
   - Method Overrides:
     - `calculateRentalCost(long rentalDays)`
     - `calculateInsuranceCost(long rentalDays)`
     - `getDailyRentalRate(long rentalDays)`
     - `getDailyInsuranceRate()`

5. **Invoice**
   - Fields: `customerName`, `vehicle`, `reservationStart`, `reservationEnd`, `actualReturnDate`
   - Methods:
     - `printInvoice()`

6. **VehicleRentalSystem (main class)**
   - Contains the main method to run the application.

## Usage

To use the vehicle rental system, create instances of `Car`, `Motorcycle`, or `CargoVan`, and then create an `Invoice` to calculate and print the rental and insurance costs.

### Example

Here is an example of how to use the system:

```java
public class VehicleRentalSystem {
    public static void main(String[] args) {
        // Create a Car instance
        Vehicle car = new Car("Mitsubishi Mirage", 15000.00, 3);

        // Create an Invoice instance
        Invoice invoice1 = new Invoice("John Doe", car, LocalDate.of(2024, 6, 3), LocalDate.of(2024, 6, 13), LocalDate.of(2024, 6, 13));

        // Print the invoice
        invoice1.printInvoice();
    }
}

This will output an invoice with the rental and insurance costs for the specified rental period.

### Other Examples
You can also create instances of Motorcycle and CargoVan similarly:

```java
Vehicle motorcycle = new Motorcycle("Triumph Tiger Sport 660", 10000.00, 20);
Invoice invoice2 = new Invoice("Mary Johnson", motorcycle, LocalDate.of(2024, 6, 3), LocalDate.of(2024, 6, 13), LocalDate.of(2024, 6, 13));
invoice2.printInvoice();

```java
Vehicle motorcycle = new Motorcycle("Triumph Tiger Sport 660", 10000.00, 20);
Invoice invoice2 = new Invoice("Mary Johnson", motorcycle, LocalDate.of(2024, 6, 3), LocalDate.of(2024, 6, 13), LocalDate.of(2024, 6, 13));
invoice2.printInvoice();