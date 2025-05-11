package com.patients;

import java.util.ArrayList;

/*	
 * This is the class of the patient that is getting registered in a medical Unit.
 * For testing purposes now the attributes are limited to 4, but I'll add more later.
 * Version No 0.1.0
 */

public class Patient {
	
	/*	
	 * TO ADD
	 * 	
	 * Date of Birth (Rather than just birth year for accurate age calculation)
	 * Contact Information (Phone, Address, Email)
	 * Emergency Contact (Name, Phone, Relationship)
	 * Medical History (Existing conditions, allergies, previous surgeries)
	 * Current Diagnosis (Condition being treated)
	 * Medications (Prescribed drugs, dosages)
	 * Vital Signs (Blood pressure, heart rate, temperature)
	 * Symptoms (Reported complaints)
	 * Admission Date (When they were admitted)
	 * Doctor Assigned (The overseeing physician)
	 * Room Number/Ward (Where the patient is located)
	 * Treatment Plan (Procedures, medications, therapy)
	 * Discharge Date (When they leave the hospital)
	 * Billing Information (Insurance details, payment status)
	 */
	
	
	// Existing attributes
	
	private String name;
	private String surname;
	private String sex;
	private int birthYear;
	private ArrayList<Disease> diseases;
	
	public Patient(String name, String surname, String sex, int birthYear) {
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.birthYear = birthYear;
		this.diseases = new ArrayList<>();
	}
	
	// List of Getters
	
	public String GetName() {
		return name;
	}
	
	public String GetSurname() {
		return surname;
	}
	
	public String GetSex() {
		return sex;
	}
	
	public int GetBirthYear() {
		return birthYear;
	}
	
	public void GetFullInfo() {
		System.out.println(name + " " + surname + " " + sex + " " + birthYear);
	}
	
	/* 
	 * Potential Methods
	 * 
	 * Calculate Age from date of birth.
	 * Update Medical History when new conditions arise.
	 * Retrieve Patients by Diagnosis/Symptom to aid in triaging.
	 * Alert for Critical Vital Signs (If blood pressure or heart rate is dangerously high or low).
	 * Generate a Hospital Report summarising the patient's status.
	 */
	
	// List of Setters
	
	public void SetName(String name) {
		this.name = name;
	}
	
	public void SetSurname(String surname) {
		this.surname = surname;
	}
	
	public void SetSex(String sex) {
		this.sex = sex;
	}
	
	public void SetBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	
	// List of Disease Management
	
	public void AddDisease(Disease newDisease) {
		if (!diseases.contains(newDisease)) {
			diseases.add(newDisease);
		} else System.out.println("Disease already added. ");
		
	}
	
	public  ArrayList<Disease> ShowDisease() {
		return diseases;
	}
}
