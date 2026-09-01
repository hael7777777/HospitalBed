package com.mycompany.hospitalbed;

import java.util.Scanner;

public class Report {

    public static boolean prompt(Scanner input) {
        System.out.print("Would you like to view reports? y/n");
        String answer = input.nextLine().trim();

        while (true) {
            if (answer.equalsIgnoreCase("Y")) {
                reportChoice(input);
                return false;
            } else if (answer.equalsIgnoreCase("N")) {
                System.out.println("Ok, not viewing reports.\n");
                return false;
            } else {
                System.out.println("Error: Enter y/n please.");
            }
        }
    }

    public static void displayRegisteredPatients() {
        System.out.println("Displaying all patients and their information: ");
        
        System.out.println("There are " + (Inpatient.listOfInpatients.size() + Patient.listOfPatients.size()) + " registered patients");
        
        for (int x = 0; x < Patient.listOfPatients.size(); x++) {
            System.out.println("////////////////////////////////////////////////////"
                    + "\nPatient " + (x + 1) + " (" + Patient.listOfPatients.get(x).PatientID + ") :"
                    + "\n\tCatagory: " + Patient.listOfPatients.get(x).Category
                    + "\n\tName: " + Patient.listOfPatients.get(x).FirstName + "," + Patient.listOfPatients.get(x).LastName
                    + "\n\tAge: " + Patient.listOfPatients.get(x).Age
                    + "\n\tGender: " + Patient.listOfPatients.get(x).theGender
                    + "\n\tCondition: " + Patient.listOfPatients.get(x).MedicalCondition
                    + "\n////////////////////////////////////////////////////\n");
        }
        for (int y = 0; y < Inpatient.listOfInpatients.size(); y++) {
            System.out.println("////////////////////////////////////////////////////"
                    + "\nPatient " + (y + 1) + " (" + Inpatient.listOfPatients.get(y).PatientID + ") :"
                    + "\n\tCatagory: " + Inpatient.listOfInpatients.get(y).Category
                    + "\n\tName: " + Inpatient.listOfInpatients.get(y).FirstName + "," + Inpatient.listOfInpatients.get(y).LastName
                    + "\n\tAge: " + Inpatient.listOfInpatients.get(y).Age
                    + "\n\tGender: " + Inpatient.listOfInpatients.get(y).theGender
                    + "\n\tCondition: " + Inpatient.listOfInpatients.get(y).MedicalCondition
                    + "\nBed: " + Inpatient.listOfInpatients.get(y).getBedNumber()
                    + "\n////////////////////////////////////////////////////\n");
        }
    }

    public static void displayAvailableBeds(Inpatient[][] bed) {
        System.out.println("Here is a diagram of both the occupied and free beds: \n");
        Beds.displayBeds(bed);
        
        System.out.println("Occupied Bed/s: " + (20 - Inpatient.listOfInpatients.size()));
        System.out.println("Ward Capacity: " + (Inpatient.listOfInpatients.size() / 20) * 100);
    }

    public static boolean reportChoice(Scanner input) {
        System.out.println("Which report would you like to see?\n\t1. Bed Information\n\t2. Patient Information\n\t3. EXIT");

        while (true) {
            int oneOrTwo = input.nextInt();
            input.nextLine(); //munch

            switch (oneOrTwo) {
                case 1:
                    displayRegisteredPatients();
                    break;
                case 2:
                    displayAvailableBeds(Beds.bed);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return false;
                default:
                    System.out.println("Error: Please enter an integer of 1 to 3");
                    break;
            }
        }
    }
}
