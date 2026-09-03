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
    
    Patient a = new Patient(10000 + random.nextInt(89000), 1 + random.nextInt(90), randomName(), randomName(),randomGender(), randomCondition(), PatientCategory.Emergency);
    Patient b = new Patient(10000 + random.nextInt(89000), 1 + random.nextInt(90), randomName(), randomName(), randomGender(), randomCondition(), PatientCategory.Outpatient);
    //note: the ward number is 3 due to the scenario so it is hardcoded.
    Inpatient c = new Inpatient(10000 + random.nextInt(89000), 1 + random.nextInt(90), randomName(), randomName(), randomGender(), randomCondition(), PatientCategory.Inpatient, 3, 1 + random.nextInt(19));
}
