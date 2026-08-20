package com.mycompany.hospitalbed;

import java.util.Scanner;

public class Intro {

    //instantiating necessary variables
    PatientCategory Category;
    Scanner input = new Scanner(System.in);
    int answer;

    public void Intro() {
        /*
        - input of patient category
        - simple while loop for if defualt is activated to repromt
        - the sentinal value means that the prompt runs while the condition is met, defualt doesnt change the condition so it runs again.
         */
        boolean flag5 = true;
        while (flag5) {
            System.out.println("- Category: \n\t1. Inpatient\n\t2. Outpatient\n\t3. Emergency");
            int x = input.nextInt();
            switch (x) {
                case 1 -> {
                    Category = PatientCategory.Inpatient;
                    
                    flag5 = false;
                }

                case 2 -> {
                    Category = PatientCategory.Outpatient;
                    
                    flag5 = false;
                }

                case 3 -> {
                    Category = PatientCategory.Emergency;
                    flag5 = false;
                }

                default ->
                    System.out.println("Error: Please answer with either 1, 2 or 3.");

            }
        }
    }
}
