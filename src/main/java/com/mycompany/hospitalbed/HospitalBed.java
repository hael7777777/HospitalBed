package com.mycompany.hospitalbed;

import java.util.Scanner;

public class HospitalBed {

    /*
        Please view my public GitHub repo @hael7777777 to see:
        - Commit history (if needed)
        - README
        - UML diagrams 
    
    ////////////////////////////////////////////////////////////////
    // The purpose of this project is to shift the administrative //
    // work of MediCare Hospital over to a digital interface to   //
    // reduce paper trails and increase efficiency by modernising //
    // the approach taken.                                        //
    //                                                            //
    ////////////////////////////////////////////////////////////////
    //                                                            //
    // The system should:                                         //   
    // - Register and maintain patient information                //
    // - Allocate and release hospital beds                       //
    // - Enable viewing of patient and bed information            //
    // - Generate basic ward reports                              //
    //                                                            //
    ////////////////////////////////////////////////////////////////
    //                                                            //
    // Assumptions:                                               //
    // - 1 Ward (3)                                               //
    // - 20 beds in ward                                          //
    // - one bed at a time for a patient                          // 
    // - one patient per bed at a time                            //
    // - outpatients and emergency dont require beds              //
    // - all information is stored in memory during runtime       //
    // - console based and menu-driven                            //
    ////////////////////////////////////////////////////////////////
    
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            Register.register(input);

            System.out.println("\n--- ward 3 ---");
            Beds.beds(input);

            //goes to updating details or deleting a patient
            running = Register.option(input);
        }
        
        System.out.println("Closing!");
        input.close();
    }
}
