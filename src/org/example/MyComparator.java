package org.example;

import java.util.Comparator;

public class MyComparator implements Comparator<MyValue> {


    @Override
    public int compare(MyValue o1, MyValue o2) {
        return o1.getPerson().getRegisterNum().compareTo(o2.getPerson().getRegisterNum());
    }
}
