package com.mycompany.hospitalbed;

import java.util.Scanner;

public class Register {

    public static void register(Scanner input) {
        System.out.println("Is your patient an inpatient? (enter '1' or '2') \n\t1. Yes\n\t2. No");
        int x = input.nextInt();
        PatientCategory cat = null;
        switch (x) {
            case 1:
                Inpatient.details(input);
                break;
            case 2:
                Patient.details(input);
                break;
            default:
                System.out.println("Error: Enter a number 1 through 3");
        }
    }

    public static void reprompt(Scanner input) {
        System.out.println("Do you want to register another patient? y/n");
        String answer = input.nextLine().trim();

        if (answer.equalsIgnoreCase("Y")) {
            register(input);
        } else if (answer.equalsIgnoreCase("N")) {
            System.out.println("Ok, not registering another patient.\n");
        }
    }

}
