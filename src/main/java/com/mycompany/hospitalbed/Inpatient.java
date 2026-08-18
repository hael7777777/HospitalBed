package com.mycompany.hospitalbed;

public class Inpatient extends Patient {

    private int WardNumber, BedNumber;

    public Inpatient(int PatientID, int Age, String FirstName, String LastName, String Gender, String MedicalCondition, PatientCategory Category, int WardNumber, int BedNumber) {
        super(PatientID, Age, FirstName, LastName, Gender, MedicalCondition, Category);
        this.WardNumber = WardNumber;
        this.BedNumber = BedNumber;
    }

    @Override
    public void displayDetails() {
        System.out.println("Patient identification number: " + PatientID + "(" + Category + ")" + "\n\nPatient " + FirstName + ", " + LastName
                + "(" + Gender + ")" + " is " + Age + " years of age and is in " + MedicalCondition + " condition. "
                + "\nThey have been admitted to ward " + WardNumber + ", bed " + BedNumber + ".");
    }
}
