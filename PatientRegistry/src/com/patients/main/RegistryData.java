package com.patients.main;

import com.patients.managers.DiseaseManager;
import com.patients.managers.PatientManager;

public class RegistryData {
    private PatientManager patientManager;
    private DiseaseManager diseaseManager;

    public RegistryData(PatientManager patientManager, DiseaseManager diseaseManager) {
        this.patientManager = patientManager;
        this.diseaseManager = diseaseManager;
    }

    public PatientManager getPatientManager() {
        return patientManager;
    }

    public DiseaseManager getDiseaseManager() {
        return diseaseManager;
    }
}