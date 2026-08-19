package com.mycompany.hospitalbed;

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
}
