package org.example.repository;

import org.example.model.MyValue;
import org.example.model.Person;

import java.util.Comparator;

public class MyComparator implements Comparator<MyValue> {

    @Override
    public int compare(MyValue o1, MyValue o2) {
        Person p1 = o1.getPerson();
        Person p2 = o2.getPerson();
        if (p1.getRegisterNum().equalsIgnoreCase(p2.getRegisterNum()))
            return p1.getParty().compareTo(p2.getParty());
        else
            return p1.getRegisterNum().compareTo(p2.getRegisterNum());
    }
}
