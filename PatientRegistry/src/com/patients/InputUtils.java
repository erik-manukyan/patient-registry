package com.patients;

import java.time.Year;
import java.util.Scanner;

public class InputUtils {
	private static final Scanner input = new Scanner(System.in);
	
	public static String getValidatedSex() {
		while(true) {
			String sex = stringInput().toLowerCase();
			if (sex.equals("male") || sex.equals("m")) return "Male";
			else if (sex.equals("female") || sex.equals("f")) return "Female";
			else System.out.println("Please enter 'Male' or 'Female': ");			
		}
	}
	
	public static String getCapitalizedInput() {
		String surname = stringInput().toLowerCase();
		return surname.substring(0, 1).toUpperCase() + surname.substring(1);
	}
	
	public static int getValidatedBirthYear() {
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
	
	// 
	
	public static boolean getBoolean() {
	    while (true) {
	        String answer = stringInput().trim().toLowerCase();
	        if (answer.startsWith("y")) return true;
	        else if (answer.startsWith("n")) return false;
	        System.out.println("ERROR| Please enter Y/N: ");
	    }
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
	
	public static Patient getPatientDetails() {
		System.out.println("Please enter the name of the patient: ");
		String name = getCapitalizedInput();
		
		System.out.println("Please enter the surname of the patient: ");
		String surname = getCapitalizedInput();
		
		System.out.println("Please enter the sex of the patient: ");
		String sex = getValidatedSex();

		System.out.println("Please enter the year of birth of the patient: ");
		int birthYear = getValidatedBirthYear();
		
		return new Patient(name, surname, sex, birthYear); 
	}
	
	public static Disease getDiseaseDetails() {
		System.out.println("Please enter the name of the disease: ");
		String name = getCapitalizedInput();
		
		System.out.println("Please enter the ICD code of the disease: ");
		String ICD = getCapitalizedInput();
		
		System.out.println("Please enter the level of severity of the disease (e.g., Mild, Moderate, Severe): ");
		String severityLevel = getCapitalizedInput();
		
		System.out.println("Please enter if the disease is contagious or not (Y/N): ");
		boolean contagious = getBoolean();
		
		return new Disease(name, ICD, severityLevel, contagious);
	}
}
