package com.patients.managers;

import java.util.ArrayList;

import com.patients.models.Disease;

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
			for (int i = 0; i < diseases.size(); i++) {
				System.out.println((i + 1) + ". " + diseases.get(i));
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
		if (index > 0 && index <= diseases.size()) {
			return diseases.get(index - 1);			
		} else {
			throw new IndexOutOfBoundsException("Invalid disease index. ");
		}
	}
	
	public boolean isEmpty() {
		return diseases.isEmpty();
	}
	
	
}
