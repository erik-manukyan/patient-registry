package com.patients.ui;

import com.patients.managers.DiseaseManager;
import com.patients.models.Disease;
import com.patients.utils.GeneralInput;
import com.patients.utils.ObjectInput;

public class DiseaseMenu {
	public static void handleDiseaseMenu(DiseaseManager diseaseManager) {
		
		boolean back = false;
		while(!back) {
			printMenu();
            int option = GeneralInput.intInput();
            
            switch(option) {
            	case 1 ->{
            		Disease inputDisease = ObjectInput.getDiseaseDetails();
        		    if (resolveDuplicate(inputDisease, diseaseManager)) {
        		        diseaseManager.addDisease(inputDisease);
        		        System.out.println("Disease successfully registered.");
        		    }
            	}
            	case 2 -> diseaseManager.viewDiseases();
            	case 3 -> diseaseManager.deleteDisease();
            	case 4 -> diseaseManager.editDisease();
            	case 5 -> diseaseManager.searchDisease();
            	case 0 -> back = true;
            	default -> System.out.println("Invalid option.");
            }
            
		}
		
	}
	
	private static void printMenu() {
	    System.out.println("\n+------- DISEASE MENU -------+");
	    System.out.println("| 1) Add New Disease         |");
	    System.out.println("| 2) View All Diseases       |");
	    System.out.println("| 3) Remove Disease          |");
	    System.out.println("| 4) Edit Disease Details    |");
	    System.out.println("| 5) Search Disease By ICD   |");
	    System.out.println("| 0) Back to Main Menu       |");
	    System.out.println("+----------------------------+");
	    System.out.println("| Select an option:          |");
	}

	private static boolean resolveDuplicate(Disease inputDisease, DiseaseManager diseaseManager) {
	    while (diseaseManager.isDuplicate(inputDisease)) {
	        System.out.println("A disease with same name or ICD found in the registry.");
	        System.out.println("Please change the data provided.");
	        System.out.print("Do you want to change name or ICD code of the disease you wish to add?");
	        System.out.println("Please enter (Y/N): ");
	        boolean change = GeneralInput.getBoolean();
	        if (change) {
	            ObjectInput.changeDiseaseName(inputDisease);
	            ObjectInput.changeDiseaseICD(inputDisease);
	        } else {
	            System.out.println("Disease not added.");
	            return false; // cancelled by user
	        }
	    }
	    return true; // no duplicates, safe to add
	}
}
