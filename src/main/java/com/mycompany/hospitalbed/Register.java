package com.mycompany.hospitalbed;

import java.util.Scanner;

public class Register {

    /////////////////////////////////////////initial////////////////////////////
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

    ////////////////////////////////////////////////////////////////////////////
    
    public static void option(Scanner input) {
        System.out.println("Do you want to update a patient's details or discharge a patient?");
        boolean flag = true;
        while (flag) {
            System.out.println("\t1. Update\n\t2. Discharge\n\t3. EXIT");
            int option = input.nextInt();
            //consuuuuume
            input.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Updating...");
                    updatePatient(input);
                    flag = false;
                    break;
                case 2:
                    System.out.println("Discharging...");
                    deletePatient(input);
                    flag = false;
                    break;
                case 3:
                    System.out.println("Exiting...");
                    reprompt(input);
                    flag = false;
                    break;
                default:
                    System.out.println("Error: input value 1 or 2.");
            }
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
        //if no patients exist then it goes to inpatients
        if (updatingPatient != null) {
            updatingPatient.updateDetails(input);
        } else if (updatingInpatient != null) {
            updatingInpatient.updateDetails(input);
        } else {
            System.out.println("Patient with ID: " + wantedID + " not found.");
        }
    }
    
   public static void deletePatient(Scanner input) {
       if (Patient.listOfPatients.isEmpty() && Inpatient.listOfInpatients.isEmpty()) {
            System.out.println("No patients exist!");
            return;
        }

        System.out.println("\nEnter patient ID: ");
        int toDeleteID = input.nextInt();
        //we did next int so i need to consume \n
        input.nextLine();

        Patient removablePatient = null;
        Inpatient removableInpatient = null;

        //looping through objects (referenced)
        for (Patient x : Patient.listOfPatients) {
            if (x.getPatientID() == toDeleteID) {
                removablePatient = x;
                break;
            }
        }
        //if no patients exist then it goes to inpatients
        if (removablePatient == null) {
            for (Inpatient y : Inpatient.listOfInpatients) {
                if (y.getPatientID() == toDeleteID) {
                    removableInpatient = y;
                    break;
                }
            }
        }
        if (removablePatient != null) {
            //delete
            Patient.listOfPatients.remove(removablePatient);
        } else if (removableInpatient != null) {
            //delete
            Inpatient.listOfInpatients.remove(removableInpatient);
        } else {
            System.out.println("Patient with ID: " + toDeleteID + " not found.");
        }
   }
}
