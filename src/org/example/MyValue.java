package org.example;

import org.apache.poi.ss.formula.functions.T;

import java.util.HashSet;
import java.util.Set;

public class MyValue implements Comparable<T> {
    private Person person;
    private final Set<String> fileNames;
    private int duplicateCounter;

    public MyValue(String fileName) {
        this.fileNames = new HashSet<>();
        this.fileNames.add(fileName);
        this.duplicateCounter = 0;
    }

    public void incrementDuplicateCounter(){
        ++duplicateCounter;
    }

    public void addFileName(String fileName){
        this.fileNames.add(fileName);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<String> getFileNames() {
        return fileNames;
    }

    public int getDuplicateCounter() {
        return duplicateCounter;
    }

    @Override
    public String toString() {
        return "MyValue{" +
                "person=" + person.getRegisterNum() +
                ", fileNames=" + fileNames +
                ", duplicateCounter=" + duplicateCounter +
                "}\n";
    }



    @Override
    public int compareTo(T o) {
        return 0;
    }
}
