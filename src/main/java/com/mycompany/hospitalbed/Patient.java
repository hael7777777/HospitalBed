package com.mycompany.hospitalbed;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Patient {

    /*
    This is the Parent class which serves the purpose of 
    providing a rough outline of what the child classes will 
    contain.
    
    This is done because:
    - reduced redundancy of repetive code
    - greater efficiency of storage usage
    - improved organisation
     */
    //instantiating my variables (and objects) which are to assigned to the patient object (protected so that the child class can still access)
    protected int PatientID, Age;
    protected String FirstName, LastName, MedicalCondition;
    PatientCategory Category;
    Gender Gender;
    Scanner input = new Scanner(System.in);
    ArrayList<Patient> PatientList = new ArrayList<>();

    //costructor for the patient wherein the details of the patient are assigned to an object
    public Patient(int PatientID, int Age, String FirstName, String LastName, Gender Gender, String MedicalCondition, PatientCategory Category) {
        this.PatientID = PatientID;
        this.Age = Age;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Gender = Gender;
        this.MedicalCondition = MedicalCondition;
        this.Category = Category;
    }

    //display method to give information about patient
    public void displayDetails() {
        System.out.println("Patient identification number: " + PatientID + "(" + Category + ")" + "\n////////////////////////////////////////////"
                + "\nPatient " + FirstName + ", " + LastName
                + " (" + Gender + ")" + " is " + Age + " year/s of age and has " + MedicalCondition);
    }

    //adding a patient (creating a new patient object) (method returns an object therefore not void, instead, Patient is the object returned)
    public Patient register() {

        //initial prompt
        System.out.print("To register a patient, please follow the proceeding questions:\n");

        //error handling for incorrect inputs
        boolean flag1 = true;
        while (flag1) {

            //input of patient first name
            System.out.println("- First name: ");
            FirstName = input.nextLine();
            if (FirstName.matches("^[a-zA-Z]+$")) {
                flag1 = false;
            } else {
                System.out.println("Error: Do not use numbers or leave empty");
                flag1 = true;
            }
        }

        boolean flag2 = true;
        while (flag2) {

            //input of patient last name
            System.out.println("- Last name: ");
            LastName = input.nextLine();
            if (FirstName.matches("^[a-zA-Z]+$")) {
                flag2 = false;
            } else {
                System.out.println("Error: Do not use numbers or leave empty");
                flag2 = true;
            }
        }

        boolean flag3 = true;
        while (flag3) {

            //input of patient gender
            System.out.print("- Gender, M / F: ");
            //trim method to remove white space, upper case method to standardize.
            String answer = input.nextLine().trim().toUpperCase();
            if (answer.equals("M") || answer.equals("MALE")) {
                Gender = Gender.Male;
                flag3 = false;
            } else if (answer.equals("F") || answer.equals("FEMALE")) {
                Gender = Gender.Female;
                flag3 = false;
            } else {
                System.out.println("Error: Please enter if patient is male or female");
            }
        }

        boolean flag4 = true;
        while (flag4) {
            //exception handeling for user misinputs (safe exit)
            try {
                //input of patient age:
                System.out.print("- Age: ");
                Age = input.nextInt();
                if (Age >= 0) {
                    flag4 = false;
                } else {
                    System.out.println("Error: Age cannot be negative");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter an integer");
                //consuming the wrong input
                input.nextLine();
            }
        }
        //consuming the \n
        input.nextLine();
        //input of patient medical condition
        System.out.print("- Medical condition: ");
        MedicalCondition = input.nextLine();
        
        //generating patient ID (4 digits)
        this.PatientID = ThreadLocalRandom.current().nextInt(1000,9999);     
        Patient nonInpatient = new Patient(PatientID, Age, FirstName, LastName, Gender, MedicalCondition, Category);
        //adding to the list of patients
        PatientList.add(nonInpatient);
        return nonInpatient;
    }
}
