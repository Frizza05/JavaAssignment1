/*
 * Class:		MiRidesApplication
 * Description: The class processes and store car objects
 * Author: Kobe Friswell - s3783258
 */

package application;

import application.Booking;
import application.Car;
import utilities.DateTime;
import application.Menu;
import java.util.Scanner;

public class MiRidesApplication {	
	
	private Car car[] = new Car[10];
	int arrayLength = car.length;
	Scanner scanner = new Scanner(System.in);
	private Car carBooking;
	private int carSelect;
	
	public void createCar(Car newCar) {
		Menu menu = new Menu();
		
		// Check if all elements of the array are empty
		boolean emptyArray = true;
		for (int i = 0; i < arrayLength; i++) {
			if (car[i] != null) {
				emptyArray = false;
	
			}
		}
		// Write to first element as array is empty
		if (emptyArray == true) {
			car[0] = newCar;
			System.out.println("New Car added successfully for registration number: " + menu.getRegNo());
			System.out.println();
		}
		
		else {
			// check whole array
			boolean unique = true;
			for (int i = 0; i < arrayLength; i++) {
				if (car[i] != null) {
					String carSplit[] = car[i].toString().split(":");
					String newCarSplit[] = newCar.toString().split(":");
					if (carSplit[0].equals(newCarSplit[0])) {
						System.out.println("Error - already exists in the system.");
						System.out.println();
						unique = false;
						break;
					}
					
				
				}	
			}
			if (unique == true) {
				for (int i = 0; i < arrayLength; i++) {
					if (car[i] == null) {
						car[i] = newCar;
						System.out.println("New Car added successfully for registration number: " + menu.getRegNo());
						System.out.println();
						break;
					}
			
				}
			
			}
		}
	}
	
	public void bookCar(String date) {
		String splitDate[] = date.split("/");
		DateTime bookingDate = new DateTime(Integer.parseInt(splitDate[0]), 
				Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[2]));
		
		Car availableCars[] = new Car[10];
		int counter = 0; 
		for (int i = 0; i<arrayLength; i++) {
			if (car[i] != null) {
				if (car[i].checkDate(bookingDate) == true) {
					availableCars[counter] = car[i];
					counter++;
				}
			}
		}	
		if (counter == 0) {
			System.out.println("Error - No cars are available on this date");
		}
		else {
			System.out.println();
			System.out.println("The following cars are available:");
			System.out.println();
			int num = 1;
			for (int i = 0; i<arrayLength; i++) {
				if (availableCars[i] != null) {
					String carSplit[] = availableCars[i].toString().split(":");
					System.out.println(num + ". " + carSplit[0]);
					num++;
				}
			}
			System.out.println();
			System.out.print("Please select the number next to the car you wish to book: ");
			boolean valid = false;
			carSelect = 0;
			
			while (valid == false) {
				carSelect = scanner.nextInt();
				if (carSelect < 1 || carSelect > num-1) {
					System.out.println("Error - Please pick a number from the list");
				}
				else {
					valid = true;
				}
			}
			
			System.out.print("Enter First Name: ");
			String firstName = scanner.next();
			System.out.print("Enter Last Name: ");
			String lastName = scanner.next();
			System.out.print("Enter Number of Passengers: ");
			int numPassengers = scanner.nextInt();
			
			carBooking = car[carSelect-1];
			
			Booking booking = new Booking(firstName, lastName, bookingDate, numPassengers, carBooking);
			if (booking.getValid() == true) {
				carBooking.setCar(carBooking);
				
				if (carBooking.book(firstName, lastName, bookingDate, numPassengers) == true) {
					String carDetails[] = carBooking.toString().split(":");
					System.out.println("Thank you for your booking. " + carDetails[3] + " will pick you up on " 
							+ bookingDate.getFormattedDate() + ".");
					System.out.println("Your booking reference is: " + booking.getId());
					System.out.println();
				}
			}
			else {
				System.out.println("**Returning to Menu**");
				System.out.println();
			}
		}	
	}
	public void displayAllCars() {
		int counter = 1;
		boolean valid = false;
		for (int i = 0; i < arrayLength; i++) {
			if (car[i] != null) {
				String[] carSplit = car[i].toString().split(":");
				System.out.println(counter + ". " + carSplit[0]);
				counter++;
				valid = true;
			}
		}
		if (valid == false) {
			System.out.println("Error - No cars in the system");
			System.out.println();
		}
	}
	public void displaySpecificCar(String regNo) {
		boolean valid = false;
		for (int i = 0; i < arrayLength; i++) {
			if (car[i] != null) {
				String[] carSplit = car[i].toString().split(":");
				String existingRegNo = carSplit[0];
				if (existingRegNo.equals(regNo)) {
					System.out.println(car[i].getDetails());
					valid = true;
				}
			}
		}
		if (valid == false) {
			System.out.println("Error - The car could not be located");
		}
	}
	public Car getCar() {
		carBooking = car[carSelect-1];
		return carBooking;
	}
}	