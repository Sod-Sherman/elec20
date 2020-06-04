package org.example.service;

import org.apache.poi.ss.usermodel.Sheet;
import org.example.model.MyValue;
import org.example.repository.MyData;
import org.example.model.Person;
import org.example.utils.MyExcel;
import org.example.utils.MyFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Service {
    private final MyFile myFile;
    private final MyData myData = MyData.getInstance();
    private boolean[] filters;

    public final static int FIRST_REGISTER_ROW = 2;
    public final static int FIRST_REGISTER_COL = 1;

    public Service() {
        myFile = new MyFile();
        this.filters = new boolean[] {true, true, true, true, true, false};
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
        System.out.println("Total extra ordinary person number: " + myData.getNormalData().size());
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

        HashMap<Person, MyValue> dataMap = myData.getNormalData();
        Person person;
        MyExcel myExcel = new MyExcel(file);
        Sheet sht = myExcel.getSht();
        MyValue myValue;

        for (int row = FIRST_REGISTER_ROW; row <= sht.getLastRowNum(); row++) {

            myValue = new MyValue(file.getName());

            person = new Person.Builder()
                    .withRegisterNum(myExcel.getCellValue(row, FIRST_REGISTER_COL))
                    .withSureName(myExcel.getCellValue(row, FIRST_REGISTER_COL + 1))
                    .withFirstName(myExcel.getCellValue(row, FIRST_REGISTER_COL + 2))
                    .withLastName(myExcel.getCellValue(row, FIRST_REGISTER_COL + 3))
                    .withSex(myExcel.getCellValue(row, FIRST_REGISTER_COL + 4))
                    .withAddress(myExcel.getCellValue(row, FIRST_REGISTER_COL + 5))
                    .withFlags(filters)
                    .withOruulagch(getOruulagch(file.getName()))
                    .withParty(getParty(file.getName()))
                    .build();
            person.setMyValue(myValue);

            myValue.setPerson(person);

            if (dataMap.containsKey(person)) {
                myValue = dataMap.get(person);
                myValue.incrementDuplicateCounter();
                myValue.addFileName(file.getName());
                myData.getDuplicateData().add(person.getMyValue());
                myData.getDuplicateData().add(dataMap.get(person));
                dataMap.remove(person);

            } else {
                dataMap.put(person, person.getMyValue());
            }

        }
        //  return dataMap;
    }

    public void writeOutputFile() throws IOException {
        myData.mySort();
        WriteFile.write(myData, new String[]{"Давхцалт-Filter " + booleanArrayToString(filters), "Эрүүл"});
    }

    private String booleanArrayToString(boolean[] a){
        StringBuilder sb = new StringBuilder();

        for(boolean b : a){
            sb.append(b ? 1 : 0);
        }
        return sb.toString();
    }

    public String getParty(String fileName){
        String[] res = fileName.split("[_.]");
        return res.length < 3 ? "МАН" : res[1];
    }
    public String getOruulagch(String fileName){
        return fileName.split("[_.]")[0];
    }
}
