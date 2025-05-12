package com.patients.ui;

import com.patients.managers.DiseaseManager;
import com.patients.managers.PatientManager;
import com.patients.models.Patient;
import com.patients.utils.GeneralInput;
import com.patients.utils.ObjectInput;

public class DiseaseMenu {
	public static void handleDiseaseMenu(DiseaseManager diseaseManager) {
		
		boolean back = false;
		while(!back) {
			System.out.println("\n+------- DISEASE MENU -------+");
            System.out.println("| 1) Add New Disease         |");
            System.out.println("| 2) View All Diseases       |");
            System.out.println("| 0) Back to Main Menu       |");
            System.out.println("+----------------------------+");
            System.out.println("| Select an option:          |");
            int option = GeneralInput.intInput();
            
            switch(option) {
            	case 1 -> diseaseManager.addDisease(ObjectInput.getDiseaseDetails());
            	case 2 -> diseaseManager.viewDiseases();
            	case 0 -> back = true;
            	default -> System.out.println("Invalid option.");
            }
            
		}
		
		
	}
}
