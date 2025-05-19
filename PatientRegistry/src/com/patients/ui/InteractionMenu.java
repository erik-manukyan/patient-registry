package com.patients.ui;

import com.patients.managers.DiseaseManager;
import com.patients.managers.InteractionManager;
import com.patients.managers.PatientManager;
import com.patients.utils.GeneralInput;

public class InteractionMenu {
	public static void handleInteractionMenu(DiseaseManager diseaseManager, PatientManager patientManager) {
		boolean back = false;
		while(!back) {
			System.out.println("\n+-------- INTERACTION MENU --------+");
			System.out.println("| 1) Assign Disease To Patient     |");
			System.out.println("| 2) Remove Disease From Patient   | ");
			System.out.println("| 3) View Diseases of a Patient    |");
	        System.out.println("| 4) View Patients by Disease      |");
            System.out.println("| 0) Back to Main Menu             |");
	        System.out.println("+----------------------------------+");
			System.out.println("| Select an option:                |");
			int option = GeneralInput.intInput();
			
			switch(option) {
			case 1 -> InteractionManager.assignDisease(diseaseManager, patientManager);
			case 2 -> InteractionManager.deleteDisease(diseaseManager, patientManager);
			case 3 -> InteractionManager.viewDiseasesByPatient(patientManager, diseaseManager);
			case 4 -> InteractionManager.viewPatientsByDisease(patientManager, diseaseManager);
			case 0 -> back = true;
			default -> System.out.println("Invalid option.");
			}
		}
	}
}
