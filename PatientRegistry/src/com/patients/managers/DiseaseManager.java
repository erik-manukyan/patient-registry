package com.patients;

import java.util.ArrayList;

public class DiseaseManager {
	private ArrayList<Disease> diseases;
	
	public DiseaseManager() {
		diseases = new ArrayList<>();
	}
	
	public void addDisease(Disease newDisease) {
		diseases.add(newDisease); // Adds disease to the end of the array list
	}
	
	public void viewDiseases() {
		if (diseases.isEmpty()) {
			System.out.println("Currently no diseases registered.");
		} else {
			for (Disease disease : diseases) {
				disease.GetFullInfo();
			}
		}
	}
	
	public void removeDisease(Disease disease) {
		diseases.remove(disease);
	}
	
	public int getDiseaseCount() {
		return diseases.size();
	}
	
	public Disease getDisease(int index) {
		return diseases.get(index - 1);
	}
	
	public boolean isEmpty() {
		if (diseases.isEmpty()) {
			return true;
		} else return false;
	}
}
