package org.example.service;

import org.apache.poi.ss.usermodel.Sheet;
import org.example.Constants;
import org.example.model.MyValue;
import org.example.repository.MyComparator;
import org.example.repository.MyData;
import org.example.model.Person;
import org.example.utils.MyExcel;
import org.example.utils.MyFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private final MyFile myFile;
    private final MyData myData = MyData.getInstance();
    private boolean[] filters;

    public final static int FIRST_REGISTER_ROW = 2;
    public final static int FIRST_REGISTER_COL = 1;

    public Service() {
        myFile = new MyFile();
        this.filters = new boolean[]{true, false, false, false, false, false};
    }

    public void setFilters(boolean[] filters) {
        this.filters = filters;
    }

    public void printDuplicatePerson() {
        System.out.println("Duplicated records: -----> " + myData.getDuplicateData());
        System.out.println("Total duplicated person number: " + myData.getDuplicateData().size());
    }

    public void printExtraOrdinaryPerson() {
//        System.out.println("printExtraOrdinaryPerson = " + myData.getDataMap());
        System.out.println("Total extra ordinary person number: " + myData.getRawData().size());
    }

    public void readFilesInFolder(Service myService) throws IOException {
        myFile.readFiles(myService);
    }

    public List<File> getFilesInFolder() {
        return myFile.getFilesInFolder();
    }


    public void printFilesInFolder() {
        myFile.printFilesInFolder();
    }

    public void printFirstRows(int row, File file) throws IOException {
        MyExcel myExcel = new MyExcel(file);
        myExcel.printFirstRows(row);
    }

    //new boolean[] {true, false, true, false, true, false}
    public void generateDataSet(File file)
            throws IOException {

        HashMap<Person, MyValue> dataMap = myData.getRawData();
        Person person;
        MyValue myValue;

        MyExcel myExcel = new MyExcel(file);
        Sheet sht = myExcel.getSht();

        for (int row = FIRST_REGISTER_ROW; row <= sht.getLastRowNum(); row++) {

            person = new Person.Builder()
                    .withRegisterNum(myExcel.getCellValue(row, FIRST_REGISTER_COL).trim())
                    .withSureName(myExcel.getCellValue(row, FIRST_REGISTER_COL + 1).trim())
                    .withFirstName(myExcel.getCellValue(row, FIRST_REGISTER_COL + 2).trim())
                    .withLastName(myExcel.getCellValue(row, FIRST_REGISTER_COL + 3).trim())
                    .withSex(myExcel.getCellValue(row, FIRST_REGISTER_COL + 4).trim())
                    .withAddress(myExcel.getCellValue(row, FIRST_REGISTER_COL + 5).trim())
                    .withFlags(filters)
                    .withOruulagch(getOruulagch(file.getName()))
                    .withParty(getParty(file.getName()))
                    .build();
            myValue = new MyValue(file.getName());
            person.setMyValue(myValue);
            myValue.setPerson(person);

            if (dataMap.containsKey(person)) {
                if (dataMap.get(person).getDuplicateCounter() == 0)
                    myData.getDuplicateData().add(person.getMyValue());
                myValue = dataMap.get(person);
                myValue.incrementDuplicateCounter();
                myValue.addFileName(file.getName());
                person.setMyValue(myValue);
                myData.getDuplicateData().add(person.getMyValue());
            }
            dataMap.put(person, person.getMyValue());
        }
    }

    public void writeOutputFile() throws IOException {
        myData.mySort();
        WriteFile.write(myData, new String[]{"Давхцалт-Filter " + booleanArrayToString(filters), "Эрүүл"});
    }

    private String booleanArrayToString(boolean[] a) {
        StringBuilder sb = new StringBuilder();

        for (boolean b : a) {
            sb.append(b ? 1 : 0);
        }
        return sb.toString();
    }

    public String getParty(String fileName) {
        String[] res = fileName.split("[_.]");
        Constants.addParty(res.length < 3 ? "МАН" : res[1]);
        return res.length < 3 ? "МАН" : res[1];
    }

    public String getOruulagch(String fileName) {
        return fileName.split("[_.]")[0];
    }
}
