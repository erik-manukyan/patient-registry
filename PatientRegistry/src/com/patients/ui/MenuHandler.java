package com.patients.ui;

import com.patients.managers.DiseaseManager;
import com.patients.managers.PatientManager;
import com.patients.models.Disease;
import com.patients.models.Patient;
import com.patients.utils.GeneralInput;

public class MenuHandler {
	private PatientManager patientManager;
	private DiseaseManager diseaseManager;
	
	public MenuHandler(PatientManager patientManager, DiseaseManager diseaseManager) {
		this.patientManager = patientManager;
		this.diseaseManager = diseaseManager;
	}
	
	public void run() {
		boolean running = true;
		while(running) {
			displayMainMenu();
			int option = GeneralInput.intInput();
			
			switch (option) {
				case 1 -> PatientMenu.handlePatientMenu(patientManager);
				case 2 -> DiseaseMenu.handleDiseaseMenu(diseaseManager);
				case 3 -> assignDisease();
				case 4 -> viewPatientDiseases();
				case 5 -> viewPatientsByDisease();
				case 0 -> running = false;
				default -> System.out.println("Invalid option. ");
			}
		}
	}
	
	private void displayMainMenu() {
		System.out.println("\n+------------- MAIN MENU -------------+");
        System.out.println("| 1) Patient Options                  |");
        System.out.println("| 2) Disease Options                  |");
        System.out.println("| 3) Assign Disease to Patient        |");
        System.out.println("| 4) View Diseases of a Patient       |");
        System.out.println("| 5) View Patients by Disease         |");
        System.out.println("| 0) Exit                             |");
        System.out.println("+-------------------------------------+");
        System.out .println("| Select an option:                   |");        
	}
	
	private void assignDisease() {
		System.out.println("+---------------------------------------------+");
		System.out.println("|               ASSIGN DISEASE                |");
		System.out.println("+---------------------------------------------+");
		
		// Check if there are patients
		
		if (patientManager.isEmpty()) {
			System.out.println("No patients available to assign diseases. ");
			return;
		}
		
		if (diseaseManager.isEmpty()) {
			System.out.println("No diseases available to assign. ");
			return;
		}
		
		// Main process of selecting a patient
		
		patientManager.viewPatients();
		System.out.println("Please select a patient from the list: ");
		int option = GeneralInput.intInput();
		Patient selectedPatient = null;
		
		while(selectedPatient == null) {
			try {
				selectedPatient = patientManager.getPatient(option);	
				break;
			} catch (NullPointerException e) {
				System.out.println("Please enter a valid number. ");
				option = GeneralInput.intInput();
			}
		}

		// Main process of selecting a disease
		
		diseaseManager.viewDiseases();
		System.out.println("Please select a disease from the list: ");
		option = GeneralInput.intInput();
		Disease addingToPatient = null;
		
		
		while (addingToPatient == null) {
			try {
				addingToPatient = diseaseManager.getDisease(option);
				break;
			} catch (Exception e) {
				System.out.println("Invalid selection. Please choose a number from the list.");
				option = GeneralInput.intInput();
			}
		}
		
		if (selectedPatient.containsDisease(addingToPatient)) {
			System.out.println("Disease already assigned to the patient" + selectedPatient + ".");
			return;
		}
		
		selectedPatient.addDisease(addingToPatient);
		
		System.out.println("Disease " + addingToPatient.getName() + " has been successfully assigned to patient '" + selectedPatient.getName() + "' .");
	}
	
	private void viewPatientDiseases() {
		System.out.println("+---------------------------------------------+");
		System.out.println("|            View Patient Disease             |");
		System.out.println("+---------------------------------------------+");
		System.out.println("Please select a patient: ");
		patientManager.viewPatients();
		int option = GeneralInput.intInput();
		Patient selectedPatient = patientManager.getPatient(option);
		System.out.println("+---------------------------------------------+");
		System.out.println("|          Patient's medical record           |");
		System.out.println("+---------------------------------------------+");
		selectedPatient.showDiseases();
	}
	
	private void viewPatientsByDisease() {
		System.out.println("+----------------------------------------+");
	    System.out.println("|       View Patients by Disease         |");
		System.out.println("+----------------------------------------+");
		
		
		diseaseManager.viewDiseases();
		System.out.println("Please select a disease from the list: ");
		int diseaseOption = GeneralInput.intInput();
		
		Disease selectedDisease = diseaseManager.getDisease(diseaseOption);
		
		boolean found = false;
		for (Patient patient : patientManager.getPatients()) {
			if (patient.containsDisease(selectedDisease)) {
				System.out.println(patient.toString());
				found = true;
			}
		}
		
		if (!found) {
			System.out.println("No patients have this disease.");
		}
	}
}
