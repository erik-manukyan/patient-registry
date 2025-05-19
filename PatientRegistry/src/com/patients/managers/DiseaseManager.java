package com.patients.managers;

import java.util.ArrayList;

import com.patients.models.Disease;
import com.patients.utils.GeneralInput;
import com.patients.utils.ValidationUtils;

/**
 * Manages a collection of Disease objects.
 * Provides methods to add, view, remove, and retrieve diseases.
 * Ensures no duplicates by checking name and ICD code before adding.
 */

public class DiseaseManager {
    
    // List to hold all diseases in the registry
    private ArrayList<Disease> diseases;
    
    /**
     * Constructs a new DiseaseManager with an empty disease list.
     */
    
    public DiseaseManager() {
        diseases = new ArrayList<>();
    }
    
    /**
     * Adds a new disease to the registry
     
     * @param newDisease the Disease object to add
     */
    
    public void addDisease(Disease newDisease) {
        diseases.add(newDisease); // Add disease 
    }
   
    /**
     * Displays all diseases currently registered.
     * If no diseases exist, prints a message indicating the registry is empty.
     */
    public void viewDiseases() {
        if (diseases.isEmpty()) {
            System.out.println("Currently no diseases registered.");
        } else {
            for (int i = 0; i < diseases.size(); i++) {
                System.out.println((i + 1) + ". " + diseases.get(i));
            }
        }
    }
    
    /**
     * Removes a specified disease from the registry.
     * 
     * @param disease the Disease to remove
     */
    public void removeDisease(Disease disease) {
        diseases.remove(disease);
    }
    
    /**
     * Returns the current number of diseases in the registry.
     * 
     * @return the number of diseases
     */
    public int getDiseaseCount() {
        return diseases.size();
    }
    
    /**
     * Retrieves a disease by its 1-based index.
     * Throws an IndexOutOfBoundsException if the index is invalid.
     * 
     * @param index the 1-based index of the disease to retrieve
     * @return the Disease at the given index
     */
    public Disease getDisease(int index) {
        if (index > 0 && index <= diseases.size()) {
            return diseases.get(index - 1);            
        } else {
            throw new IndexOutOfBoundsException("Invalid disease index.");
        }
    }
    
    /**
     * Checks if the disease registry is empty.
     * 
     * @return true if no diseases registered, false otherwise
     */
    public boolean isEmpty() {
        return diseases.isEmpty();
    }
    
    /**
     * Checks whether a disease with the same name or ICD code already exists.
     * Comparison is case-insensitive.
     * 
     * @param newDisease the Disease to check for duplication
     * @return true if a duplicate exists, false otherwise
     */
    public boolean isDuplicate(Disease newDisease) {
        for (Disease disease : diseases) {
            if (disease.getName().equalsIgnoreCase(newDisease.getName()) ||
                disease.getICD().equalsIgnoreCase(newDisease.getICD())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns an unmodifiable list of diseases currently in the registry.
     * 
     * @return an unmodifiable list of diseases
     */
    public ArrayList<Disease> getDiseases() {
        return diseases;
    }
    
    
    /**
     * Deletes a disease from the list of diseases currently in the registry.
     * 
     * @param index of the disease within the list of diseases.
     */
    
    public void deleteDisease() {
    	viewDiseases();
		if (isEmpty()) {
			return;
		}
		
		int index = GeneralInput.getPositiveInt("Please select a disease you wish to delete.");
		System.out.println("You want to delete a disease from registry. Are you sure you want to proceed?");
		System.out.println("Please enter Y/N: ");
		boolean proceed = GeneralInput.getBoolean();
		if (proceed) {
    		diseases.remove(index);
			System.out.println("Disease successfully deleted from the registry.");
			return;
		} else {
			System.out.println("Disease hasn't been deleted.");
			return;
		}
    }
    
    public void searchDisease() {
    	System.out.println("+---------------------------------------------+");
		System.out.println("|              SEARCHING DISEASES             |");
		System.out.println("+---------------------------------------------+");
		
		System.out.println("Please enter the ICD of the disease. ");
		String info = ValidationUtils.getCapitalizedInput();
		for (Disease disease : diseases) {
			if (disease.getICD() == info && disease != null) {
				System.out.println(disease.toString());
			} else System.out.println("No patients with name or surname '" + info + "' found in the registry");
		}
    }
    
    public void editDisease() {
    	System.out.println("+---------------------------------------------+");
		System.out.println("|             EDIT PATIENT DETAILS            |");
		System.out.println("+---------------------------------------------+");
		viewDiseases();
		int diseaseNum = GeneralInput.getPositiveInt("Please select a disease from the list. ");
		Disease selectedDisease = getDisease(diseaseNum);
		System.out.println("You selected disease" + selectedDisease.toString());
		System.out.println("What information would you like to change about the disease? ");
		selectedDisease.printAttributes();
		System.out.println("Please select a detail's number in the list: ");
		int choice = GeneralInput.getPositiveInt("If you do not wish to continue enter 0.");
		switch (choice) {
		case 1 -> {
			System.out.println("Please enter the new name of the disease: ");
			String newDetail = ValidationUtils.getCapitalizedInput();
			selectedDisease.setName(newDetail);
			return;
		}
		case 2 -> {
			System.out.println("Please enter the new ICD of the disease: ");
			String newDetail = ValidationUtils.getCapitalizedInput();
			selectedDisease.setICD(newDetail);
			return;			
		}
		case 3 -> {
			System.out.println("Please enter the new level of severity of the disease: ");
			int severityLevelNum = GeneralInput.getPositiveInt("1. Mild - 2. Moderate - 3. Severe ");
			switch (severityLevelNum) {
			case 1 -> selectedDisease.setSeverityLevel("Mild");
			case 2 -> selectedDisease.setSeverityLevel("Moderate");
			case 3 -> selectedDisease.setSeverityLevel("Severe");
			default -> System.out.println("Invalid input. ");
			}
			return;	
		}
		case 4 -> {
			System.out.println("You wish to change contagious tag of the disease. Proceed?");
			boolean proceed = GeneralInput.getBoolean();
			if (proceed) selectedDisease.changeContagious();
			return;
		}
		default -> System.out.println("Invalid option.");
		}
		System.out.println("The detail successfully changed!");
    }
}
