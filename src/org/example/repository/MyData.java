package org.example.repository;

import org.example.model.Person;
import org.example.model.MyValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class MyData {
    //Map<"actual value", MyKey=(Register, FileName)>
    private final HashMap<Person, MyValue> rawData;
//    private HashMap<Person, MyValue> duplicates;
    private final List<MyValue> duplicateData;
    private final List<MyValue> normalData;

    private static MyData INSTANCE;

    private MyData(HashMap<Person, MyValue> rawData, List<MyValue> duplicateData, List<MyValue> normalData) {
        this.rawData = rawData;
        this.duplicateData = duplicateData;
        this.normalData = normalData;
    }

    public static MyData getInstance() {
        if (INSTANCE == null)
            INSTANCE = new MyData(new HashMap<>(), new ArrayList<>(), new ArrayList<>());
        return INSTANCE;
    }

    public HashMap<Person, MyValue> getRawData() {
        return rawData;
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

    public List<MyValue> getNormalData() {
        return normalData;
    }

    public void mySort(){
        duplicateData.sort(new MyComparator());
        normalData.sort(new MyComparator());
    }
}
