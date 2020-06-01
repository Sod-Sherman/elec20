package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class MyFile {
    private List<File> filesInFolder = new LinkedList<>();
    private List<File> outputFiles = new LinkedList<>();
    private Service myService;

    public void readFiles(Service myService) throws IOException {
        this.myService = myService;
                Files.walk(Paths.get("input"))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .forEach(file -> {
                    try {
                        filesInFolder.add(file);
                        myService.generateDataSet(file);
                    } catch (IOException e) {
                        System.out.println("Error: in readFiles");
                        e.printStackTrace();
                    }
                });


    }

    public List<File> getFilesInFolder() {
        return this.filesInFolder;
    }

    public void setOutputFiles(List<File> files) {
        this.outputFiles = files;
    }

    public int getFileCount() {
        return filesInFolder.size();
    }

    public void printFilesInFolder() {
        if (filesInFolder == null) {
            System.out.println("No Files~!!");
            return;
        }
        System.out.println("Total files list (Нийт файл жагсаалт): ");
        int c = 0;
        for (File file : filesInFolder) {
            System.out.println(++c + ". " + file.getName());
        }
        System.out.println("Total number of files (Уншигдсан файлын тоо): " + c);
    }
}
