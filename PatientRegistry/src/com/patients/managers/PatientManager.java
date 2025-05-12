package com.patients;

public class PatientManager {
	private Patient[] patients;
	
	public PatientManager(int capacity) {
		patients = new Patient[capacity];
	}
	
	public boolean hasAvailableBed() {
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
	
		for (int bed = 0; bed < patients.length; bed++) {
			if (patients[bed] == null) {
				patients[bed] = newPatient;
				System.out.println("Patient registered! Patient No " + (bed + 1));
				return;
			}
		}
		System.out.println("No available beds.");
	}	
	
	public void viewPatients() {
		System.out.println("+---------------------------------------------+");
		System.out.println("|                VIEW PATIENTS                |");
		System.out.println("+---------------------------------------------+");
		boolean found = false;
		for (Patient patient : patients) {
			if (patient != null) {
				patient.GetFullInfo();
				found = true;
			} 
		}
		
		if (!found) {
			System.out.println("No patients currently registered.");
		}
	}
	
	public boolean isEmpty() {
		for (Patient patient : patients) {
			if (patient != null) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	
	public Patient[] getPatientsList() {
	    return patients;
	}
	
	public Patient getPatient(int index) {
		return patients[index - 1];
	}
}
