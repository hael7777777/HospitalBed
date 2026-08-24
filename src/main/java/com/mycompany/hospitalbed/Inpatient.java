package com.mycompany.hospitalbed;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Inpatient extends Patient {

    //creating private variables which are specific to the inpatient
    private int WardNumber, BedNumber;
    static ArrayList<Inpatient> listOfInpatients = new ArrayList<>();

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

        System.out.print("First Name: ");
        input.nextLine();//consuming /n (for some reason it only works when i do it this way around)
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
        
        //consuming \n
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

        category = PatientCategory.Inpatient;

        //generating a patientID
        patientID = ThreadLocalRandom.current().nextInt(10000, 99999);

        //as due to the scenario
        System.out.println("Your inpatient will be in ward 3");
        wardNumber = 3;

        Inpatient inpatient_object = new Inpatient(patientID, age, firstName, lastName, gender, medicalCondition, category, wardNumber, 0);
        
        //assigning to the first open bed
        boolean assigned = Beds.assignBeds(inpatient_object);
        
        //whether to add or not
        if (assigned){
            listOfInpatients.add(inpatient_object);
        }
        
        //viewing inpatient details
        System.out.println("Do you want to view patient details? y/n");
        String view = input.nextLine().trim();
        if (view.equalsIgnoreCase("Y")) {
            inpatient_object.displayDetails();
        } else if (view.equalsIgnoreCase("N")) {
            System.out.println("Ok, not displaying details.\n");
        }
        
        return inpatient_object;
    }
    
    /////////////////////////////////display////////////////////////////////////
    @Override
    public void displayDetails() {
        System.out.println("Patient ID: " + getPatientID() + "(" + getCategory() + ")\n////////////////////////////////\n" + getFirstName() + ", "
                + getLastName() + "(" + getGender() + ")" + " is " + getAge() + " years of age and has " + getMedicalCondition() +
                ". They have been placed in ward " + getWardNumber() + ", bed " + getBedNumber() + ".");
    }

}
