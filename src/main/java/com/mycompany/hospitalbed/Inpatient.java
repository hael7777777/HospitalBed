package com.mycompany.hospitalbed;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Inpatient extends Patient {

    //creating private variables which are specific to the inpatient
    private int WardNumber, BedNumber;
    static ArrayList<Inpatient> listOfInpatients = new ArrayList<>();

    //constructor of everything belonging to the inpatient.
    public Inpatient(int PatientID, int Age, String FirstName, String LastName, Gender Gender, String MedicalCondition, PatientCategory Category, int WardNumber, int BedNumber) {
        super(PatientID, Age, FirstName, LastName, Gender, MedicalCondition, Category);
        this.WardNumber = WardNumber;
        this.BedNumber = BedNumber;
    }

    public int getWardNumber() {
        return WardNumber;
    }

    public void setWardNumber(int WardNumber) {
        this.WardNumber = WardNumber;
    }

    public int getBedNumber() {
        return BedNumber;
    }

    public void setBedNumber(int BedNumber) {
        this.BedNumber = BedNumber;
    }

    ///////////////////////////////////////////////////Registering///////////////////////////////////////////////////
    static Inpatient details(Scanner input) {
        int patientID, age, wardNumber, bedNumber;
        String firstName, lastName, medicalCondition;
        PatientCategory category;
        Gender gender;

        System.out.println("Fill out the following questions about the patient: ");

        //inputs for the inpatient name
        firstName = "";
        //consuming \n
        input.nextLine();
        //loop for error handling
        boolean flag1 = true;
        while (flag1) {
            System.out.print("First Name: ");
            //trim removes whitespace
            firstName = input.nextLine().trim();
            //checking if input is blank
            if (firstName.isEmpty()) {
                System.out.println("Error: You did not enter a first name.");
            } else {
                flag1 = false;
            }
        }

        //inputs for the inpatients last name
        lastName = "";
        boolean flag2 = true;
        while (flag2) {
            System.out.print("Last name: ");
            //trim removes whitespace
            lastName = input.nextLine().trim();
            //checking if input is blank
            if (lastName.isEmpty()) {
                System.out.println("Error: You did not enter a last name.");
            } else {
                flag2 = false;
            }
        }

        //age input for the inpatient with error handling
        age = 0;
        boolean flag3 = true;
        while (flag3) {
            System.out.print("Age: ");
            try {
                age = input.nextInt();
                if (age <= 0) {
                    System.out.println("Error: Age must be greater than 0.");
                } else {
                    flag3 = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Enter an integer please.");
                //clearing the error
                input.nextLine();
            }
        }
        //consuming \n
        input.nextLine();

        //initializing gender
        gender = null;
        //gender input for inpatient withh error handling
        boolean flag4 = true;
        while (flag4) {
            //gender input for the inpatient 
            System.out.print("Gender - M/F: ");
            String answer = input.nextLine().trim();

            if (answer.equalsIgnoreCase("M")) {
                gender = Gender.Male;
                flag4 = false;
            } else if (answer.equalsIgnoreCase("F")) {
                gender = Gender.Female;
                flag4 = false;
            } else {
                System.out.println("Error: Input M or F.");
            }
        }

        //initializing the condition
        medicalCondition = "";
        //input for the condition of the patient
        boolean flag5 = true;
        while (flag5) {
            System.out.print("Condition: ");
            //trimming whitespace
            medicalCondition = input.nextLine().trim();
            //error handling
            if (medicalCondition.isEmpty()) {
                System.out.println("Error: You did not state the condition.");
            } else {
                flag5 = false;
            }
        }

        //since the person registering selected inpatient, the default category will be inpatient
        category = PatientCategory.Inpatient;

        //generating a patientID
        patientID = ThreadLocalRandom.current().nextInt(10000, 99999);

        //as due to the scenario
        System.out.println("Your inpatient will be in ward 3.");
        wardNumber = 3;

        //creating the inpatient object
        Inpatient inpatient_object = new Inpatient(patientID, age, firstName, lastName, gender, medicalCondition, category, wardNumber, 0);

        //assigning to the first open bed
        boolean assigned = Beds.assignBeds(inpatient_object);

        //whether to add or not
        if (assigned) {
            listOfInpatients.add(inpatient_object);
        }

        //viewing inpatient details
        System.out.println("Do you want to view inpatient's details? y/n");
        boolean flag6 = true;
        while (flag6) {
            //trim to remove the whitespace
            String view = input.nextLine().trim();
            if (view.equalsIgnoreCase("Y")) {
                inpatient_object.displayDetails();
                flag6 = false;
            } else if (view.equalsIgnoreCase("N")) {
                System.out.println("Ok, not displaying details.\n");
                flag6 = false;
            } else if (view.isEmpty()) {
                System.out.println("Error: You did not give an answer.");
            } else {
                System.out.println("Error: You did not give a valid answer (y/n).");
            }
        }
        return inpatient_object;
    }

    /////////////////////////////////display////////////////////////////////////
    @Override
    public void displayDetails() {
        System.out.println("Patient ID: " + getPatientID() + "(" + getCategory() + ")\n////////////////////////////////\n" + getFirstName() + ", "
                + getLastName() + "(" + getGender() + ")" + " is " + getAge() + " years of age and has " + getMedicalCondition()
                + ". They have been placed in ward " + getWardNumber() + ", bed " + getBedNumber() + ".");
    }
}
