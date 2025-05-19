 package com.patients.utils;

import com.patients.managers.DiseaseManager;
import com.patients.managers.PatientManager;
import com.patients.models.Disease;
import com.patients.models.Patient;

public class ObjectInput {
	// Input Details of Patients
	
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
	
	// Select A Patient from the List of Already Registered Patients
	
	public static Patient selectPatient(PatientManager patientManager) {
		patientManager.viewPatients();
		if (patientManager.isEmpty()) {
			return null;
		}
		System.out.println("Please select a patient from the list: ");
		int option = GeneralInput.intInput();
		
		while(true) {
			if (option >= 1 && option <= patientManager.getPatients().length) {
					Patient selectedPatient = patientManager.getPatient(option);						
					if (selectedPatient != null) {
						return selectedPatient;
					} else {
						System.out.println("No patient in that slot, try again");
						System.out.println("Please select a patient from the list: ");
						option = GeneralInput.intInput();
					}
			} else {
				System.out.println("Invalid input, try again");
				System.out.println("Please select a patient from the list: ");
				option = GeneralInput.intInput();
			}
		}
	}
	
	// Input Details of a New Disease
	
	public static Disease getDiseaseDetails() {
		System.out.println("Please enter the name of the disease: ");
		String name = ValidationUtils.getCapitalizedInput();
		
		System.out.println("Please enter the ICD code letter: ");
		String ICDName = ValidationUtils.getValidatedICDName();
		
		System.out.println("Please enter the ICD code number: ");
		int ICDNum = ValidationUtils.getValidatedICDNum();
		
		String ICD = ICDName + "-" + ICDNum;
		
		System.out.println("Please enter the level of severity of the disease (e.g., Mild, Moderate, Severe): ");
		String severityLevel = ValidationUtils.getValidatedSeverityLevel();
		
		System.out.println("Is the disease contagious? (Y/N): ");
		boolean contagious = GeneralInput.getBoolean();
		
		return new Disease(name, ICD, severityLevel, contagious);
	}
	
	// Input Details of a New Disease separately
	
	public static void changeDiseaseName(Disease changingDisease) {
		System.out.println("Please enter the name of the disease: ");
		String newName = ValidationUtils.getCapitalizedInput();
		changingDisease.setName(newName);
	}
	
	public static void changeDiseaseICD(Disease changingDisease) {
		System.out.println("Please enter the ICD code letter: ");
		String ICDName = ValidationUtils.getValidatedICDName();
		
		System.out.println("Please enter the ICD code number: ");
		int ICDNum = ValidationUtils.getValidatedICDNum();
		
		String ICD = ICDName + "-" + ICDNum;
		
		changingDisease.setICD(ICD);
	}
	
	// Select a Disease of Already Registered Diseases
	
	public static Disease selectDisease(DiseaseManager diseaseManager) {
		diseaseManager.viewDiseases();
		
		if (diseaseManager.isEmpty()) {
			return null;
		}
		
		System.out.println("Please select a disease from the list: ");
		int option = GeneralInput.intInput();
		Disease selectedDisease = null;
		
		
		while (selectedDisease == null) {
			try {
				selectedDisease = diseaseManager.getDisease(option);
				break;
			} catch (Exception e) {
				System.out.println("Invalid selection. Please choose a number from the list.");
				option = GeneralInput.intInput();
			}
		}
		
		return selectedDisease;
	}
}
