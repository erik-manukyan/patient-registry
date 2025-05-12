package com.patients.ui;

import com.patients.managers.PatientManager;
import com.patients.utils.GeneralInput;
import com.patients.utils.ObjectInput;


public class PatientMenu {
	public static void handlePatientMenu(PatientManager patientManager) {
		boolean back = false;
		while(!back) {
			System.out.println("\n+------- PATIENT MENU -------+");
            System.out.println("| 1) Add New Patient         |");
            System.out.println("| 2) View All Patients       |");
            System.out.println("| 0) Back to Main Menu       |");
            System.out.println("+----------------------------+");
            System.out.println("| Select an option:          |"); 
            int option = GeneralInput.intInput();
            
            switch (option) {
				case 1 -> {
					if (patientManager.hasAvailableBed()) {
						patientManager.addPatient(ObjectInput.getPatientDetails());
					} else {
						System.out.println("No available beds.");
					}
				}
				case 2 -> patientManager.viewPatients();
				case 0 -> back = true;
				default -> System.out.println("Invalid option.");
			}
            
		}
	}
}
