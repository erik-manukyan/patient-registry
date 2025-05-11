package com.patients;

/* 
 * This is the class of diseases of the patients currently registered in the medical unit
 * For testing purposes now the attributes are limited to 4, but I'll add more later.
 * Version No 0.1.0
 */

public class Disease {
	
	/* 
	 * TO ADD
	 * 
	 * Symptoms (Common signs reported by the patient)
	 * Diagnosis Method (How it's diagnosed—tests, scans, labs)
	 * Treatment Options (Medication, therapy, surgery)
	 * Precautions (Isolation guidelines, PPE requirements)
	 * Expected Recovery Time (Approximate healing duration)
	 * Complications (Potential risks, secondary conditions)
	 * Medications Prescribed (Common drugs used for treatment)
	 * Specialist Required (Which type of doctor handles the case)
	 * Hospital Department (Which unit treats it—Cardiology, Neurology, Infectious Diseases, etc.)
	 * Follow-up Required (Boolean: true/false)
	 */
	
	// Existing attributes
	
	private String name;
	private String ICD;
	private String severityLevel;
	private boolean contagious;
	
	public Disease(String name,String ICD,String severityLevel,boolean contagious) {
		this.name = name;
		this.ICD = ICD;
		this.severityLevel = severityLevel; 
		this.contagious = contagious;		
	}
	
	// List of Getters
	
	public String GetName() {
		return name;
	}
	
	public String GetICD() {
		return ICD;
	}
	
	public String GetSeverityLevel() {
		return severityLevel;
	}
	
	public boolean GetContagious() {
		return contagious;
	}
	
	public void GetFullInfo() {
		if (contagious) System.out.println(name + " " + ICD + " " + severityLevel + " Contagious");
		else if (!contagious) System.out.println(name + " " + ICD + " " + severityLevel + " Not contagious");
	}
	
	/* 
	 * Potential Methods
	 * 
	 * Match Symptoms to Disease (Cross-check symptoms to suggest possible diagnoses)
	 * Generate Treatment Summary (Print or return basic treatment plan)
	 * Check Contagion Risk (Warn if special precautions are needed)
	 * Estimate Recovery Timeline (Based on disease severity)
	 */
	
	// List of Setters
	
	public void SetName(String name) {
		this.name = name;
	}
	
	public void SetICD(String ICD) {
		this.ICD = ICD;
	}
	
	public void SetSeverityLevel(String severityLevel) {
		this.severityLevel = severityLevel;
	}
	
	public void SetContagious(boolean contagious) {
		this.contagious = contagious;
	}
}
