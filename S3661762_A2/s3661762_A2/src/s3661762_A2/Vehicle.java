package s3661762_A2;

public class Vehicle {

	private String regNo;
	private String make;
	private String description;
	private String bookingID;
	private int numPassengers;
	private DateTime bookingDate;
	private int year;
	private int BOOKING_FEE = 100;
	private int PASSENGER_SURCHARGE = 20;
	/*
	 * protected variable that stores the calculated fee & surcharge depending on
	 * the number of passengers
	 */
	protected double totalFee;

	public Vehicle(String regNo, String make, int year, String description) {
		this.regNo = regNo;
		this.make = make;
		this.year = year;
		this.description = description;
		this.bookingID = "N/A";
	}

	public String getRegNo() throws VehicleException {
		return regNo;
	}

	/*
	 * method that records the number of passengers travelling in the vehicle and
	 * throws a VehicleException when the incorrect number of passengers is entered
	 * by the user
	 */
	private boolean recordPassengerNo(int numPassengers) throws VehicleException {
		if ((numPassengers < 1) || (numPassengers > 6)) {

			throw new VehicleException("Error - Invalid Number of passengers: Must be b/w 1 and 6.");
		} else {
			this.numPassengers = numPassengers;
			return true;
		}

	}

	/*
	 * method that calculates the totalFee for a normal sized Vehicle and throws a
	 * VehicleException when the user enters a date that is in the past
	 */
	public double book(int numPassengers, DateTime date) throws VehicleException {

		if (recordPassengerNo(numPassengers) == false) {
			return -1;

		} else if (date.getFormattedDate().compareTo(DateTime.getCurrentTime()) < 0) {

			throw new VehicleException("Error - Date must be in the future.");
		} else {

			bookingID = regNo + date.getEightDigitDate();

		}
		bookingDate = date;

		totalFee = BOOKING_FEE + (PASSENGER_SURCHARGE * numPassengers);
		return totalFee;
	}

	/* method that prints out the vehicle details */
	public String getVehicleDetails() throws VehicleException {

		String firstLine = String.format("%-20s %s\n", "Reg Num:", regNo);
		String secondLine = String.format("%-20s %s\n", "Make:", make);
		String thirdLine = String.format("%-20s %s\n", "Year:", year);
		String fourthLine = String.format("%-20s %s\n", "Description:", description);
		String fifthLine = String.format("%-20s %s\n", "Booking Ref:", bookingID);
		String sixthLine = String.format("%-20s %s\n", "Booking Date:", bookingDate);
		String seventhLine = String.format("%-20s %s\n", "Num Passengers:", numPassengers);
		String eighthLine = String.format("%-20s %s\n", "fee:", "$" + totalFee);
		if (bookingID.equals("N/A")) {
			return firstLine + secondLine + thirdLine + fourthLine + fifthLine;
		} else {
			return firstLine + secondLine + thirdLine + fourthLine + fifthLine + sixthLine + seventhLine + eighthLine;
		}
	}

	/*
	 * another method that prints out the vehicle details for a normal sized vehicle
	 * when writing to the Vehicle.txt file
	 */
	public String getDetailsForVehicle() throws VehicleException {
		String firstLine = regNo + "," + make + "," + year + "," + description + "," + bookingID;
		String secondLine = regNo + "," + make + ",'" + year + "," + description + "," + bookingID + "," + bookingDate
				+ "," + numPassengers + "," + totalFee;

		if (bookingID.equals("N/A")) {
			return firstLine;
		} else {
			return secondLine;
		}

	}

}
