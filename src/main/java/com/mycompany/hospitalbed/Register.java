package com.mycompany.hospitalbed;

import java.util.Scanner;

public class Register {

    public static void register(Scanner input) {
        System.out.println("Please select the category of your patient:\n\t1. Inpatient\n\t2. Outpatient\n\t3. Emergency");
        int x = input.nextInt();
        PatientCategory cat = null;
        switch (x) {
            case 1:
                System.out.println("Registering an inpatient");
                Inpatient.regi(input);
            case 2:
                System.out.println("Registering an outpatient");
                Patient.regi(input);
            case 3:
                System.out.println("Registering an emergency");
                Patient.regi(input);
            default:
                System.out.println("Error: Enter a number 1 through 3");
        }
    }

}
