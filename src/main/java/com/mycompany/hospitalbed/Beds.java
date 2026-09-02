package com.mycompany.hospitalbed;

import java.util.Scanner;

public class Beds {

    //the bed layout for the scenario is 20 beds in a 4 by 5 format [row][column] / [x][y]
    public static Inpatient[][] bed = new Inpatient[4][5];

    ////////////////////////////initialDisplay//////////////////////////////////
    
    public static void beds(Scanner input) {
        displayBeds(bed);
    }
    
    //////////////////////////////////assign////////////////////////////////////

    //populating the array
    public static boolean assignBeds(Inpatient patientX) {
        for (int x = 0; x < bed.length; x++) {
            for (int y = 0; y < bed[x].length; y++) {
                //checking if space is empty
                if (bed[x][y] == null) {
                    bed[x][y] = patientX;

                    int bedNum = (x * bed[0].length) + y + 1;
                    //setting that patient to that bed
                    patientX.setBedNumber(bedNum);
                    return true;
                }
            }
        }
        System.out.println("Error: All beds are full in the ward!");
        return false;
    }

    ///////////////////////////////////display//////////////////////////////////
    
    //printing out the array
    public static void displayBeds(Inpatient bed[][]) {
        for (int x = 0; x < bed.length; x++) {
            for (int y = 0; y < bed[x].length; y++) {
                if (bed[x][y] != null) {
                    /*
                    - using printf(ormat) to have a structured layout.
                    - I also considered that "Empty" has 5 letters and hence the ID is 5
                    characters to maintain consistency of form.
                    - '%-5' implies the space has to be 5 characters and 's' that there is a string after.
                    */
                    System.out.printf("[%-5s]", bed[x][y].getPatientID());
                } else {
                    System.out.printf("[%-5s]", "Empty");
                }
            }
            //printing a \n after each row
            System.out.println();
        }
    }

    /////////////////////////////////empty//////////////////////////////////////
    
    public static boolean emptyBed(int bedNumber) {
        for (int x = 0; x < bed.length; x++) {
            for (int y = 0; y < bed[x].length; y++) {
                //applying the same indexing logic as when assigning the bed to an impatient
                int currentBed = (x * bed[0].length) + y + 1;
                if (currentBed == bedNumber && bed[x][y] != null) {
                    bed[x][y] = null;
                    return true;
                }
            }
        }
        return false;
    }
}
