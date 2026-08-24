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
    protected PatientCategory Category;
    protected Gender theGender;
    static ArrayList<Patient> listOfPatients = new ArrayList<>();

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
    static Patient details(Scanner input) {
        int patientID, age;
        String firstName, lastName, medicalCondition;
        PatientCategory category;
        Gender gender;

        System.out.println("Fill out the following questions about the patient: ");

        System.out.print("First Name: ");
        //consuming /n (for some reason it only works when i do it this way around)
        input.nextLine();
        firstName = input.nextLine();

        System.out.print("Last name: ");
        lastName = input.nextLine();

        age = 0;
        /////////////
        try {
            System.out.print("Age: ");
            age = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: Enter an integer please");
        }

        //consuming /n
        input.nextLine();
        
        System.out.print("Gender - M/F: ");
        String answer = input.nextLine().trim();
        //initializing gender
        gender = null;
        if (answer.equalsIgnoreCase("M")) {
            gender = Gender.Male;
        } else if (answer.equalsIgnoreCase("F")) {
            gender = Gender.Female;
        }

        System.out.print("Condition: ");
        medicalCondition = input.nextLine();

        System.out.println("Category confirmation: \n\t1. Outpatient\n\t2. Emergency");
        int y = input.nextInt();
        //consuming \n
        input.nextLine();
        //initialized
        category = null;
        switch (y) {
            case 1:
                category = PatientCategory.Outpatient;
                break;
            case 2:
                category = PatientCategory.Emergency;
                break;
            default:
                System.out.println("Error: Enter a number 1 through 3");
        }

        //generating a patientID
        patientID = ThreadLocalRandom.current().nextInt(10000, 99999);

        Patient patient_object = new Patient(patientID, age, firstName, lastName, gender, medicalCondition, category);
        listOfPatients.add(patient_object);
        
        //viewing patient details
        System.out.println("Do you want to view patient details? y/n");
        String view = input.nextLine().trim();
        if (view.equalsIgnoreCase("Y")) {
            patient_object.displayDetails();
        } else if (view.equalsIgnoreCase("N")) {
            System.out.println("Ok, not displaying details.\n");
        }

        Register.reprompt(input);

        return patient_object;
    }

    /////////////////////////////////display////////////////////////////////////
    public void displayDetails() {
        System.out.println("Patient ID: " + getPatientID() + "(" + getCategory() + ")\n////////////////////////////////\n" + getFirstName() + ", "
                + getLastName() + "(" + getGender() + ")" + " is " + getAge() + " years of age and has " + getMedicalCondition() + "." );
    }
    
//    public void update(){
//        
//    }
}
