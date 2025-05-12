package com.patients.managers;

import com.patients.models.Patient;

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
		boolean found = isEmpty();
		for (int i = 0; i < patients.length; i++) {
			System.out.println((i + 1) + ". " + patients[i]);
		}
		
		if (found) {
			System.out.println("No patients currently registered.");
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
	
	public Patient[] getPatientsList() {
	    return patients;
	}
	
	public Patient getPatient(int index) {
		return patients[index - 1];
	}
}
