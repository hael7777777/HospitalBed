package com.mycompany.hospitalbed;

import java.util.Scanner;

public class Beds {

    public static void beds() {
        Inpatient bed[][] = new Inpatient[4][5];
    }

    public static void initializeBeds(Inpatient bed[][], Scanner input) {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 5; y++) {
                bed[x][y] = Inpatient.details(input);
            }
        }
    }

    public static void displayBeds(Inpatient bed[][], Scanner input) {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 5; y++) {
                System.out.printf("%-4d", bed[x][y]);
            }
        }
    }
}
