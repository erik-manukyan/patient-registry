package com.patients.managers;

import com.patients.models.Patient;
import com.patients.utils.GeneralInput;
import com.patients.utils.ValidationUtils;

public class PatientManager {
	private Patient[] patients;
	
	public PatientManager(int capacity) {
		patients = new Patient[capacity];
	}
	
	public Patient[] getPatients(){
		return patients;
	}
	
	public boolean hasAvailableBed( ) {
		for (Patient patient : patients) {
			if (patient == null) {
				return true;
			}
		}
		return false;
	}
	

	public void addPatient(Patient newPatient) {
		System.out.println("+---------------------------------------------+");
		System.out.println("|               ADD NEW PATIENT               |");
		System.out.println("+---------------------------------------------+");
	
		if (!hasAvailableBed()) {
			System.out.println("Sorry, all the beds in the unit are occupied.");
			System.out.println("Can't proceed to add a patient.");
			return;
		} else if (isDuplicate(newPatient)) {
			System.out.println("A patient already registered with same credentials.");
			System.out.println("Do you wish to register another patient with same credentials? ");
			System.out.println("Please enter Y/N: ");
			boolean cont = GeneralInput.getBoolean();
			if (!cont) {
				System.out.println("Patient not registered.");
				return;
			}
		}
		
		for (int bed = 0; bed < patients.length; bed++) {
			if (patients[bed] == null) {
				
				patients[bed] = newPatient;
				return;
			}
		}
		System.out.println("No available beds.");
	}	
	
	private boolean isDuplicate(Patient newPatient) {
		for (Patient patient: patients) {
			if (patient != null &&
					patient.getName().equalsIgnoreCase(newPatient.getName()) &&
					patient.getSurname().equalsIgnoreCase(newPatient.getSurname()) &&
					patient.getSex().equalsIgnoreCase(newPatient.getSex()) &&
					patient.getBirthYear() == newPatient.getBirthYear()) {
				return true;
			}
		}
		return false;
	}
	
	public void viewPatients() {
		System.out.println("+---------------------------------------------+");
		System.out.println("|                VIEW PATIENTS                |");
		System.out.println("+---------------------------------------------+");
		
		if (isEmpty()) {
			System.out.println("No patients currently registered.");
			return;
		} else {			
			for (int i = 0; i < patients.length; i++) {
				if (patients[i] != null) {
					System.out.println((i + 1) + ". " + patients[i]);				
				}
			}
		}
	}
	
	public boolean isEmpty() {
		for (Patient patient : patients) {
			if (patient != null) {
				return false;
			} 
		}
		return true;
	}
	
	public Patient getPatient(int index) {
	    if (index > 0 && index <= patients.length && patients[index - 1] != null) {
	        return patients[index - 1];
	    } else {
	        throw new IndexOutOfBoundsException("Invalid or empty patient slot.");
	    }
	}
	
	public void removePatient(int index) {
		System.out.println("+---------------------------------------------+");
		System.out.println("|                REMOVE PATIENT               |");
		System.out.println("+---------------------------------------------+");
	    if (index > 0 && index <= patients.length && patients[index - 1] != null) {
	        patients[index - 1] = null;
	        System.out.println("Patient removed.");
	    } else {
	        System.out.println("Invalid patient number.");
	    }
	}

	public void searchPatient() {
		System.out.println("+---------------------------------------------+");
		System.out.println("|              SEARCHING PATIENTS             |");
		System.out.println("+---------------------------------------------+");
		
		System.out.println("Please enter the name or surname of the patient. ");
		String info = ValidationUtils.getCapitalizedInput();
		for (Patient patient : patients ) {
			if (patient != null && (patient.getName() == info || patient.getSurname() == info)) {
				System.out.println(patient.toString());
			} else System.out.println("No patients with name or surname '" + info + "' found in the registry");
		}
	}
	
	public void editPatientDetails() {
		System.out.println("+---------------------------------------------+");
		System.out.println("|             EDIT PATIENT DETAILS            |");
		System.out.println("+---------------------------------------------+");
		viewPatients();
		int patientNum = GeneralInput.getPositiveInt("Please select a patient from the list. ");
		Patient selectedPatient = getPatient(patientNum);
		System.out.println("You selected patient '" + selectedPatient.getName() + selectedPatient.getAge() + "'.");
		System.out.println("What information would you like to change about the patient? ");
		selectedPatient.printAttributes();
		System.out.println("Please select a detail's number in the list: ");
		int choice = GeneralInput.getPositiveInt("If you do not wish to continue enter 0.");
		switch (choice) {
		case 1 -> {
			System.out.println("Please enter the new name of the patient: ");
			String newDetail = ValidationUtils.getCapitalizedInput();
			selectedPatient.setName(newDetail);
			return;
		}
		case 2 -> {
			System.out.println("Please enter the new surname of the patient: ");
			String newDetail = ValidationUtils.getCapitalizedInput();
			selectedPatient.setSurname(newDetail);
			return;			
		}
		case 3 -> {
			System.out.println("You wish to change the sex of patient. Proceed?");
			boolean proceed = GeneralInput.getBoolean();
			if (proceed) selectedPatient.changeSex();
			return;
		}
		case 4 -> {
			int newBirthYear = GeneralInput.getPositiveInt("Please enter the new year of birth of the patient: ");
			selectedPatient.setBirthYear(newBirthYear);
			return;
		}
		default -> System.out.println("Invalid option.");
		}
	}
}
