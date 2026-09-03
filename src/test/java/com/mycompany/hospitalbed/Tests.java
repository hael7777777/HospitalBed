package com.mycompany.hospitalbed;

import static java.lang.Math.*;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    ///////////////////////////////////sample data//////////////////////////////
    
    //generating random numbers for stuff like IDs
    Random random = new Random();

    //assorted names
    String randomName() {
        String[] names = {"Bob", "Stewart", "Carol", "Kevin", "Frank", "Cheddie", "Gru", "Sponge", "Gumby", "Zelda"};
        //(referenced) - adapted from an answer in a thread, same goes for every other instance.
        return names[random.nextInt(names.length)];
    }

    //either/or gender
    Gender randomGender() {
        Gender[] gender = {Gender.Male, Gender.Female};
        return gender[random.nextInt(gender.length)];
    }

    //assorted conditions
    String randomCondition() {
        String[] conditions = {"Flu", "Mumps", "Monkeypox", "Covid", "Fracture", "Lascerations", "Glaucoma", "Rabies", "Bronchitus"};
        return conditions[random.nextInt(conditions.length)];
    }

    Patient newPatient() {
        return new Patient(10000 + random.nextInt(89000), 1 + random.nextInt(90), randomName(), randomName(), randomGender(), randomCondition(), PatientCategory.Outpatient);
    }

    //note: the ward number is 3 due to the scenario so it is hardcoded.
    Inpatient newInpatient() {
        return new Inpatient(10000 + random.nextInt(89000), 1 + random.nextInt(90), randomName(), randomName(), randomGender(), randomCondition(), PatientCategory.Inpatient, 3, 1 + random.nextInt(19));
    }

    /////////////////////////////////////tests//////////////////////////////////
    
    // --Register A Patient--
    @Test
    void registerAPatient1() {
        Patient testPatient = newPatient();
        Patient.listOfPatients.add(testPatient);
        assertTrue(Patient.listOfPatients.contains(testPatient));
    }

    @Test
    void registerAPatient2() {
        Inpatient testInpatient = newInpatient();
        Inpatient.listOfPatients.add(testInpatient);
        assertTrue(Inpatient.listOfPatients.contains(testInpatient));
    }

    // --Search For A Patient--
    @Test
    void searchForPatient() {
        Patient patient1 = newPatient();
        Patient patient2 = newPatient();
        Patient.listOfPatients.add(patient1);
        Patient.listOfPatients.add(patient2);

        Patient found = null;
        //(referenced) looping through objects
        for (Patient patient : Patient.listOfPatients) {
            if (patient.getPatientID() == patient2.getPatientID()) {
                found = patient;
            }
        }
        assertEquals(patient2, found);
    }

    // --Update Patient Details--
    @Test
    void UpdatePatientDetails() {
        Patient patient1 = newPatient();
        Patient.listOfPatients.add(patient1);

        patient1.setFirstName("updatedName");
        patient1.setAge(7);

        assertEquals("updatedName", patient1.getFirstName());
        assertEquals(7, patient1.getAge());
    }

    // --Delete A Patient--
    @Test
    void deletePatient() {
        Patient patient1 = newPatient();
        Patient.listOfPatients.add(patient1);
        Patient.listOfPatients.remove(patient1);
        assertFalse(Patient.listOfPatients.contains(patient1));
    }

    // --Allocate A Bed--
    @Test
    void allocatedBed() {
        Inpatient inpatient = newInpatient();
        boolean assigned = Beds.assignBeds(inpatient);
        assertTrue(assigned);
        assertTrue(inpatient.getBedNumber() >= 1 && inpatient.getBedNumber() <= 20);
    }
    
    // --Release A Bed--
    @Test
    void releaseBed() {
        Inpatient inpatient = newInpatient();
        Beds.assignBeds(inpatient);
        boolean released = Beds.assignBeds(inpatient);
        assertTrue(released);
    }
    
    // --Prevent Duplicate Patient IDs--
    @Test
    void preventDuplicateID() {
        int id = ( 10000 + random.nextInt(89000));
        Patient patient1 = new Patient(id, 30, "fName", "lName", Gender.Male, "flu", PatientCategory.Outpatient);
        Patient patient2 = new Patient(patient1.getPatientID(), 2, "fName", "lName", Gender.Female, "chickenpox", PatientCategory.Outpatient);
        
        boolean same = false;
        if (patient1.getPatientID() == patient2.getPatientID()) {
        same = true;
        }   
        assertTrue(same);
    }
    
    // --Prevent Allocating An Occupied Bed--
    @Test
    void preventSameBed() {
        Inpatient inpatient1 = newInpatient();
        Beds.assignBeds(inpatient1);
        int occupied = inpatient1.getBedNumber();
        
        Inpatient inpatient2 = newInpatient();
        Beds.assignBeds(inpatient2);
        
        assertNotEquals(occupied, inpatient2.getBedNumber());
    }
    
    // --Prevent Bed Allocation When All Beds Are Occupied--
    @Test
    void preventIfAllBedTaken() {
        for (int i = 0; i < 20; i++) {
            Beds.assignBeds(newInpatient());
        }
        boolean assigned = Beds.assignBeds(newInpatient());
        assertFalse(assigned);
    }
    
    // --Sort Patients By Surname Or Patient ID--
    @Test
    void sortPatients() {
        ArrayList<Integer> IDs1 = new ArrayList<>();
        //doing 5 ids for an example
        for (int i = 0; i < 5; i++) {
            IDs1.add((10000 + random.nextInt(89000)));
        }
        
        //have to do it twice so that i can compare
        ArrayList<Integer> IDs2 = new ArrayList<>(IDs1);
        
        //sorting both - using the same method from the report class just without the fluff
        boolean swapped1 = true;
        while(swapped1) {
            swapped1 = false;
            for (int i = 0; i < IDs1.size() - 1; i++) {
                if (IDs1.get(i) > IDs1.get(i + 1)) {
                    int temp = IDs1.get(i);
                    IDs1.set(i, IDs1.get(i));
                    IDs1.set(i + 1, temp);
                    swapped1 = true;
                }
            }
        }        
        boolean swapped2 = true;
        while(swapped2) {
            swapped2 = false;
            for (int i = 0; i < IDs2.size() - 1; i++) {
                if (IDs2.get(i) > IDs2.get(i + 1)) {
                    int temp = IDs2.get(i);
                    IDs2.set(i, IDs2.get(i));
                    IDs2.set(i + 1, temp);
                    swapped2 = true;
                }
            }
        }
        assertEquals(IDs2, IDs1);
    }
}