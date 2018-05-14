package s3661762_A2;

import java.util.*;

public class OversizedVehicle extends Vehicle {

	private double weight;
	private String category;
	private final double CLEARANCE_HEIGHT;
	private final double LIGHT_VEHICLE_CHARGE = 10.0;
	private final double MEDIUM_VEHICLE_CHARGE = 20.0;
	private final double HEAVY_VEHICLE_CHARGE = 50.0;

	public OversizedVehicle(String regNo, String make, int year, String description, double CLEARANCE_HEIGHT) {
		super(regNo, make, year, description);
		category = "N/A";
		this.CLEARANCE_HEIGHT = CLEARANCE_HEIGHT;
	}

	/*
	 * method that assigns determines the category of the vehicle depending on its
	 * weight
	 */
	private void recordWeight(double weight) {

		this.weight = weight;
		if (weight <= 3) {
			category = "N/A";
		} else if ((weight >= 3.1) && (weight <= 4.5)) {
			category = "LIGHT";
		} else if ((weight >= 4.51) && (weight <= 8.0)) {
			category = "MEDIUM";
		} else if (weight > 8.0) {
			category = "HEAVY";
		}

	}
	
	public void checkingHeight()throws VehicleException{
		if(CLEARANCE_HEIGHT > 3){
			throw new VehicleException("Error - the vehicle is too tall to fit in the ferry!");
		}
	}

	/*
	 * method that calculates the totalFee for an Oversized Vehicle and throws an
	 * exception if the user enters an invalid weight
	 */
	public double book(int numPassengers, DateTime date, double weight) throws VehicleException {
		double surcharge = 0;
		if ((numPassengers < 1) || (numPassengers > 6)) {
			throw new VehicleException("Error - Invalid Number of passengers: Must be b/w 1 and 6.");
		}
		else if (date.getFormattedDate().compareTo(DateTime.getCurrentTime()) < 0) {

			throw new VehicleException("Error - Date must be in the future.");
		}
		recordWeight(weight);
		if (CLEARANCE_HEIGHT > 3) {
			//return -3;
		} else if (category.equals("LIGHT")) {
			surcharge = LIGHT_VEHICLE_CHARGE;

		} else if (category.equals("MEDIUM")) {
			surcharge = MEDIUM_VEHICLE_CHARGE;

		} else if (category.equals("HEAVY")) {
			surcharge = HEAVY_VEHICLE_CHARGE;
		}
		if (weight <= 0) {
			throw new VehicleException("Error - weight must be greater than 0!");
		}
		
		double totalFee = super.book(numPassengers, date) + ((weight - 3) * surcharge);

		return totalFee;
	}
	/* method that prints out the vehicle details for an oversized vehicle */
	public String getVehicleDetails() throws VehicleException {

		if (weight == 0) {
			String ninthLine = String.format("%-20s %s\n", "Clearance Height: ", CLEARANCE_HEIGHT);
			return super.getVehicleDetails() + ninthLine;
		}
		System.out.println();
		String ninthLine = String.format("%-20s %s\n", "Clearance Height: ", CLEARANCE_HEIGHT);
		String tenthline = String.format("%-20s %s\n", "Weight:  ", weight);
		String eleventhLine = String.format("%-20s %s\n", "Category: ", category);
		return super.getVehicleDetails() + ninthLine + tenthline + eleventhLine;

	}

	/*
	 * another method that prints out the vehicle details for an oversized Vehicle
	 * when writing to the Vehicle.txt file
	 */
	public String getDetailsForVehicle() throws VehicleException {
		if (weight == 0) {
			String thirdLine = "," + CLEARANCE_HEIGHT;
			return super.getDetailsForVehicle() + thirdLine;
		}
		String tenthLine = "," + CLEARANCE_HEIGHT + "," + weight + "," + "," + category;
		return super.getDetailsForVehicle() + tenthLine;
	}
}

