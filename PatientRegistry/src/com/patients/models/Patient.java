package com.patients.models;

import java.util.ArrayList;

import com.patients.utils.GeneralInput;

import java.time.Year;

/**
 * Represents a patient registered in the medical system.
 * Contains personal information and a list of diseases the patient has.
 * Future expansions may include detailed medical history, contact info, etc.
 */
public class Patient {
    
    // Patient's first name
    private String name;
    
    // Patient's last name
    private String surname;
    
    // Patient's sex/gender
    private String sex;
    
    // Patient's birth year (used for calculating age)
    private int birthYear;
    
    // List of diseases assigned to the patient
    private ArrayList<Disease> diseases;
    
    /**
     * Constructs a new Patient with given details.
     * Initialises the disease list as empty.
     * 
     * @param name patient's first name
     * @param surname patient's last name
     * @param sex patient's sex/gender
     * @param birthYear patient's birth year
     */
    public Patient(String name, String surname, String sex, int birthYear) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.birthYear = birthYear;
        this.diseases = new ArrayList<>();
    }
    
    // Getters for all patient attributes
    
    public String getName() {
        return name;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public String getSex() {
        return sex;
    }
    
    public int getBirthYear() {
        return birthYear;
    }
    
    /**
     * Returns a copy of the list of diseases assigned to this patient.
     * Protects the internal list from external modification.
     * 
     * @return copy of the disease list
     */
    public ArrayList<Disease> getDiseases() {
        return new ArrayList<>(diseases);
    }

    /**
     * Returns a formatted string with basic patient info and calculated age.
     */
    public String toString() {
        return String.format("Name: %s %s | Sex: %s | Birth Year: %d | Age: %d",
                name, surname, sex, birthYear, getAge());
    }
    
    // Setters for updating patient attributes
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
    
    public void printAttributes() {
		System.out.println("Patient Details:");
		System.out.println("1. - Name");
		System.out.println("2. - Surname");
		System.out.println("3. - Sex");
		System.out.println("4. - Birth Year");
    }
    
    /**
     * Adds a disease to the patient’s disease list if it’s not already present.
     * Prevents duplicate diseases.
     * 
     * @param newDisease the Disease to add
     */
    public void addDisease(Disease newDisease) {
        if (!diseases.contains(newDisease)) {
            diseases.add(newDisease);
        }
    }
    
    /**
     * Checks if the patient already has the given disease.
     * 
     * @param disease the Disease to check for
     * @return true if disease is present, false otherwise
     */
    public boolean containsDisease(Disease disease) {
        return diseases.contains(disease);
    }
    
    /**
     * Prints the list of diseases the patient currently has.
     * If none, prints a message indicating no diseases registered.
     */
    public void showDiseases() {
        if (diseases.isEmpty()) {
            System.out.println("No diseases registered for this patient.");
        } else {
            for (int i = 0; i < diseases.size(); i++) {
                Disease disease = diseases.get(i);
                System.out.println((i + 1) + ". " + disease.toString());
            }
        }
    }
    
    /**
     * Calculates the patient’s current age based on birth year and current year.
     * 
     * @return age in years
     */
    public int getAge() {
        return Year.now().getValue() - birthYear;
    }
    
    /*
     * Select Disease from the ones assigned to the patient
     *
     * @return specific disease
     */
    
    public void deleteDisease() {
    	showDiseases();
	    if (!diseases.isEmpty()) {
	    	int index = GeneralInput.getPositiveInt("Please select a disease from the list. ");
	    	diseases.remove(index);
	    } else System.out.println("No diseases currently assigned to the patient. ");
    }
    
    public void changeSex() {
    	if (sex.equals("Male")) setSex("Female");
    	else if (sex.equals("Female")) setSex("Male");
    }
}