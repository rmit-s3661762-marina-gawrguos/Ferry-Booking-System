package s3661762_A2;

import java.util.*;
import java.io.*;

public class BookingSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BookingSystem system = new BookingSystem();
		system.displayMenu(); // calling the displayMenu method

	}

	private Vehicle[] allVehicles = new Vehicle[1000];
	int counter = 0;

	public void displayMenu() {
		while (true) {
			System.out.println("*** Vehicle Booking System Menu ***");
			System.out.println("Seed Data                        A");
			System.out.println("Add vehicle                      B");
			System.out.println("Display Vehicles                 C");
			System.out.println("Book Passage                     D");
			System.out.println("Exit Program                     X");
			Scanner keyboard = new Scanner(System.in);
			String letter = keyboard.nextLine();

			if (letter.equals("A")) {
				seedData();
			} else if (letter.equals("B")) {
				addVehicle();
			} else if (letter.equals("C")) {
				displayVehicles();
			} else if (letter.equals("D")) {
				bookPassage();
			} else if (letter.equals("X")) {
				exitProgram();
			}
		}

	}

	public void seedData() {

		/* hard coded Vehicles that are added to the allVehicles array */
		Vehicle vehicle1 = new Vehicle("SIM479", "Honda", 2007, "White Sedan");
		Vehicle vehicle2 = new Vehicle("RAM153", "Holden", 2003, "Red Fire");
		allVehicles[0] = vehicle1;
		allVehicles[1] = vehicle2;
		Vehicle vehicle3 = new Vehicle("SCA888", "Volvo", 2003, "Burnt Orange");
		DateTime date = new DateTime(20, 9, 2018);
		try {
			vehicle3.book(3, date);
		} catch (VehicleException ve) {

			System.out.println(ve.getMessage());
		}
		Vehicle vehicle4 = new Vehicle("MUF122", "Maserati", 2006, "Midnight Black");
		DateTime date1 = new DateTime(21, 11, 2018);
		try {
			vehicle4.book(4, date1);
		} catch (VehicleException ve) {

			System.out.println(ve.getMessage());
		}
		allVehicles[2] = vehicle3;
		allVehicles[3] = vehicle4;
		OversizedVehicle vehicle5 = new OversizedVehicle("PUM234", "Lexus", 2007, "Red Sedan", 1.1);
		OversizedVehicle vehicle6 = new OversizedVehicle("CAV169", "DAF", 2017, "Driveless Light", 2.5);
		OversizedVehicle vehicle7 = new OversizedVehicle("BOM555", "DAF", 2015, "Reliable Heavy Haul", 2.6);
		allVehicles[4] = vehicle5;
		allVehicles[5] = vehicle6;
		allVehicles[6] = vehicle7;
		OversizedVehicle vehicle8 = new OversizedVehicle("WAL691", "DAF", 2015, "Superfast Medium", 2.4);
		DateTime date2 = new DateTime(15, 10, 2018);
		try {
			vehicle8.book(3, date2, 4.5);
		} catch (VehicleException ve) {

			System.out.println(ve.getMessage());
		}
		OversizedVehicle vehicle9 = new OversizedVehicle("CAC545", "Ventura", 2016, "Passenger coach", 2.9);
		DateTime date3 = new DateTime(03, 10, 2018);
		try {
			vehicle9.book(4, date3, 6);
		} catch (VehicleException ve) {

			System.out.println(ve.getMessage());
		}
		OversizedVehicle vehicle10 = new OversizedVehicle("VIM333", "Ferrari", 2017, "Purple Sky", 3.0);
		DateTime date4 = new DateTime(12, 10, 2018);
		try {
			vehicle10.book(2, date4, 9);
		} catch (VehicleException ve) {

			System.out.println(ve.getMessage());
		}
		allVehicles[7] = vehicle8;
		allVehicles[8] = vehicle9;
		allVehicles[9] = vehicle10;
		counter = 10;

	}

	/* method that adds a vehicle into the system */
	public void addVehicle() {

		Scanner keyboard = new Scanner(System.in).useDelimiter("\r\n");
		System.out.println("*** Add Vehicle ***");
		System.out.println("Enter vehicle registration: ");
		String regNo = keyboard.nextLine();
		/*
		 * if the user enters a registration number that already exists, an
		 * error message will be shown to the user
		 */

		if (doesVehicleRegNoExist(regNo) == true) {
			System.out.println("Error - Registration " + regNo + " already exists in the System!");
		} else {
			System.out.println("Enter Vehicle make: ");
			String make = keyboard.nextLine();
			System.out.println("Enter vehicle Year: ");
			int year = keyboard.nextInt();
			System.out.println("Enter Description: ");
			String description = keyboard.next();

			try {
				System.out.println("Enter height: ");
				double height = keyboard.nextDouble();

				if (height <= 1) {
					Vehicle v = new Vehicle(regNo, make, year, description);
					allVehicles[counter] = v;
					counter++;
					System.out.println("New Vehicle added successfully for registration" + regNo);

				} else {

					OversizedVehicle ov = new OversizedVehicle(regNo, make, year, description, height);
					ov.checkingHeight();
					allVehicles[counter] = ov;
					counter++;
					System.out.println("New Oversized Vehicle added successfully for registration" + regNo);
				}
			} catch (VehicleException ve) {
				// TODO Auto-generated catch block
				System.out.println(ve.getMessage());
			}
		}

	}

	/* method to check and see if a Vehicle registration already exists */
	public boolean doesVehicleRegNoExist(String regNo) {
		for (int i = 0; i < counter; i++) {
			Vehicle currentVehicle = allVehicles[i];
			try {
				if (regNo.equals(currentVehicle.getRegNo())) {
					return true;
				}
			} catch (VehicleException ve) {
				// TODO Auto-generated catch block
				System.out.println(ve.getMessage());
			}
		}
		return false;
	}

	/* method that displays and prints the vehicle details */
	public void displayVehicles() {
		try {
			for (int i = 0; i < counter; i++) {
				System.out.println(allVehicles[i].getVehicleDetails());
				// counter++;
			}
		} catch (VehicleException ve) {
			// TODO Auto-generated catch block
			System.out.println(ve.getMessage());
		}

	}

	/* method created to allow for booking a vehicle */
	public void bookPassage() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter registration number: ");
		String regNo = keyboard.next();
		/*
		 * variable created to only iterate once through the loop instead of
		 * iterating through the length of the allVehicles array
		 */
		boolean executeOnce = true;

		for (int i = 0; i < allVehicles.length; i++) {
			if (doesVehicleRegNoExist(regNo) == false) {
				System.out.println("Error- registration number not found");
				break;
			}

			if (executeOnce == true) {
				System.out.println("Enter day of month: ");
				int day = keyboard.nextInt();
				System.out.println("Enter month: ");
				int month = keyboard.nextInt();
				System.out.println("Enter year: ");
				int year = keyboard.nextInt();
				DateTime date = new DateTime(day, month, year);
				if (date.getFormattedDate().compareTo(DateTime.getCurrentTime()) < 0) {
					System.out.println("Error - date must be in the future");
				} else {
					try {
						System.out.println("Enter Passengers: ");
						int numPassengers = keyboard.nextInt();

						if (allVehicles[i] instanceof OversizedVehicle) {
							System.out.println("Enter Weight: ");
							double weight = keyboard.nextDouble();
							((OversizedVehicle) allVehicles[i]).book(numPassengers, date, weight);

							// i=0;
						}

						counter = 0;
						System.out.println(
								"Booking for " + regNo + " on " + day + "/" + month + "/" + year + " was successful.");
						System.out.println("The total cost of the booking is: " + "$"
								+ allVehicles[counter].book(numPassengers, date));
						counter++;
					} catch (VehicleException ve) {

						System.out.println(ve.getMessage());
					}
				}
			}
			executeOnce = false;
		}
	}

	/*
	 * method that allows to the user to exit the program whilst writing to the
	 * Vehicles.txt file the vehicle details
	 */
	public void exitProgram() {
		String fileName = "Vehicles.txt";
		PrintWriter output = null;

		try {
			output = new PrintWriter(new FileOutputStream(fileName, true));
			for (int i = 0; i < counter; i++) {
				if (allVehicles[i] instanceof OversizedVehicle) {
					output.println(allVehicles[i].getDetailsForVehicle() + "");
				} else {
					output.println(allVehicles[i].getDetailsForVehicle() + "");
				}
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());

		} catch (VehicleException ve) {
			System.out.println(ve.getMessage());
		}
		output.close();
	}
}
