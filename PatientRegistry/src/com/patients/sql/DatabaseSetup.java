package com.patients.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.patients.managers.DiseaseManager;
import com.patients.managers.PatientManager;
import com.patients.models.Disease;
import com.patients.models.Patient;

public class DatabaseSetup {

    public static void createPatientTable() {
        String sqlPatientTable = "CREATE TABLE IF NOT EXISTS patients (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " name TEXT NOT NULL,\n"
                + " surname TEXT NOT NULL,\n"
                + " sex TEXT NOT NULL,\n"
                + " birthYear INTEGER NOT NULL\n"
                + ");";

        try (Connection conn = SQLiteConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlPatientTable);
            System.out.println("Patient table created or already exists.");
        } catch (Exception e) {
            System.out.println("Error creating Patients table: ");
        }
    }
    
    public static PatientManager loadPatients() {
		int beds = getBedCount();
		if (beds <= 0) {
	        System.out.println("Invalid bed count in registry.");
	        return null;
		}
    PatientManager patientManager = new PatientManager(beds); // or your capacity
    String sql = "SELECT name, surname, sex, birthYear FROM patients;";

    try (Connection conn = SQLiteConnection.connect();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        
        while (rs.next()) {
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String sex = rs.getString("sex");
            int birthYear = rs.getInt("birthYear");

            Patient patient = new Patient(name, surname, sex, birthYear);
            patientManager.addPatient(patient);
        }
        System.out.println("Patients loaded.");
    } catch (SQLException e) {
        System.out.println("Error loading patients: ");
        return null;
    }

    return patientManager;
    }
    
    public static void savePatients(PatientManager patientManager) {
        String deleteSQL = "DELETE FROM patients;";
        String insertSQL = "INSERT INTO patients (name, surname, sex, birthYear) VALUES (?, ?, ?, ?)";

        try (Connection conn = SQLiteConnection.connect();
             Statement deleteStmt = conn.createStatement();
             var insertStmt = conn.prepareStatement(insertSQL)) {

            // Clear old data
            deleteStmt.execute(deleteSQL);

            for (Patient p : patientManager.getPatients()) {
            		if (p == null) continue;
            	
                insertStmt.setString(1, p.getName());
                insertStmt.setString(2, p.getSurname());
                insertStmt.setString(3, p.getSex());
                insertStmt.setInt(4, p.getBirthYear());
                insertStmt.addBatch();
            }

            insertStmt.executeBatch();
            System.out.println("Patients saved.");
        } catch (SQLException e) {
            System.out.println("Error saving patients: " + e.getMessage());
        }
    }
    
    public static void createDiseaseTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS disease (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL UNIQUE,
                icd TEXT NOT NULL UNIQUE,
                severityLevel TEXT NOT NULL,
                contagious BOOLEAN
            );
        """;

        try (Connection conn = SQLiteConnection.connect();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute(sql);
            System.out.println("Disease table created or already exists.");
        } catch (SQLException e) {
            System.out.println("Error creating Disease table: ");
        }
    }
    
    public static DiseaseManager loadDiseases() {
        DiseaseManager diseaseManager = new DiseaseManager();
        String sql = "SELECT name, icd, severityLevel, contagious FROM disease;";

        try (Connection conn = SQLiteConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String name = rs.getString("name");
                String icd = rs.getString("icd");
                String severityLevel = rs.getString("severityLevel");
                boolean contagious = rs.getBoolean("contagious"); 
                
                Disease disease = new Disease(name, icd, severityLevel, contagious);
                diseaseManager.addDisease(disease);
            }
            System.out.println("Diseases loaded.");
        } catch (SQLException e) {
            System.out.println("Error loading diseases: ");
            return null;
        }

        return diseaseManager;
    }

    public static void saveDiseases(DiseaseManager diseaseManager) {
        String deleteSQL = "DELETE FROM disease;";
        String insertSQL = "INSERT INTO disease (name, icd, severityLevel, contagious) VALUES (?, ?, ?, ?)";

        try (Connection conn = SQLiteConnection.connect();
             Statement deleteStmt = conn.createStatement();
             var insertStmt = conn.prepareStatement(insertSQL)) {

            // Clear old data
            deleteStmt.execute(deleteSQL);

            for (Disease d : diseaseManager.getDiseases()) {
            		if (d == null) continue;
            	
                insertStmt.setString(1, d.getName());
                insertStmt.setString(2, d.getICD());
                insertStmt.setString(3, d.getSeverityLevel());
                insertStmt.setBoolean(4, d.isContagious());
                insertStmt.addBatch();
            }

            insertStmt.executeBatch();
            System.out.println("Diseases saved.");
        } catch (SQLException e) {
            System.out.println("Error saving patients: " + e.getMessage());
        }
    }
    
    public static void createRegistryTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS registry (
                id INTEGER PRIMARY KEY CHECK (id = 1),
                beds INTEGER NOT NULL
            );
        """;

        try (Connection conn = SQLiteConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error creating registry table: " + e.getMessage());
        }
    }
    
    public static void saveBedCount(int beds) {
        String sql = "INSERT OR REPLACE INTO registry (id, beds) VALUES (1, ?);";

        try (Connection conn = SQLiteConnection.connect();
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, beds);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving bed count: " + e.getMessage());
        }
    }
    
    public static int getBedCount() {
        String sql = "SELECT beds FROM registry WHERE id = 1;";

        try (Connection conn = SQLiteConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt("beds");
            } else {
                System.out.println("Bed count not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving bed count: " + e.getMessage());
        }

        return -1; // indicates failure
    }
    
    public static void saveData(PatientManager patientManager, DiseaseManager diseaseManager) {
    		savePatients(patientManager);
    		saveDiseases(diseaseManager);
    }
}