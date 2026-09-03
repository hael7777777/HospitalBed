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
        return new Patient(10000 + random.nextInt(89000), 1 + random.nextInt(90), randomName(), randomName(),randomGender(), randomCondition(), PatientCategory.Outpatient);
    }
    
    //note: the ward number is 3 due to the scenario so it is hardcoded.
    Inpatient newInpatient() {
        return new Inpatient(10000 + random.nextInt(89000), 1 + random.nextInt(90), randomName(), randomName(), randomGender(), randomCondition(), PatientCategory.Inpatient, 3, 1 + random.nextInt(19));
    }
    
    /////////////////////////////////////tests//////////////////////////////////
    
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
    
    @Test
    void UpdatePatientDetails() {
        Patient patient1 = newPatient();
        Patient.listOfPatients.add(patient1);
        
        patient1.setFirstName("updatedName");
        patient1.setAge(7);
        
        assertEquals("updatedName", patient1.getFirstName());
        assertEquals(7, patient1.getAge());
    }
    
    @Test
    void deletePatient() {
        boolean exists = false;
        Patient patient1 = newPatient();
        Patient.listOfPatients.add(patient1);
        Patient.listOfPatients.remove(patient1);
        if(Patient.listOfPatients.contains(patient1)) {
            exists =  true;
        }
        assertTrue(exists);
    }
}
