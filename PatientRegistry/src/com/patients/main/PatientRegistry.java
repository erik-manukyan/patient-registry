package com.patients.main;

import com.patients.managers.DiseaseManager;
import com.patients.managers.PatientManager;
import com.patients.ui.LoadingMenu;
import com.patients.ui.MenuHandler;


public class PatientRegistry {
	public static void main(String[] args) {
	    System.out.println("Welcome to the Patient Registry System!");

	    // Run loading menu and get back initialised managers
	    RegistryData registryData = LoadingMenu.run();
	    if (registryData == null) {
	        System.out.println("Exiting program.");
	        return;
	    }

	    PatientManager patientManager = registryData.getPatientManager();
	    DiseaseManager diseaseManager = registryData.getDiseaseManager();

	    MenuHandler menu = new MenuHandler(patientManager, diseaseManager);
	    menu.run();
	}
}
