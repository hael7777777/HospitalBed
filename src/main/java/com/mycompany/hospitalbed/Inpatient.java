package com.mycompany.hospitalbed;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Inpatient extends Patient {

    //creating private variables which are specific to the inpatient
    private int WardNumber, BedNumber;

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

    @Override
    public int getPatientID() {
        return PatientID;
    }

    @Override
    public void setPatientID(int PatientID) {
        this.PatientID = PatientID;
    }

    @Override
    public int getAge() {
        return Age;
    }

    @Override
    public void setAge(int Age) {
        this.Age = Age;
    }

    @Override
    public String getFirstName() {
        return FirstName;
    }

    @Override
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    @Override
    public String getLastName() {
        return LastName;
    }

    @Override
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    @Override
    public String getMedicalCondition() {
        return MedicalCondition;
    }

    @Override
    public void setMedicalCondition(String MedicalCondition) {
        this.MedicalCondition = MedicalCondition;
    }

    @Override
    public PatientCategory getCategory() {
        return Category;
    }

    @Override
    public void setCategory(PatientCategory Category) {
        this.Category = Category;
    }

    @Override
    public Gender getGender() {
        return theGender;
    }

    @Override
    public void setGender(Gender gender) {
        this.theGender = gender;
    }

    ///////////////////////////////////////////////////Registering///////////////////////////////////////////////////
    static Inpatient regi(Scanner input) {
        int patientID, age, wardNumber, bedNumber;
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
        
        category = PatientCategory.Inpatient;
        
        //generating a patientID
        patientID = ThreadLocalRandom.current().nextInt(1000,9999);
        
        //as due to the scenario
        System.out.println("Your inpatient will be in ward 3");
        wardNumber = 3;
        
        bedNumber = 4;

        Inpatient q = new Inpatient(patientID, age, firstName, lastName, gender, medicalCondition, category, wardNumber, bedNumber);
        return q;
    }
    
    
}
