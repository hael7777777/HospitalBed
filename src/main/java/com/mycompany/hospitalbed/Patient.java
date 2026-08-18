package com.mycompany.hospitalbed;

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
    protected String FirstName, LastName, Gender, MedicalCondition;
    PatientCategory Category;

    //costructor for the patient wherein the details of the patient are assigned to an object
    public Patient(int PatientID, int Age, String FirstName, String LastName, String Gender, String MedicalCondition, PatientCategory Category) {
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
        System.out.println("Patient identification number: " + PatientID + "(" + Category + ")" + "\n\nPatient " + FirstName + ", " + LastName
                + "(" + Gender + ")" + " is " + Age + " years of age and is in " + MedicalCondition + " condition.");
    }
}
