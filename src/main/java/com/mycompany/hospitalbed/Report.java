package com.mycompany.hospitalbed;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Report {

    static ArrayList<Integer> IDs = new ArrayList<>();

    public static boolean prompt(Scanner input) {
        while (true) {
            System.out.println("Would you like to view reports? y/n");
            String answer1 = input.nextLine().trim();
            if (answer1.equalsIgnoreCase("Y")) {
                reportChoice(input);
                return false;
            } else if (answer1.equalsIgnoreCase("N")) {
                System.out.println("Ok, not viewing reports.\n");
                return false;
            } else {
                System.out.println("Error: Enter y/n please.");
            }
        }
    }

    public static void displayRegisteredPatients(Scanner input) {
        //IDs get would get duplicated if i called this more than once so i have to make it a clean slate each time
        IDs.clear();

        System.out.println("There are " + (Inpatient.listOfInpatients.size() + Patient.listOfPatients.size()) + " registered patients");
        System.out.println("Displaying all patients and their information: ");

        for (int x = 0; x < Patient.listOfPatients.size(); x++) {
            System.out.println("////////////////////////////////////////////////////"
                    + "\nPatient " + (x + 1) + " (" + Patient.listOfPatients.get(x).PatientID + ") :"
                    + "\n\tCatagory: " + Patient.listOfPatients.get(x).Category
                    + "\n\tName: " + Patient.listOfPatients.get(x).FirstName + "," + Patient.listOfPatients.get(x).LastName
                    + "\n\tAge: " + Patient.listOfPatients.get(x).Age
                    + "\n\tGender: " + Patient.listOfPatients.get(x).theGender
                    + "\n\tCondition: " + Patient.listOfPatients.get(x).MedicalCondition
                    + "\n////////////////////////////////////////////////////\n");
            IDs.add(Patient.listOfPatients.get(x).PatientID);
        }
        for (int y = 0; y < Inpatient.listOfInpatients.size(); y++) {
            System.out.println("////////////////////////////////////////////////////"
                    + "\nPatient " + (y + 1) + " (" + Inpatient.listOfInpatients.get(y).PatientID + ") :"
                    + "\n\tCatagory: " + Inpatient.listOfInpatients.get(y).Category
                    + "\n\tName: " + Inpatient.listOfInpatients.get(y).FirstName + "," + Inpatient.listOfInpatients.get(y).LastName
                    + "\n\tAge: " + Inpatient.listOfInpatients.get(y).Age
                    + "\n\tGender: " + Inpatient.listOfInpatients.get(y).theGender
                    + "\n\tCondition: " + Inpatient.listOfInpatients.get(y).MedicalCondition
                    + "\nBed: " + Inpatient.listOfInpatients.get(y).getBedNumber()
                    + "\n////////////////////////////////////////////////////\n");
            IDs.add(Inpatient.listOfInpatients.get(y).PatientID);
        }
        System.out.println("Would you like the patient IDs displayed in ascending order? y/n");
        boolean sentinalValue = true;
        while (sentinalValue) {
            String answer2 = input.nextLine().trim();
            if (answer2.equalsIgnoreCase("Y")) {

                //variable for loop
                int i;

                //for my encompassing loop
                boolean flag = true;

                //temp holder for position while moving to avoid over-writing
                int temp;

                while (flag) {
                    flag = false;
                    //loop so that each index is sorted till resolution is found
                    for (i = 0; i < IDs.size() - 1; i++) {
                        if (IDs.get(i) > IDs.get(i + 1)) {
                            temp = IDs.get(i);
                            IDs.set(i, IDs.get(i + 1));
                            IDs.set(i + 1, temp);
                            flag = true;
                        }
                    }
                }
                //printing out the ids
                for (int j =0; j < IDs.size(); j++) {
                    System.out.println(IDs.get(j));
                }
                sentinalValue = false;
            } else if (answer2.equalsIgnoreCase("N")) {
                System.out.println("Ok, not viewing sorted IDs.\n");
                sentinalValue = false;
            } else {
                System.out.println("Error: Enter y/n please.");
            }
        }
    }

    public static void displayAvailableBeds(Inpatient[][] bed) {
        System.out.println("Here is a diagram of both the occupied and free beds: \n");
        Beds.displayBeds(bed);

        System.out.println("Occupied Bed/s: " + (Inpatient.listOfInpatients.size()));
        System.out.println("Ward Capacity: " + (Inpatient.listOfInpatients.size() * 100) / 20 + "%");
    }

    public static boolean reportChoice(Scanner input) {
        while (true) {
            System.out.println("Which report would you like to see?\n\t1. Bed Information\n\t2. Patient Information\n\t3. EXIT");
            try {
                int oneOrTwo = input.nextInt();
                input.nextLine(); //munch

                switch (oneOrTwo) {
                    case 1:
                        displayAvailableBeds(Beds.bed);
                        break;
                    case 2:
                        displayRegisteredPatients(input);
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        return false;
                    default:
                        System.out.println("Error: Please enter an integer of 1 to 3");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: you did not enter an integer value");
                input.nextLine();
            }
        }
    }
}
