package com.patients.utils;

import java.util.Scanner;

public class GeneralInput {
	private static final Scanner input = new Scanner(System.in);
	
	// Function of inputing and checking input of STRINGS
	
		public static String stringInput() {
			String string;
			while (true) {
				try {
					string = input.nextLine().trim();
					if (!string.isEmpty()) {
						return string;
					} else {
						System.out.println("ERROR| You did not input anything: ");
					}
				} catch (Exception e) {
					System.out.println("ERROR| Enter a valid input: ");
					input.nextLine();
				}
			}
		}
		
		
		// Function of inputing and checking the input of INTEGERS
		
		public static int intInput() {
			while(true) {
				try {
					int num = input.nextInt();
					input.nextLine();
			 		return num;
				} catch (Exception e) {
					System.out.println("ERROR| Enter a valid number: ");
					input.nextLine();
				}
			}
		}
		
		public static int getPositiveInt(String prompt) {
			System.out.println(prompt);
			while(true) {
				int positive = intInput();
				if (positive > 0) {
					return positive;
				} else {
					System.out.println("Please enter a positive number: ");
				}
			}
		}
		
		// Function for Yes/No
		
		public static boolean getBoolean() {
		    while (true) {
		        String answer = stringInput().trim().toLowerCase();
		        if (answer.startsWith("y")) return true;
		        else if (answer.startsWith("n")) return false;
		        System.out.println("ERROR| Please enter Y/N: ");
		    }
		}
}
