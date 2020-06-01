package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public final class MyData {
    //Map<"actual value", MyKey=(Register, FileName)>
    private final HashMap<Person, MyValue> dataMap;
//    private HashMap<Person, MyValue> duplicates;
    private final List<MyValue> duplicates;

    private static MyData INSTANCE;

    private MyData(HashMap<Person, MyValue> dataMap, List<MyValue> duplicates) {
        this.dataMap = dataMap;
        this.duplicates = duplicates;
    }

    public static MyData getInstance() {
        if (INSTANCE == null)
            INSTANCE = new MyData(new HashMap<>(), new ArrayList<>());
        return INSTANCE;
    }

    public HashMap<Person, MyValue> getDataMap() {
        return dataMap;
    }

//    public void setDataMap(HashMap<Person, MyValue> dataMap) {
//        this.dataMap = dataMap;
//    }

//    public void setDuplicates(HashMap<Person, MyValue> duplicates) {
//        this.duplicates = duplicates;
//    }

    public List<MyValue> getDuplicates() {
        return duplicates;
    }
    public void mySort(){
        duplicates.sort(new MyComparator());
    }
}
