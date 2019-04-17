/*
 * Class:		Menu
 * Description: This class prints the menu and takes input from the user
 * Author: Kobe Friswell - s3783258
 */

package application;

import java.util.Scanner;
import application.MiRidesApplication;
import application.Car;

public class Menu {
	Scanner scanner = new Scanner(System.in);
	private Car car;
	private MiRidesApplication miApp = new MiRidesApplication();
	static String regNo;
	
	/*
	 * ALGORITHM
	 * BEGIN
	 * 		WHILE valid option isn't selected
	 * 			IF user input is valid
	 * 				VALID = true
	 * 			ELSE
	 * 				PRINT invalid user input
	 * END		
	 */
	
	public void menu() {
		System.out.println("*** MiRides System Menu ***");
		String menu = String.format("%-25s %s\n","Create Car", "CC")
				+ String.format("%-25s %s\n", "Book Car", "BC")
				+ String.format("%-25s %s\n", "Complete Booking", "CB")
				+ String.format("%-25s %s\n", "Display ALL Cars", "DA")
				+ String.format("%-25s %s\n", "Search Specific Car", "SS")
				+ String.format("%-25s %s\n", "Search Available Cars", "SA")
				+ String.format("%-25s %s\n", "Seed Data", "SD")
				+ String.format("%-25s %s\n", "Exit Program", "EX");
		System.out.print(menu);
		
		
		boolean valid = false;
		while (valid == false) {
			String option = scanner.nextLine();
			if (option.toUpperCase().equals("CC")) {
				this.createCar();
				valid = true;
			}
			else if (option.toUpperCase().equals("BC")){
				this.bookCar();
				valid = true;
			}
			else if (option.toUpperCase().equals("CB")) {
				this.completeBooking();
				valid = true;
			}
			else if (option.toUpperCase().equals("DA")) {
				this.displayAllCars();
				valid = true;
			}
			else if (option.toUpperCase().equals("SS")) {
				this.displaySpecificCar();
				valid = true;
			}
			else if (option.toUpperCase().equals("SD")) {
				this.seedData();
				valid = true;
			}
			else if (option.toUpperCase().equals("")) {
				valid = false;
			}
			else {
				System.out.println("Invalid Input please input again");
			}
		}
	}
	public void createCar() {
		boolean valid = false;
		while (valid == false) {
			System.out.print("Enter Registration Number: ");
			regNo = scanner.nextLine().toUpperCase();
			
			if (regNo.matches("^[A-Z]{3}[0-9]{3}$") == false) {	
				System.out.println("Error - Registration number is invalid.");
			}
			else {
				valid = true;
			}
		}
		System.out.print("Enter Car Make: ");
		String make = scanner.nextLine();
		
		System.out.print("Enter Car Model: ");
		String model = scanner.nextLine();
		
		System.out.print("Enter Driver Name: ");
		String driverName = scanner.nextLine();
		
		System.out.print("Enter Car Passenger Capacity: ");
		int passengerCapacity = scanner.nextInt();
		
		car = new Car(regNo, make, model, driverName, passengerCapacity);
		
		miApp.createCar(car);
		
		this.menu();
		
	}
	public void bookCar() {
		boolean valid = false;
		String date = null;
		while (valid == false) {
			System.out.print("Enter Date Required (DD/MM/YYYY): ");
			date = scanner.next();
			if (date.matches("^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$") == false ) {
				System.out.println("Error - Invalid Date (DD/MM/YYYY)");
			}
			else {
				valid = true;
			}
			
		}
		miApp.bookCar(date);
		this.menu();
	}
	public void completeBooking() {
		String input = null;
		boolean valid = false;
		System.out.print("Enter Registration or Booking Date: ");
		while (valid == false) {
			input = scanner.next().toUpperCase();
			if (input.matches("^[A-Z]{3}[0-9]{3}$") == false && input.matches("^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$") == false  ) {	
				System.out.println("Error - Please enter valid registration or booking date");
			}
			else {
				valid = true;
			}
		}
		
		System.out.print("Enter First Name: ");
		String firstName = scanner.next();
		System.out.print("Enter Last Name: ");
		String lastName = scanner.next();
		
		String fullName = firstName + " " + lastName;
		car.completeBooking(input,fullName);
		this.menu();
		
	}
	
	public void displayAllCars() {
		miApp.displayAllCars();
		this.menu();
	}
	
	public void displaySpecificCar() {
		boolean valid = false;
		while (valid == false) {
			System.out.print("Enter Registration Number: ");
			regNo = scanner.next().toUpperCase();
			
			if (regNo.matches("^[A-Z]{3}[0-9]{3}$") == false) {	
				System.out.println("Error - Registration number is invalid.");
			}
			else {
				valid = true;
			}
		}
		miApp.displaySpecificCar(regNo);
		
		this.menu();
		
	}
	
	public void seedData() {
		car = new Car("ABC123", "Toyota" , "Hilux", "Benny Sharp", 4);
		miApp.createCar(car);
		car = new Car("REG123", "Mazda", "3", "Jeremy Jensen", 4);
		miApp.createCar(car);
		car = new Car("DEF123", "Toyota" , "RAV4", "Thomas Lowery", 4);
		miApp.createCar(car);
		car = new Car("HIJ123", "Honda", "CR-V", "Jeremy Jensen", 6);
		miApp.createCar(car);
		car = new Car("ZXZ123", "Honda" , "Civic", "Madeleine Barrow", 4);
		miApp.createCar(car);
		car = new Car("CBA123", "BMW", "X5", "Darcy Francis", 6);
		miApp.createCar(car);
		
		this.menu();
	}
	
	public String getRegNo() {
		return regNo;
	}
	
	public Car getCar() {
		return car;
	}
}

