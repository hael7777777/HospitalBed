package com.mycompany.hospitalbed;

import java.util.Scanner;

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
    
    For the sake of the scenario, the "hospital ward" which "contains 20 beds" as mentioned in Feature 2
    will be ward 3. ^ therefore the "WardNumber" will be fixed at 3.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Register.register(input);
    }
}
