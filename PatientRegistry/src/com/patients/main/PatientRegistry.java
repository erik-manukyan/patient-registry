package com.patients;

public class PatientRegistry {
	
	
	private static PatientManager patientManager; // |Creates an instance for future use of arrays to manage patients
	private static DiseaseManager diseaseManager; // |
	
	public static void main(String[] args) {
		System.out.println("Welcome to the Patient Registry"); 
		int beds = getBeds(); // This is a method to get number of beds of Medical Unit
		patientManager = new PatientManager(beds); // Based on the number of beds an array is created of that size
		diseaseManager = new DiseaseManager();
		runMenu(); // Runs the Main Menu
	}
	
	// Method to get number of beds for future use
	
	public static int getBeds() {
		int bedAmount = -1;
		while (bedAmount <= 0) {
			System.out.println("Before continuing the entry, please enter the amount of beds in medical unit: ");
			bedAmount = InputUtils.intInput();
			if (bedAmount <= 0) {
				System.out.println("Please enter a valid amount of beds in medical unit: ");
			}
		}
		return bedAmount;
	}
	
	public static void runMenu() {
		int option;
		boolean cont = true;
		
		while(cont) {
			option = getOption();
			switch (option) {
				case 0:
					cont = false;
					break;
				case 1:
					if (patientManager.hasAvailableBed()) {               // First checks if there are available beds
						Patient patient = InputUtils.getPatientDetails(); // If there are, we start getting details of the patient
						patientManager.addPatient(patient);               // Finally, we add patient to the array created previously
					} else {
						System.out.println("No available beds. ");        // If there are no available beds
					}
					break;
				case 2:
					System.out.println("+---------------------------------------------+");
					System.out.println("|               ADD NEW DISEASE               |");
					System.out.println("+---------------------------------------------+");
					Disease disease = InputUtils.getDiseaseDetails();
					diseaseManager.addDisease(disease);;
					break;
				case 3:
					assignDisease();
					break;
				case 4:
					patientManager.viewPatients(); // View Patients currently in the medical unit
					break;
				case 5:
					diseaseManager.viewDiseases(); // View Diseases currently registerd in the medical unit
					break;
				case 6:
					viewDisease();
					break;
				default:
					System.out.println("Option not available. Please select a valid option: ");
			}
		}
	}
	
	public static int getOption(){
		int option;
		while(true) {
			System.out.println();
			System.out.println("+---------------------------------------------+");
			System.out.println("|                MAIN MENU                    |");
            System.out.println("+---------------------------------------------+");
            System.out.println("|  1) Add New Patient                         |");
            System.out.println("|  2) Add New Disease                         |");
            System.out.println("|  3) Assign Disease to Patient               |");
            System.out.println("|  4) View All Patients                       |");
            System.out.println("|  5) View All Diseases                       |");
            System.out.println("|  6) View Diseases of a Patient              |");
            System.out.println("|  0) Quit                                    |");
            System.out.println("+---------------------------------------------+");
            System.out.print("Please select an option: ");
            
            option = InputUtils.intInput();
            
            if (option >= 0 && option <= 6) {
            	return option;
            } else {
				System.out.println("Please select a valid menu option (0-6). ");
			}
		}
	}
	
	private static void assignDisease() {
		System.out.println("+---------------------------------------------+");
		System.out.println("|               ASSIGN DISEASE                |");
		System.out.println("+---------------------------------------------+");
		
		// Check if there are patients
		
		if (patientManager.isEmpty()) {
			System.out.println("No patients available to assign diseases. ");
			return;
		}
		
		if (diseaseManager.isEmpty()) {
			System.out.println("No diseases available to assign. ");
			return;
		}
		
		System.out.println("Please select a patient: ");
		patientManager.viewPatients();
		int option = InputUtils.intInput();
		Patient selected = null;
		
		while(selected == null) {
			try {
				selected = patientManager.getPatient(option);	
				break;
			} catch (NullPointerException e) {
				System.out.println("Please enter a valid number. ");
				option = InputUtils.intInput();
			}
		}
		
		System.out.println("Please select a disease: ");
		
		Disease adding = null;
		diseaseManager.viewDiseases();
		option = InputUtils.intInput();
		
		while (adding == null) {
			try {
				adding = diseaseManager.getDisease(option);
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Invalid selection. Please choose a number from the list.");
				option = InputUtils.intInput();
			}
		}
	
		selected.AddDisease(adding);
		
		System.out.println("Disease " + adding.GetName() + " has been assigned to patient '" + selected.GetName() + "' .");
	}
	
	private static void viewDisease() {
		System.out.println("+---------------------------------------------+");
		System.out.println("|            View Patient Disease             |");
		System.out.println("+---------------------------------------------+");
		System.out.println("Please select a patient: ");
		patientManager.viewPatients();
		int option = InputUtils.intInput();
		Patient selected = patientManager.getPatient(option);
		System.out.println("+---------------------------------------------+");
		System.out.println("|          Patient's medical record           |");
		System.out.println("+---------------------------------------------+");
		selected.ShowDisease();
	}
	
}
