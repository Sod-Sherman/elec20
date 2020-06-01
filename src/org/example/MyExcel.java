package org.example;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;

public class MyExcel {
    private File file;
    private Workbook wb;
    private Sheet sht;
    private Row row;
    private Cell cell;

    public MyExcel(File file) throws IOException {
        this.file = file;
        try {
            wb = WorkbookFactory.create(file);
            sht = wb.getSheetAt(0);
        } catch (IOException e) {
            System.out.println("Error: Exception in MyExcel: " + e);
            e.printStackTrace();
        }

    }

    public String getCellValue(int row, int col) {
        if(sht.getRow(row) ==null || sht.getRow(row).getCell(col) == null) return "";
        return new DataFormatter().formatCellValue(sht.getRow(row).getCell(col));
    }

    public Cell[] findCellByValue(String value) {
        return null;
    }

    public void printFirstRows(int row) {
        System.out.println("-----------------------------");

        row = Math.min(row, sht.getLastRowNum() + 1);

        for (int r = 0; r < row; r++) {
            for (int c = sht.getLeftCol(); c < 5; c++) {
                System.out.printf("%s ", getCellValue(r, c));
            }
            System.out.println(" ");
        }
        System.out.println("-----------------------------");
    }

    public Sheet getSht() {
        return sht;
    }
}
