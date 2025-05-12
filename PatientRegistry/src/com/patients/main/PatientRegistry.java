package com.patients.main;

import com.patients.managers.DiseaseManager;
import com.patients.managers.PatientManager;
import com.patients.ui.MenuHandler;
import com.patients.utils.GeneralInput;


public class PatientRegistry {
	public static void main(String[] args) {
		System.out.println("Welcome to the Patient Registry System!");
		int beds = GeneralInput.getPositiveInt("Enter the number of beds in the medical unit: ");
		PatientManager patientManager = new  PatientManager(beds);
        DiseaseManager diseaseManager = new DiseaseManager();

        MenuHandler menu = new MenuHandler(patientManager, diseaseManager);
        menu.run();
	}
}
