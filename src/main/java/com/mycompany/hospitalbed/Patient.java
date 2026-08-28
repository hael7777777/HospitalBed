package com.mycompany.hospitalbed;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Patient {

    /*
    This is the Parent class which serves the purpose of 
    providing a rough outline of what the child classes will 
    contain.
    
    This is done because:
    - reduced redundancy of repetive code
    - greater efficiency of storage usage
    - improved organisation
     */
    //instantiating my variables (and objects) which are to assigned to the patient object (protected so that the child class can still access)
    protected int PatientID, Age;
    protected String FirstName, LastName, MedicalCondition;
    protected PatientCategory Category;
    protected Gender theGender;
    static ArrayList<Patient> listOfPatients = new ArrayList<>();

    //costructor for the patient wherein the details of the patient are assigned to an object
    protected Patient(int PatientID, int Age, String FirstName, String LastName, Gender Gender, String MedicalCondition, PatientCategory Category) {
        this.PatientID = PatientID;
        this.Age = Age;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.theGender = Gender;
        this.MedicalCondition = MedicalCondition;
        this.Category = Category;
    }

    //getters and setters for retrieving the "data"
    public int getPatientID() {
        return PatientID;
    }

    public void setPatientID(int PatientID) {
        this.PatientID = PatientID;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getMedicalCondition() {
        return MedicalCondition;
    }

    public void setMedicalCondition(String MedicalCondition) {
        this.MedicalCondition = MedicalCondition;
    }

    public PatientCategory getCategory() {
        return Category;
    }

    public void setCategory(PatientCategory Category) {
        this.Category = Category;
    }

    public Gender getGender() {
        return theGender;
    }

    public void setGender(Gender gender) {
        this.theGender = gender;
    }

    ////////////////////////////////////registering/////////////////////////////
    static Patient details(Scanner input) {
        int patientID, age;
        String firstName, lastName, medicalCondition;
        PatientCategory category;
        Gender gender;

        System.out.println("Fill out the following questions about the patient: ");

        //inputs for the inpatient name
        firstName = "";
        //consuming \n
        input.nextLine();
        //loop for error handling
        boolean flag1 = true;
        while (flag1) {
            System.out.print("First Name: ");
            //trim removes whitespace
            firstName = input.nextLine().trim();
            //checking if input is blank
            if (firstName.isEmpty()) {
                System.out.println("Error: You did not enter a first name.");
            } else {
                flag1 = false;
            }
        }

        //inputs for the inpatients last name
        lastName = "";
        boolean flag2 = true;
        while (flag2) {
            System.out.print("Last name: ");
            //trim removes whitespace
            lastName = input.nextLine().trim();
            //checking if input is blank
            if (lastName.isEmpty()) {
                System.out.println("Error: You did not enter a last name.");
            } else {
                flag2 = false;
            }
        }

        //age input for the inpatient with error handling
        age = 0;
        boolean flag3 = true;
        while (flag3) {
            System.out.print("Age: ");
            try {
                age = input.nextInt();
                if (age <= 0) {
                    System.out.println("Error: Age must be greater than 0.");
                } else {
                    flag3 = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Enter an integer please.");
                //clearing the error
                input.nextLine();
            }
        }
        //consuming \n
        input.nextLine();

        //initializing gender
        gender = null;
        //gender input for inpatient withh error handling
        boolean flag4 = true;
        while (flag4) {
            //gender input for the inpatient 
            System.out.print("Gender - M/F: ");
            String answer = input.nextLine().trim();

            if (answer.equalsIgnoreCase("M")) {
                gender = Gender.Male;
                flag4 = false;
            } else if (answer.equalsIgnoreCase("F")) {
                gender = Gender.Female;
                flag4 = false;
            } else {
                System.out.println("Error: Input M or F.");
            }
        }

        //initializing the condition
        medicalCondition = "";
        //input for the condition of the patient
        boolean flag5 = true;
        while (flag5) {
            System.out.print("Condition: ");
            //trimming whitespace
            medicalCondition = input.nextLine().trim();
            //error handling
            if (medicalCondition.isEmpty()) {
                System.out.println("Error: You did not state the condition.");
            } else {
                flag5 = false;
            }
        }

        //confirming what category thr patient is
        System.out.println("Category confirmation: \n\t1. Outpatient\n\t2. Emergency");
        int y = input.nextInt();
        //consuming \n
        input.nextLine();
        //initialized
        category = null;
        switch (y) {
            case 1:
                category = PatientCategory.Outpatient;
                break;
            case 2:
                category = PatientCategory.Emergency;
                break;
            default:
                System.out.println("Error: Enter a number 1 through 3");
        }

        //generating a patientID (referenced)
        patientID = ThreadLocalRandom.current().nextInt(10000, 99999);

        //creating the patient and adding them to the list of the patients
        Patient patient_object = new Patient(patientID, age, firstName, lastName, gender, medicalCondition, category);
        listOfPatients.add(patient_object);

        //viewing patient details
        System.out.println("Do you want to view patient's details? y/n");
        boolean flag6 = true;
        while (flag6) {
            //trim to remove the whitespace
            String view = input.nextLine().trim();
            if (view.equalsIgnoreCase("Y")) {
                patient_object.displayDetails();
                flag6 = false;
            } else if (view.equalsIgnoreCase("N")) {
                System.out.println("Ok, not displaying details.\n");
                flag6 = false;
            } else if (view.isEmpty()) {
                System.out.println("Error: You did not give an answer.");
            } else {
                System.out.println("Error: You did not give a valid answer (y/n).");
            }
        }

        Register.reprompt(input);

        return patient_object;
    }

    /////////////////////////////////display////////////////////////////////////
    public void displayDetails() {
        System.out.println("Patient ID: " + getPatientID() + "(" + getCategory() + ")\n////////////////////////////////\n" + getFirstName() + ", "
                + getLastName() + "(" + getGender() + ")" + " is " + getAge() + " years of age and has " + getMedicalCondition() + ".");
    }
    
    ////////////////////////////////update_details//////////////////////////////
    public void updateDetails(Scanner input) {
        System.out.println("Which details do you want to update?");
        boolean updateFlag = true;
        while (updateFlag) {
            System.out.println("\t1. Age\n\t2. First Name\n\t3. Last Name\n\t4. Gender\n\t5. Medical Condition\n\t6. Display Details\n\t7. EXIT");
            int num = input.nextInt();
            input.nextLine();
            switch (num) {
                case 1:
                    int updateAge = 0;
                    boolean flag7 = true;
                    while (flag7) {
                        System.out.print("Enter the new age: ");
                        try {
                            updateAge = input.nextInt();
                            if (updateAge <= 0) {
                                System.out.println("Error: Age must be greater than 0.");
                            } else {
                                flag7 = false;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Enter an integer please.");
                            //clearing the error
                            input.nextLine();
                        }
                    }
                    //consuming \n
                    input.nextLine();
                    break;
                case 2:
                    String fName;
                    //loop for error handling
                    boolean flag8 = true;
                    while (flag8) {
                        System.out.print("Enter the new first name:");
                        //trim removes whitespace
                        fName = input.nextLine().trim();
                        //checking if input is blank
                        if (fName.isEmpty()) {
                            System.out.println("Error: You did not enter a first name.");
                        } else {
                            setFirstName(fName);
                            flag8 = false;
                        }
                    }
                    break;
                case 3:
                    String lName;
                    boolean flag9 = true;
                    while (flag9) {
                        System.out.print("Enter the new last name:");
                        //trim removes whitespace
                        lName = input.nextLine().trim();
                        //checking if input is blank
                        if (lName.isEmpty()) {
                            System.out.println("Error: You did not enter a last name.");
                        } else {
                            setLastName(lName);
                            flag9 = false;
                        }
                    }
                    break;
                case 4:
                    //initializing gender
                    Gender gender;
                    //gender input for inpatient withh error handling
                    boolean flag4 = true;
                    while (flag4) {
                        //gender input for the inpatient 
                        System.out.print("Enter the updated gender - M/F:");
                        String gen = input.nextLine().trim();
                        if (gen.equalsIgnoreCase("M")) {
                            setGender(Gender.Male);
                            flag4 = false;
                        } else if (gen.equalsIgnoreCase("F")) {
                            setGender(Gender.Female);
                            flag4 = false;
                        } else {
                            System.out.println("Error: Input M or F.");
                        }
                    }
                    break;
                case 5:
                    boolean flag5 = true;
                    while (flag5) {
                        System.out.print("Enter the new medical condition:");
                        //trimming whitespace
                        String medicalCondition = input.nextLine().trim();
                        //error handling
                        if (medicalCondition.isEmpty()) {
                            System.out.println("Error: You did not state the condition.");
                        } else {
                            setMedicalCondition(medicalCondition);
                            flag5 = false;
                        }
                    }
                    break;
                case 6:
                    displayDetails();
                    break;
                case 7:
                    System.out.println("Exiting the updating section...");
                    updateFlag = false;
                    break;
                default:
                    System.out.println("Error: Invalid option.");
            }
        }
    }
}
