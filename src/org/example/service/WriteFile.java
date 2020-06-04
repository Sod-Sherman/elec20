package org.example.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.model.MyValue;
import org.example.repository.MyData;
import org.example.model.Person;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


public class WriteFile {

    public static void write(MyData myData, String[] sheetName) throws IOException {
        XSSFWorkbook workbook;
        XSSFSheet[] sheet = new XSSFSheet[5];


        List<MyValue> myValues = myData.getDuplicateData();

        //Blank workbook
        workbook = new XSSFWorkbook();

        //This data needs to be written (Object[])
        List<Map<Integer, Object[]>> data = new LinkedList<>();


        for (int i = 0; i < sheetName.length; i++) {
            //Create a blank sheet
            sheet[i] = workbook.createSheet(sheetName[i]);
            data.add(new TreeMap<>());
        }


        data.get(0).put(1, new Object[]{"ID", "Ургийн овог", "Овог", "Нэр", "Утас", "Хаяг", "Регистр", "Нам", "Оруулагч", "Файлын нэр", "Давхцалт"});
        data.get(1).put(1, new Object[]{"ID", "Ургийн овог", "Овог", "Нэр", "Утас", "Хаяг", "Регистр", "Нам", "Оруулагч", "Файлын нэр"});
        for (int k = 0; k < 2; k++) {
            // myValues = k == 0 ? myValues : (List<MyValue>) myData.getDataMap().values();
            int j, i = 2;
            for (MyValue v : k == 0 ? myValues : myData.getNormalData().values()) {
                Person p = v.getPerson();
                Object[] objects = new Object[20];
                objects[0] = i - 1;
                j = 1;
                objects[j++] = p.getSureName();
                objects[j++] = p.getFirstName();
                objects[j++] = p.getLastName();
                objects[j++] = p.getPhone();
                objects[j++] = p.getAddress();
                objects[j++] = p.getRegisterNum();
                objects[j++] = p.getParty();
                objects[j++] = p.getOruulagch();
                for (String s : v.getFileNames()) {
                    objects[j++] = s;
                }
                objects[j] = k == 0 ? v.getDuplicateCounter() : "";


                data.get(k).put(i++, objects);
            }
        }
        for (int i = 0; i < data.size(); i++) {
            Set<Integer> keyset = data.get(i).keySet();
            int rownum = 0;
            Row row;
            for (Integer key : keyset) {
                row = sheet[i].createRow(rownum++);
                Object[] objArr = data.get(i).get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
                    if (obj instanceof String) {
                        if (((String) obj).equalsIgnoreCase("МАН"))
                            cell.getCellStyle().setFillBackgroundColor(IndexedColors.RED.getIndex());
                        else
//                        if (((String) obj).equalsIgnoreCase("АН"))
                            cell.getCellStyle().setFillBackgroundColor(IndexedColors.BLUE.getIndex());
                        cell.setCellValue((String) obj);
                    } else if (obj instanceof Integer)
                        cell.setCellValue((Integer) obj);
                }
            }
            int rowN = 0;
            row = sheet[i].createRow(rownum);
            row.createCell(rowN++).setCellValue("Нийт бичлэг");
            row.createCell(rowN).setCellValue(i == 0 ? myValues.size() : myData.getNormalData().size());

        }

        FileOutputStream out = new FileOutputStream(new File("output.xlsx"));
        try {
            //Write the workbook in file system
            workbook.write(out);
//            out.close();
            System.out.println("output.xlsx has written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    private static void generateObjectArray(List<Map<Integer, Object[]>> data, List<MyValue> myValues) {


    }

//    private static void dataToWriteSheet(List<Map<Integer, Object[]>> data) {
//        //Iterate over data and write to sheet
//        for (int i = 0; i < data.size(); i++) {
//            Set<Integer> keyset = data.get(i).keySet();
//            int rownum = 0;
//            for (Integer key : keyset) {
//                Row row = sheet[i].createRow(rownum++);
//                Object[] objArr = data.get(i).get(key);
//                int cellnum = 0;
//                for (Object obj : objArr) {
//                    Cell cell = row.createCell(cellnum++);
//                    if (obj instanceof String)
//                        cell.setCellValue((String) obj);
//                    else if (obj instanceof Integer)
//                        cell.setCellValue((Integer) obj);
//                }
//            }
//        }
//
//    }


}
