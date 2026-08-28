package com.mycompany.hospitalbed;

import java.util.Scanner;

public class Register {

    public static void register(Scanner input) {
        System.out.println("Is your patient an inpatient? (enter '1' or '2') \n\t1. Yes\n\t2. No");
        int x = input.nextInt();
        switch (x) {
            case 1:
                Inpatient.details(input);
                break;
            case 2:
                Patient.details(input);
                break;
            default:
                System.out.println("Error: Enter 1 or 2.");
                return;
        }
    }

    public static void reprompt(Scanner input) {
        System.out.println("Do you want to register another patient? y/n");
        String answer = input.nextLine().trim();

        if (answer.equalsIgnoreCase("Y")) {
            register(input);
        } else if (answer.equalsIgnoreCase("N")) {
            System.out.println("Ok, not registering another patient.\n");
        }
    }

    //updating details of the patient
    public static void updatePatient(Scanner input) {
        if (Patient.listOfPatients.isEmpty() && Inpatient.listOfInpatients.isEmpty()) {
            System.out.println("No patients available");
            return;
        }

        System.out.println("\nEnter patient ID: ");
        int wantedID = input.nextInt();
        //we did next int so i need to consume \n
        input.nextLine();

        Patient updatingPatient = null;
        Inpatient updatingInpatient = null;

        //looping through objects (referenced)
        for (Patient x : Patient.listOfPatients) {
            if (x.getPatientID() == wantedID) {
                updatingPatient = x;
                break;
            }
        }
        if (updatingPatient == null) {
            for (Inpatient y : Inpatient.listOfInpatients) {
                if (y.getPatientID() == wantedID) {
                    updatingInpatient = y;
                    break;
                }
            }
        }
        if (updatingPatient != null) {
            updatingPatient.updateDetails(input);
        } else if (updatingInpatient != null) {
            updatingInpatient.updateDetails(input);
        } else {
            System.out.println("Patient with ID: " + wantedID + " not found.");
        }
    }
}
