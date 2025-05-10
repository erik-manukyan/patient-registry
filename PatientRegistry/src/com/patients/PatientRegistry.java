package com.patients;

import java.time.Year;
import java.util.Scanner;

public class PatientRegistry {
	
	// Global variables
	
	private static int[] unitBeds = null;
	private static Patient[] patients = null;
	private static final Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Welcome to the Patient Registry");
		initialiseBeds(getBeds());
		runMenu();
	}
	
	public static int getBeds() {
		boolean valid = false;
		int bedAmount = -1;
		while (bedAmount <= 0) {
			System.out.println("Before continuing the entry, please enter the amount of beds in medical unit: ");
			bedAmount = intInput();
			if (bedAmount <= 0) {
				System.out.println("Please enter a valid amount of beds in medical unit: ");
			}
		}
		return bedAmount;
	}
	
	public static void initialiseBeds(int bedAmount) {
		unitBeds = new int[bedAmount];
		patients = new Patient[bedAmount];
	}
	
	public static void runMenu() {
		int option;
		boolean cont = true;
		
		while(cont) {
			option = getOption();
			switch (option) {
				case 0:
					cont = false;
					break;
				case 1:
					addPatient();
					break;
				case 2:
					addDisease();
					break;
				case 3:
					assignDisease();
					break;
				case 4:
					viewPatient();
					break;
				case 5:
					viewDisease();
					break;
				default:
					System.out.println("Option not available. Please select a valid option: ");
			}
		}
	}
	
	public static int getOption(){
		int option;
		while(true) {
			System.out.println();
			System.out.println("+---------------------------------------------+");
			System.out.println("|                MAIN MENU                    |");
            System.out.println("+---------------------------------------------+");
            System.out.println("|  1) Add New Patient                         |");
            System.out.println("|  2) Add New Disease                         |");
            System.out.println("|  3) Assign Disease to Patient               |");
            System.out.println("|  4) View All Patients                       |");
            System.out.println("|  5) View All Diseases                       |");
            System.out.println("|  0) Quit                                    |");
            System.out.println("+---------------------------------------------+");
            System.out.print("Please select an option: ");
            
            option = intInput();
            
            if (option >= 0 && option <= 5) {
            	return option;
            } else {
				System.out.println("Please select a valid menu option (0-5). ");
			}
		}
	}
	
	private static void addPatient() {
		System.out.println("+---------------------------------------------+");
		System.out.println("|               ADD NEW PATIENT               |");
		System.out.println("+---------------------------------------------+");
		
		if(!hasAvailableBed()) {
			System.out.println("Currently no space available.");
			return;
		}
		
		for (int bed = 0; bed < patients.length; bed++) {
			if (patients[bed] == null) {
				patients[bed] = getPatientDetails();
				System.out.println("Patient registered! Patient No " + (bed + 1));
				return;
			}
		}
	}
	
	private static void addDisease() {
		System.out.println("+---------------------------------------------+");
		System.out.println("|               ADD NEW DISEASE               |");
		System.out.println("+---------------------------------------------+");
		
	}	
	
	private static void assignDisease() {
		System.out.println("IT WORKS!");
	}
	
	// Function to VIEW PATIENTS 
	
	private static void viewPatient() {
		System.out.println("+---------------------------------------------+");
		System.out.println("|                VIEW PATIENTS                |");
		System.out.println("+---------------------------------------------+");
		for (Patient patient : patients) {
			if (patient != null) {
				patient.GetFullInfo();
			} 
		}
	}
	
	private static void viewDisease() {
		System.out.println("IT WORKS!");
	}
	
	// Function of inputing and checking input of STRINGS
	
	public static String stringInput() {
		String string;
		while (true) {
			try {
				string = input.nextLine().trim();
				if (!string.isEmpty()) {
					return string;
				} else {
					System.out.println("ERROR| You did not input anything: ");
				}
			} catch (Exception e) {
				System.out.println("ERROR| Enter a valid input: ");
				input.nextLine();
			}
		}
	}
	
	
	// Function of inputing and checking the input of INTEGERS
	
	public static int intInput() {
		while(true) {
			try {
				int num = input.nextInt();
				input.nextLine();
				return num;
			} catch (Exception e) {
				System.out.println("ERROR| Enter a valid number: ");
				input.nextLine();
			}
		}
	}
	
	private static Patient getPatientDetails() {
		System.out.println("Please enter the name of the patient: ");
		String name = getValidatedName();
		
		System.out.println("Please enter the surname of the patient: ");
		String surname = getValidatedName();
		
		System.out.println("Please enter the sex of the patient: ");
		String sex = getValidatedSex();

		System.out.println("Please enter the year of birth of the patient: ");
		int birthYear = getValidatedBirthYear();
		
		return new Patient(name, surname, sex, birthYear); 
	}
	
	private static boolean hasAvailableBed() {
		for (Patient patient : patients) {
			if (patient == null) {
				return true;
			}
		}
		return false;
	}
	
	private static String getValidatedSex() {
		while(true) {
			String sex = stringInput().toLowerCase();
			if (sex.equals("male") || sex.equals("m")) {
				return "Male";
			} else if (sex.equals("female") || sex.equals("f")){
				return "Female";
			} else {
				System.out.println("Please enter 'Male' or 'Female': ");
			}
		}
	}
	
	private static String getValidatedName() {
		String surname = stringInput().toLowerCase();
		return surname.substring(0, 1).toUpperCase() + surname.substring(1);
	}
	
	private static int getValidatedBirthYear() {
		int birthYear;
		while (true) {
			birthYear = intInput();
			if (birthYear > 1900 && birthYear <= Year.now().getValue()) {
				break;
			}
			System.out.println("Enter a valid year of birth (between 1900 and " + Year.now().getValue() + "): ");
		}
		return birthYear;
	}
}
