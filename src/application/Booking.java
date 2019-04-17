/*
 * Class:		Booking
 * Description: The class represents a single booking record for
 * 				any object that can be booked
 * Author: Kobe Friswell - s3783258
 */



package application;

import utilities.DateTime;
import application.Car;
import utilities.DateTime;
import application.Menu;

public class Booking {
	
	private String id;
	private DateTime pickUpDateTime;
	private String firstName;
	private String lastName;
	private int numPassengers;
	private double kilometersTravelled;
	private double tripFee;
	private Car car;
	private String carDetails[] = new String[6];
	boolean valid;
	
	public Booking(String firstName, String lastName, DateTime required, int numPassengers, Car car) {
		// Date of the booking must not be in the past or 1 week in the future
		this.pickUpDateTime = required;
		this.numPassengers = numPassengers;
		this.firstName = firstName;
		this.lastName = lastName;
		this.car = car;
		DateTime currentDate = new DateTime();
		
		this.carDetails = car.toString().split(":");
		valid = true;
		if (DateTime.diffDays(required, currentDate) <= 0) {
			System.out.println("Error - Invalid Date: Before Todays Date");
			this.valid = false;
		}
		
		if (DateTime.diffDays(required, currentDate) > 7) {
			System.out.println("Error - Invalid Date: Too far in future");
			this.valid = false;
		}
		
		// First Name must have a minimum of 3 char
		if (firstName.length() < 3 || firstName.matches("^[a-zA-Z]+$") == false) {
			System.out.println("Error - Invalid First Name (Must be greater than 3 characters)");
			this.valid = false;
		}
		
		// Last Name must have a minimum of 3 char
		if (lastName.length() < 3 || lastName.matches("^[a-zA-Z]+$") == false) {
			System.out.println("Error - Invalid Last Name (Must be greater than 3 characters)");
			this.valid = false;
		}
		
		if (numPassengers > Integer.parseInt(carDetails[4])){
			System.out.println("Error - Too many passengers for car. (Must be less than or equal to "
					+ carDetails[4]+")");
			System.out.println();
			this.valid = false;
		}
		
		// Each booking must have only one car associated with the booking
		id = car.getRegNo() + "_" + firstName.substring(0,3).toUpperCase() + lastName.substring(0,3).toUpperCase() + "_" + required.getEightDigitDate();
	}
	
	public String getDetails() {
		String details = String.format("%-25s %s\n","id:", id)
				+ String.format("%-25s %s\n", "Booking Fee", "$1.50")
				+ String.format("%-25s %s\n", "Pick up Date:", pickUpDateTime.getFormattedDate())
				+ String.format("%-25s %s\n", "Name:", firstName + " " + lastName)
				+ String.format("%-25s %s\n", "Passengers:", numPassengers)
				+ String.format("%-25s %s\n", "Travelled", kilometersTravelled)
				+ String.format("%-25s %s\n", "Trip Fee:", tripFee)
				+ String.format("%-25s %s\n", "Car Id:", car.getRegNo());
		return details;
	}
	
	public String toString() {
		// id:booking fee:date:name:passengers:distance:trip fee:car id
		String str = id + ":" + "1.5" + ":" + pickUpDateTime.getEightDigitDate() + ":" + firstName + " " + lastName + ":" + numPassengers
				+ ":" + kilometersTravelled + ":" + tripFee + ":" + car.getRegNo();
		return str;
	}
	
	public String getId() {
		return id;
	}
	
	public void setCar(Car car) {
		this.car = car;
	}
	
	public boolean getValid() {
		return valid;
	}
	
}
