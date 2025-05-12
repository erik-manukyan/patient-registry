package com.patients.utils;

import java.time.Year;

public class ValidationUtils {
	public static String getCapitalizedInput() {
		String inputStr= GeneralInput.stringInput().toLowerCase();
		return inputStr.substring(0, 1).toUpperCase() + inputStr.substring(1);
	}
	
	public static String getValidatedSex() {
		while(true) {
			String sex = GeneralInput.stringInput().toLowerCase();
			if (sex.equals("male") || sex.equals("m")) return "Male";
			else if (sex.equals("female") || sex.equals("f")) return "Female";
			else System.out.println("Please enter 'Male' or 'Female': ");			
		}
	}
	

	public static int getValidatedBirthYear() {
		int birthYear;
		while (true) {
			birthYear = GeneralInput.intInput();
			if (birthYear > 1900 && birthYear <= Year.now().getValue()) {
				break;
			}
			System.out.println("Enter a valid year of birth (between 1900 and " + Year.now().getValue() + "): ");
		}
		return birthYear;
	}
}
