package org.example.repository;

import org.example.model.Person;
import org.example.utils.MyComparator;
import org.example.model.MyValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class MyData {
    //Map<"actual value", MyKey=(Register, FileName)>
    private final HashMap<Person, MyValue> normalData;
//    private HashMap<Person, MyValue> duplicates;
    private final List<MyValue> duplicateData;

    private static MyData INSTANCE;

    private MyData(HashMap<Person, MyValue> normalData, List<MyValue> duplicateData) {
        this.normalData = normalData;
        this.duplicateData = duplicateData;
    }

    public static MyData getInstance() {
        if (INSTANCE == null)
            INSTANCE = new MyData(new HashMap<>(), new ArrayList<>());
        return INSTANCE;
    }

    public HashMap<Person, MyValue> getNormalData() {
        return normalData;
    }

//    public void setDataMap(HashMap<Person, MyValue> dataMap) {
//        this.dataMap = dataMap;
//    }

//    public void setDuplicates(HashMap<Person, MyValue> duplicates) {
//        this.duplicates = duplicates;
//    }

    public List<MyValue> getDuplicateData() {
        return duplicateData;
    }
    public void mySort(){
        duplicateData.sort(new MyComparator());
    }
}
