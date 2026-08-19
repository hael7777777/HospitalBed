package com.mycompany.hospitalbed;

public class HospitalBed {

    /*
    The purpose of this project is to shift the administrative 
    work of MediCare Hospital over to a digital interface to
    reduce paper trails and increase efficiency by modernising 
    the approach taken.
    
    The system should:
    - Register and maintain patient information
    - Allocate and release hospital beds
    - Enable viewing of patient and bed information
    - Generate basic ward reports
     */
    public static void main(String[] args) {
        
        //for trouble shooting mid development
        PatientCategory Category = null;
        Gender Gender = null;
        Patient pa = new Patient(0,0,"","",Gender ,"", Category);
        pa.register();
    }
}
