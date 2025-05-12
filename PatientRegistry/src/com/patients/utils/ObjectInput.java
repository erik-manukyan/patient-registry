package com.patients.utils;

import com.patients.models.Disease;
import com.patients.models.Patient;

public class ObjectInput {
	public static Patient getPatientDetails() {
		System.out.println("Please enter the name of the patient: ");
		String name = ValidationUtils.getCapitalizedInput();
		
		System.out.println("Please enter the surname of the patient: ");
		String surname = ValidationUtils.getCapitalizedInput();
		
		System.out.println("Please enter the sex of the patient: ");
		String sex = ValidationUtils.getValidatedSex();

		System.out.println("Please enter the year of birth of the patient: ");
		int birthYear = ValidationUtils.getValidatedBirthYear();
		
		return new Patient(name, surname, sex, birthYear); 
	}
	
	public static Disease getDiseaseDetails() {
		System.out.println("Please enter the name of the disease: ");
		String name = ValidationUtils.getCapitalizedInput();
		
		System.out.println("Please enter the ICD code of the disease: ");
		String ICD = ValidationUtils.getCapitalizedInput();
		
		System.out.println("Please enter the level of severity of the disease (e.g., Mild, Moderate, Severe): ");
		String severityLevel = ValidationUtils.getCapitalizedInput();
		
		System.out.println("Is the disease contagious? (Y/N): ");
		boolean contagious = GeneralInput.getBoolean();
		
		return new Disease(name, ICD, severityLevel, contagious);
	}
}
