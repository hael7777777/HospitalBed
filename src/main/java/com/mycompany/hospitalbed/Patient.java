package com.mycompany.hospitalbed;

import java.util.InputMismatchException;
import java.util.Scanner;

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
    //instantiating my variables which are to assigned to the patient object (protected so that the child class can still access)
    protected int PatientID, Age;
    protected String FirstName, LastName, MedicalCondition;
    PatientCategory Category;
    Gender Gender;
    Scanner input = new Scanner(System.in);

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

    //adding a patient (creating a new patient object)
    public void register() {
        //exception handeling for user misinputs (safe exit)
        try {

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

            //input of patient gender
            System.out.print("- Gender, M / F: ");
            //trim method to remove white space, upper case method to standardize.
            String answer = input.nextLine().trim().toUpperCase();
            if (answer.equals("M") || answer.equals("MALE")) {
                Gender = Gender.Male;
            } else if (answer.equals("F") || answer.equals("FEMALE")) {
                Gender = Gender.Female;
            }

            //input of patient age:
            System.out.print("- Age: ");
            Age = input.nextInt();

            //consuming the \n
            input.nextLine();

            //input of patient medical condition
            System.out.print("- Medical condition: ");
            MedicalCondition = input.nextLine();

            /*
        - input of patient category
        - simple while loop for if defualt is activated to repromt
        - the sentinal value means that the prompt runs while the condition is met, defualt doesnt change the condition so it runs again.
             */
            boolean sentinal = true;
            while (sentinal) {
                System.out.println("- Category: \n\t1. Inpatient\n\t2. Outpatient\n\t3. Emergency");
                int x = input.nextInt();
                switch (x) {
                    case 1:
                        Category = PatientCategory.Inpatient;
                        sentinal = false;
                        break;

                    case 2:
                        Category = PatientCategory.Outpatient;
                        sentinal = false;
                        break;

                    case 3:
                        Category = PatientCategory.Emergency;
                        sentinal = false;
                        break;

                    default:
                        System.out.println("Error: Please answer with either 1, 2 or 3.");

                }
            }
            Patient registeredPatient = new Patient(PatientID, Age, FirstName, LastName, Gender, MedicalCondition, Category);
        } catch (InputMismatchException e) {
            System.out.println("Error: Please enter the correct details");
        }
    }
}
