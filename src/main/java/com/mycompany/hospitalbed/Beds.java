package com.mycompany.hospitalbed;

import java.util.Scanner;

public class Beds {

    public static Inpatient[][] bed = new Inpatient[4][5];

    public static void beds(Scanner input) {
        displayBeds(bed);
    }

    //populating the array
    public static boolean assignBeds(Inpatient p) {
        for (int x = 0; x < bed.length; x++) {
            for (int y = 0; y < bed[x].length; y++) {
                if (bed[x][y] == null) {
                    bed[x][y] = p;

                    int bedNum = (x * bed[0].length) + y + 1;
                    p.setBedNumber(bedNum);
                    return true;
                }
            }
        }
        System.out.println("Error: All beds are full in the ward!");
        return false;
    }

    //printing out the array
    public static void displayBeds(Inpatient bed[][]) {
        for (int x = 0; x < bed.length; x++) {
            for (int y = 0; y < bed[x].length; y++) {
                if (bed[x][y] != null) {
                    System.out.printf("[%-5s]", bed[x][y].getPatientID());
                } else {
                    System.out.printf("[%-5s]", "Empty");
                }
            }
            //printing a \n after each row
            System.out.println();
        }
    }
}
