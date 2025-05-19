package com.patients.ui;

import com.patients.managers.*;
import com.patients.sql.DatabaseSetup;
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
				case 3 -> InteractionMenu.handleInteractionMenu(diseaseManager, patientManager); 
				case 0 -> {
					saveData(diseaseManager, patientManager);
					System.out.println("Exiting...");
					running = false;
				}
				default -> System.out.println("Invalid option. ");
			}
		}
	}
	
	private void displayMainMenu() {
		System.out.println("\n+------------- MAIN MENU -------------+");
        System.out.println("| 1) Patient Options                  |");
        System.out.println("| 2) Disease Options                  |");
        System.out.println("| 3) Interaction Options              |");
        System.out.println("| 0) Exit                             |");
        System.out.println("+-------------------------------------+");
        System.out .println("| Select an option:                   |");        
	}
	
	private void saveData(DiseaseManager diseaseManager, PatientManager patientManager) {
		System.out.println("Do you want to save data before exiting?");
		System.out.println("Please enter Y/N: ");
		boolean answer = GeneralInput.getBoolean();
		if (answer) {
			DatabaseSetup.saveData(patientManager, diseaseManager);
		} else {
			return;
		}
	}
}
