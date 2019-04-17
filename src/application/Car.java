/*
 * Class:		Car
 * Description: The class represents a single car record for
 * 				any car object that is created.
 * Author: Kobe Friswell - s3783258
 */
package application;

import utilities.DateTime;
import application.Booking;
import application.MiRidesApplication;
import application.Menu;
import java.util.Scanner;

public class Car {
	
	private String regNo;
	private String make;
	private String model;
	private String driverName;
	private int passengerCapacity;
	private boolean available;
	Booking[] currentBookings = new Booking[5];
	private Booking[] pastBookings = new Booking[30];
	Menu menu = new Menu();
	private Car carBooking;
	
	public Car(String regNo, String make, String model, 
			String driverName, int passengerCapacity) {
		//Set variable values from constructor
		this.regNo = regNo.toUpperCase();
		this.make = make;
		this.model = model;
		this.driverName = driverName;
		this.passengerCapacity = passengerCapacity;
		
		
		
		// Checks if 'regNo' is 3 letters followed by 3 numbers
		if (this.regNo.matches("^[A-Z]{3}[0-9]{3}$") == false) {	
			System.out.println("Error - Registration number is invalid");
		}
		
		
		// Making passenger capacity a valid number (1-10)
		if (passengerCapacity < 1) {
			passengerCapacity = 1;
		}
		else if (passengerCapacity > 10) {
			passengerCapacity = 10;
		}
		
	}
	
	public boolean book(String firstName, String lastName, DateTime required, int numPassengers) {
		this.driverName = firstName + " " + lastName;
		this.passengerCapacity = numPassengers;
		DateTime currentDate = new DateTime();
		int count = currentBookings.length;
		boolean validDate = true;
		
		// Bookings cannot be made for a past date
		if (DateTime.diffDays(required, currentDate) <= 0) {
			System.out.println("Invalid Date: Before Todays Date");
			validDate = false;
		}
		
		if (validDate == true) {
			// Check if there is any bookings for that day 
			for (int i = 0; i < count; i++) {
				if (currentBookings[i] != null) {
					
					String[] booking = currentBookings[i].toString().split(":");
					String[] num = booking[2].split("(?<!^)");
					DateTime bookingDate = new DateTime(Integer.parseInt(num[0]+num[1]), 
							Integer.parseInt(num[2]+num[3]),Integer.parseInt(num[4]+num[5]+num[6]+num[7]));
					
					if (DateTime.diffDays(bookingDate,required) == 0) {
						System.out.println("Error: Already Booking For That Day");
						available = false;
					}
				}
				else {
					available = true;
				}
			}
			// Check if any free spots, if so add to next empty
			if (available = true) {
				for (int i = 0; i < count; i++) {
					if (currentBookings[i] == null) {
						currentBookings[i] = new Booking(firstName, lastName, required, numPassengers, carBooking);
						available = true;
						break;
					}
				}
			}
		}
			
		return available;
	}
	public String getDetails() {
		// Should not do the printing
		String details = String.format("%-25s %s\n","RegNo:", regNo)
				+ String.format("%-25s %s\n", "Make & Model:", make + " " + model)
				+ String.format("%-25s %s\n", "Driver Name:", driverName)
				+ String.format("%-25s %s\n", "Capacity:", passengerCapacity)
				+ String.format("%-25s %s\n", "Available:", available);
		return details;
	}
	public String toString() {
		
		String stringDetails = regNo + ":" + make + ":" + model + ":"
				+ driverName + ":" + passengerCapacity + ":" + available;
		return stringDetails;
	}
	public boolean checkDate(DateTime required) {
		boolean requiredAvailable = true;
		for (int i = 0; i < currentBookings.length; i++) {
			if (currentBookings[i] != null) {
				
				String[] booking = currentBookings[i].toString().split(":");
				String[] num = booking[2].split("(?<!^)");
				DateTime bookingDate = new DateTime(Integer.parseInt(num[0]+num[1]), Integer.parseInt(num[2]+num[3]),
						Integer.parseInt(num[4]+num[5]+num[6]+num[7]));
				
				if (DateTime.diffDays(bookingDate,required) == 0) {
					requiredAvailable = false;
				}
				
			}
		}
		return requiredAvailable;
		
	}
	public String getRegNo() {
		return regNo;
	}
	
	public void completeBooking(String regNo, String fullName) {
		int arrayLength = currentBookings.length;
		int bookingValue = 100;
		for (int i = 0; i < arrayLength; i++) {
			if (currentBookings[i] != null) {
				String bookingDetails[] = currentBookings[i].toString().split(":");
				if (bookingDetails[7].equals(regNo) && bookingDetails[3].equals(fullName)) {
					bookingValue = i;
				}
			}
		}
		
		if (bookingValue != 100) {
			System.out.print("Enter Kilometers: ");
			Scanner scanner = new Scanner(System.in);
			double kilometers = scanner.nextInt();
			for (int i = 0; i<pastBookings.length;i++) {
				if (pastBookings[i] == null) {
					pastBookings[i] = currentBookings[bookingValue];
					currentBookings[bookingValue] = null;
					
					System.out.println("Thank you for riding with MiRide. We hope you enjoyed your trip.");
					System.out.println("$"+((kilometers*0.45)+1.5)+ " has been deducted from your acccount.");
					break;
				}
			}
		}
		else {
			System.out.println("Error - Booking not found");
		}
	}

	
	public void setCar(Car car) {
		this.carBooking = car;
	}
}
