package com.mycompany.hospitalbed;

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
    protected PatientCategory Category;
    protected Gender theGender;

    //costructor for the patient wherein the details of the patient are assigned to an object
    protected Patient(int PatientID, int Age, String FirstName, String LastName, Gender Gender, String MedicalCondition, PatientCategory Category) {
        this.PatientID = PatientID;
        this.Age = Age;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.theGender = Gender;
        this.MedicalCondition = MedicalCondition;
        this.Category = Category;
    }

    //getters and setters for retrieving the "data"
    public int getPatientID() {
        return PatientID;
    }

    public void setPatientID(int PatientID) {
        this.PatientID = PatientID;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getMedicalCondition() {
        return MedicalCondition;
    }

    public void setMedicalCondition(String MedicalCondition) {
        this.MedicalCondition = MedicalCondition;
    }

    public PatientCategory getCategory() {
        return Category;
    }

    public void setCategory(PatientCategory Category) {
        this.Category = Category;
    }

    public Gender getGender() {
        return theGender;
    }

    public void setGender(Gender gender) {
        this.theGender = gender;
    }

    ///////////////////////////////////////////////////Registering///////////////////////////////////////////////////

    static Patient regi(Scanner input) {
        int patientID, age;
        String firstName, lastName, medicalCondition;
        PatientCategory category;
        Gender gender;

        System.out.println("Fill out the following questions about the patient: ");

        System.out.print("First Name: ");
        firstName = input.nextLine();

        System.out.println("Last name: ");
        lastName = input.nextLine();

        age = 0; /////////////
        try {
            System.out.println("Age: ");
            age = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: Enter an integer please");
        }

        System.out.println("Gender - M/F: ");
        String answer = input.nextLine();
        //initializing gender
        gender = null;
        if (answer.toUpperCase().equals("M")) {
            gender = Gender.Male;
        } else if (answer.toUpperCase().equals("F")) {
            gender = Gender.Female;
        }

        System.out.println("Condition: ");
        medicalCondition = input.nextLine();
        
        System.out.println("Category confirmation: \n\t1. Outpatient\n\t2. Emergency");
        int y = input.nextInt();
        //initialized
        category = null;
        switch (y) {
            case 1 : 
                category = PatientCategory.Outpatient;
                    break;
            case 2 : 
                category = PatientCategory.Emergency;
                    break;
            default : System.out.println("Error: Enter a number 1 through 3");
        }
        
        //generating a patientID
        patientID = ThreadLocalRandom.current().nextInt(1000,9999);

        Patient p = new Patient(patientID, age, firstName, lastName, gender, medicalCondition, category);
        return p;
    }
    
    public void displayDetails(){
        System.out.println();
    }
    
}
