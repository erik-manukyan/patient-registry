package com.patients.models;

/**
 * Represents a disease registered in the medical system.
 * Contains identifying and descriptive information about the disease.
 */
public class Disease {
    
    // Name of the disease, e.g. "Influenza"
    private String name;
    
    // International Classification of Diseases (ICD) code for the disease
    private String ICD;
    
    // Severity level description, e.g. "Mild", "Moderate", "Severe"
    private String severityLevel;
    
    // Whether the disease is contagious (true/false)
    private boolean contagious;
    
    /**
     * Constructs a new Disease with all required fields.
     * 
     * @param name of the disease
     * @param ICD code 
     * @param severity level description
     * @param ccontagious flag (true if contagious, false otherwise)
     */
    public Disease(String name, String ICD, String severityLevel, boolean contagious) {
        this.name = name;
        this.ICD = ICD;
        this.severityLevel = severityLevel;
        this.contagious = contagious;
    }
    
    // Getters for all attributes
    
    public String getName() {
        return name;
    }
    
    public String getICD() {
        return ICD;
    }
    
    public String getSeverityLevel() {
        return severityLevel;
    }
    
    /**
     * Returns whether the disease is contagious.
     * 
     * @return true if contagious, false otherwise
     */
    public boolean isContagious() {
        return contagious;
    }
    
    /**
     * Returns a formatted string summarising the disease’s attributes.
     */
    public String toString() {
        String info = contagious ? "Yes" : "No";
        return String.format("Name: %s | ICD: %s | Severity Level: %s | Contagious: %s",
                name, ICD, severityLevel, info);
    }
    
    // Setters for modifying attributes
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setICD(String ICD) {
        this.ICD = ICD;
    }
    
    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }
    
    public void setContagious(boolean contagious) {
        this.contagious = contagious;
    }
    
    public void printAttributes() {
		System.out.println("Patient Details:");
		System.out.println("1. - Name");
		System.out.println("2. - ICD");
		System.out.println("3. - Severity Level");
		System.out.println("4. - Contagious");
    }
    
    public void changeContagious() {
    	if (contagious) setContagious(false);
    	else if (!contagious) setContagious(true);
    }
}
