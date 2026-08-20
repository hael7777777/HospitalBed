package com.mycompany.hospitalbed;

import java.util.InputMismatchException;
import java.util.concurrent.ThreadLocalRandom;

public class Inpatient extends Patient {

    //creating private variables which are specific to the inpatient
    private int WardNumber, BedNumber;

    public Inpatient(int PatientID, int Age, String FirstName, String LastName, Gender Gender, String MedicalCondition, PatientCategory Category, int WardNumber, int BedNumber) {
        super(PatientID, Age, FirstName, LastName, Gender, MedicalCondition, Category);
        this.WardNumber = WardNumber;
        this.BedNumber = BedNumber;
    }

    //overridden because we want to use the same method but with the 2 unique variables to this child class, so we repurpose the method.
    @Override
    public void displayDetails() {
        System.out.println("Patient identification number: " + PatientID + "(" + Category + ")" + "\n\nPatient " + FirstName + ", " + LastName
                + "(" + Gender + ")" + " is " + Age + " years of age and is in " + MedicalCondition + " condition. "
                + "\nThey have been admitted to ward " + WardNumber + ", bed " + BedNumber + ".");
    }
    
    @Override
    //adding an inpatient (creating a new inpatient object) (method returns an object therefore not void, instead, Inpatient is the object returned)
    public Inpatient register() {

        //(this method only gets called if patient is inpatient therefore it is a given)
        Category = PatientCategory.Inpatient;
        
        //initial prompt
        System.out.print("To register a inpatient, please follow the proceeding questions:\n");

        //error handling for incorrect inputs
        boolean flag1 = true;
        while (flag1) {

            //input of inpatient first name
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

            //input of inpatient last name
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

            //input of inpatient gender
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
                System.out.println("Error: Please enter if inpatient is male or female");
            }
        }

        boolean flag4 = true;
        while (flag4) {
            //exception handeling for user misinputs (safe exit)
            try {
                //input of inpatient age:
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
        //input of inpatient medical condition
        System.out.print("- Medical condition: ");
        MedicalCondition = input.nextLine();
        
        //generating patient ID (4 digits)
        this.PatientID = ThreadLocalRandom.current().nextInt(1000,9999);     
        Inpatient Inpatient = new Inpatient(PatientID, Age, FirstName, LastName, Gender, MedicalCondition, Category, WardNumber, BedNumber);
        //adding to the list of inpatients for the beds to later be assigned
        PatientList.add(Inpatient);
        return Inpatient;
    }
}
