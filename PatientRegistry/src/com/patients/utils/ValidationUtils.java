package com.patients.utils;

import java.time.Year;

import com.patients.managers.DiseaseManager;
import com.patients.managers.PatientManager;

public class ValidationUtils {
	public static String getCapitalizedInput() {
		String inputStr= GeneralInput.stringInput().toLowerCase();
		while(!hasOnlyLetters(inputStr)) {
			System.out.println("Please enter a name with letters only");
			inputStr= GeneralInput.stringInput().toLowerCase();
		}
		return inputStr.substring(0, 1).toUpperCase() + inputStr.substring(1);
	}
	
	private static boolean hasOnlyLetters(String name) {
		 return name.matches("^[A-Za-z]+$");
	}
	
	public static String getValidatedSex() {
		while(true) {
			String sex = GeneralInput.stringInput().toLowerCase();
			if (sex.equals("male") || sex.equals("m")) return "Male";
			else if (sex.equals("female") || sex.equals("f")) return "Female";
			else System.out.println("Please enter 'Male' or 'Female': ");			
		}
	}
	
	public static String getValidatedICDName() {
		String name = getCapitalizedInput();
		while(true){
			if (name.length() == 1) {
				return name;
			} else {
				System.out.println("Please enter a single letter: ");
				name = getCapitalizedInput();
			}
		}
	}
	
	public static int getValidatedICDNum() {
		int num = GeneralInput.intInput();
		while (num < 0 || num > 99) {
			System.out.println("Please enter number 0-99");
			num = GeneralInput.intInput();
		}
		return num;
	}
	
	public static String getValidatedSeverityLevel() {
		while(true) {
			String severityLevel = GeneralInput.stringInput().toLowerCase();
			
			switch (severityLevel) {
				case "mild":  return "Mild";
				case "moderate": return "Moderate";
				case "severe": return "Severe";
				default: {
					System.out.println("Please enter from options Mild/Moderate/Severe");
				}
			}				
		
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
	
	
	public static boolean checkEmptyObjects(PatientManager patientManager, DiseaseManager diseaseManager){
		
		if (patientManager.isEmpty()) {
			System.out.println("No patients available to assign diseases. ");
			return true;
		} else if (diseaseManager.isEmpty()) {
			System.out.println("No diseases available to assign. ");
			return true;
		} else return false;
		
	}
	
	
}
