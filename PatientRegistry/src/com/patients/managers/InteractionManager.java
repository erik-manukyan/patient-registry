package com.patients.managers;

import com.patients.models.Disease;
import com.patients.models.Patient;
import com.patients.utils.ObjectInput;
import com.patients.utils.ValidationUtils;

public class InteractionManager {
	public static void assignDisease(DiseaseManager diseaseManager, PatientManager patientManager) {
		
		System.out.println("+---------------------------------------------+");
		System.out.println("|               ASSIGN DISEASE                |");
		System.out.println("+---------------------------------------------+");
		
		// Check if there are patients or diseases
		boolean empty = ValidationUtils.checkEmptyObjects(patientManager, diseaseManager);
		if (empty) {
			return;
		}
		
		Patient selectedPatient = ObjectInput.selectPatient(patientManager);
		Disease selectedDisease = ObjectInput.selectDisease(diseaseManager);
		
		if (selectedPatient.containsDisease(selectedDisease)) {
			System.out.println("Disease already assigned to the patient" + selectedPatient + ".");
			return;
		}
		
		selectedPatient.addDisease(selectedDisease);
		
		System.out.println("Disease " + selectedDisease.getName() + " has been successfully assigned to patient '" + selectedPatient.getName() + selectedPatient.getSurname() + "' .");
	}
	
	public static void deleteDisease(DiseaseManager diseaseManager, PatientManager patientManager) {
		System.out.println("+---------------------------------------------+");
		System.out.println("|               DELETE DISEASE                |");
		System.out.println("+---------------------------------------------+");
		
		// Check if there are patients or diseases
		
		boolean empty = ValidationUtils.checkEmptyObjects(patientManager, diseaseManager);
		if (empty) {
			return;
		}
		
		Patient selectedPatient = ObjectInput.selectPatient(patientManager);
	    selectedPatient.deleteDisease();
	    
		System.out.println("Disease successfully removed from the patient '" + selectedPatient.getName() + selectedPatient.getSurname() + "' .");
	}
	
	public static void viewDiseasesByPatient(PatientManager patientManager, DiseaseManager diseaseManager) {
		System.out.println("+---------------------------------------------+");
		System.out.println("|            View Patient Disease             |");
		System.out.println("+---------------------------------------------+");
		Patient selectedPatient = ObjectInput.selectPatient(patientManager);
		if (selectedPatient == null) return;
		System.out.println("+---------------------------------------------+");
		System.out.println("|          Patient's medical record           |");
		System.out.println("+---------------------------------------------+");
		System.out.println(selectedPatient.toString());
		System.out.println("-------------------DISEASES--------------------");
		selectedPatient.showDiseases();
	}
	
	public static void viewPatientsByDisease(PatientManager patientManager, DiseaseManager diseaseManager) {
		System.out.println("+----------------------------------------+");
	    System.out.println("|       View Patients by Disease         |");
		System.out.println("+----------------------------------------+");
		
		Disease selectedDisease = ObjectInput.selectDisease(diseaseManager);
		
		if (selectedDisease == null) {
			System.out.println("There is no diseases in the registry currently.");
			System.out.println("Before interacting please ensure there are diseases registered.");
			return;
		}
		
		boolean found = false;
		
		for (Patient patient : patientManager.getPatients()) {
			if (patient != null && patient.containsDisease(selectedDisease)) {
				System.out.println(patient.toString());
				found = true;
			}
		}
		
		if (!found) {
			System.out.println("No patients have this disease.");
		}
	} 
	
	
	

	
}
