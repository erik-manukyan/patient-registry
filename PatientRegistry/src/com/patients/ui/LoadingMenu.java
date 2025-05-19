package com.patients.ui;

import com.patients.utils.GeneralInput;
import com.patients.managers.DiseaseManager;
import com.patients.managers.PatientManager;
import com.patients.sql.*;
import com.patients.main.RegistryData;

public class LoadingMenu {
	public static RegistryData run() {
		PatientManager patientManager = null;
		DiseaseManager diseaseManager = null;

		boolean running = true;
		while (running) {
			displayLoadingMenu();
			int option = GeneralInput.intInput();

			switch (option) {
				case 1 -> {
					int beds = GeneralInput.getPositiveInt("Enter the number of beds in the medical unit: ");
					DatabaseSetup.createPatientTable();
					DatabaseSetup.createDiseaseTable();
					DatabaseSetup.createRegistryTable();
					DatabaseSetup.saveBedCount(beds);
					System.out.println("New registry created.");
					patientManager = new PatientManager(beds);
					diseaseManager = new DiseaseManager();
					running = false;
				}
				case 2 -> {
					patientManager = DatabaseSetup.loadPatients();
					diseaseManager = DatabaseSetup.loadDiseases();
					if (patientManager == null || diseaseManager == null) {
						System.out.println("Failed to load registry. Try creating a new one.");
					} else {
						System.out.println("Registry loaded successfully.");
						return new RegistryData(patientManager, diseaseManager);
					}
				}
				case 0 -> {
					System.out.println("Exiting application.");
					return null; // signal main() to exit
				}
				default -> System.out.println("Invalid option, try again.");
			}
		}

		return new RegistryData(patientManager, diseaseManager);
	}

	private static void displayLoadingMenu() {
		System.out.println("\n+------------- MAIN MENU -------------+");
		System.out.println("| 1) Create a new registry            |");
		System.out.println("| 2) Open existing registry           |");
		System.out.println("| 0) Exit                             |");
		System.out.println("+-------------------------------------+");
		System.out.println("| Select an option:                   |");
	}
}
