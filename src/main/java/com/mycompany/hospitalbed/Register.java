package com.mycompany.hospitalbed;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Register {

    /////////////////////////////////////////initial////////////////////////////
    
    public static boolean register(Scanner input) {
        System.out.println("Is your patient an inpatient? (enter '1' or '2') \n\t1. Yes\n\t2. No");
        try {
            while (true) {
                int x = input.nextInt();
                //consuming \n
                input.nextLine();
                switch (x) {
                    case 1:
                        //proceeding to inpatient register
                        Inpatient.details(input);
                        return false;
                    case 2:
                        //proceeding to patient register
                        Patient.details(input);
                        return false;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Enter 1 or 2.");
            //consuming failed input
            input.nextLine();
            //recursion to reattempt
            return register(input);
        }
    }

    //////////////////////////////////repromting////////////////////////////////
    
    public static boolean reprompt(Scanner input) {
        System.out.println("Do you want to register another patient? y/n");
        // trim method to remove whitespace from the answer
        String answer = input.nextLine().trim();
        while (true) {
            if (answer.equalsIgnoreCase("Y")) {
                //proceeding to initial prompt of in- or outpatient
                register(input);
                return false;
            } else if (answer.equalsIgnoreCase("N")) {
                System.out.println("Ok, not registering another patient.\n");
                return false;
            } else {
                System.out.println("Error: Enter y/n please.");
                //recursion for reattempt
                return reprompt(input);
            }
        }
    }

    ///////////////////////////////////////option///////////////////////////////
      
    public static boolean option(Scanner input) {
        System.out.println("Do you want to update a patient's details or discharge a patient?");
        while (true) {
            System.out.println("\t1. Update\n\t2. Discharge\n\t3. Exit");
            try {
                int option = input.nextInt();
                //consuming the buffer from nextInt
                input.nextLine();
                switch (option) {
                    case 1:
                        System.out.println("Updating...");
                        //proceeds to update the patients information
                        updatePatient(input);
                        return true;
                    case 2:
                        System.out.println("Discharging...");
                        //proceeds to delete the to-be selected patient via ID
                        deletePatient(input);
                        return true;
                    case 3:
                        System.out.println("If you wish to exit, input 'Y'");
                        String answer = input.nextLine().trim();
                        //proceeds with the exit
                        return !answer.equalsIgnoreCase("Y");
                    default:
                        System.out.println("Error: Input value 1, 2 or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Input value 1 or 2.");
                //catching \n
                input.nextLine();
            }
        }
    }

    /////////////////////////////////////updating///////////////////////////////
    
    //updating details of the patient
    public static void updatePatient(Scanner input) {
        //checking if there are patients and inpatients
        if (Patient.listOfPatients.isEmpty() && Inpatient.listOfInpatients.isEmpty()) {
            System.out.println("No patients available");
            return;
        }

        //initialized ID
        int wantedID = 0;
        boolean validID = true;
        while (validID) {
            System.out.println("\nEnter patient ID: ");
            try {
                wantedID = input.nextInt();
                //clearing buffer from nextInt
                input.nextLine();
                //ending the loop
                validID = false;
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a numerical value.");
                //consuming the failed input
                input.nextLine();
            }
        }

        //initializing both objects
        Patient updatingPatient = null;
        Inpatient updatingInpatient = null;

        //looping through objects (referenced)
        for (Patient x : Patient.listOfPatients) {
            if (x.getPatientID() == wantedID) {
                updatingPatient = x;
                break;
            }
        }

        //checking if there is a patient being updated
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

    ////////////////////////////////deleting////////////////////////////////////
    
    public static void deletePatient(Scanner input) {
        if (Patient.listOfPatients.isEmpty() && Inpatient.listOfInpatients.isEmpty()) {
            System.out.println("No patients exist!");
            return;
        }

        int toDeleteID = 0;
        boolean validInput = true;
        while (validInput) {
            System.out.println("\nEnter patient ID: ");
            try {
                toDeleteID = input.nextInt();
                validInput = false;
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a numerical value.");
                input.nextLine();
            }
        }
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
            Beds.emptyBed(removableInpatient.getBedNumber());
            Inpatient.listOfInpatients.remove(removableInpatient);
        } else {
            System.out.println("Patient with ID: " + toDeleteID + " not found.");
        }
    }
}