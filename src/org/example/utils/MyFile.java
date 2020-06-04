package org.example.utils;

import org.example.repository.MyData;
import org.example.service.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MyFile {
    private final List<File> filesInFolder = new LinkedList<>();
    private List<File> outputFiles = new LinkedList<>();

    public void readFiles(Service myService) throws IOException {
        MyData myData = MyData.getInstance();
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
        myData.getNormalData().addAll(myData.getRawData().values().stream().filter(f -> f.getDuplicateCounter() == 0).collect(Collectors.toList()));
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
        System.out.println("Total files list (Нийт файл жагсаалт): ");
        int c = 0;
        for (File file : filesInFolder) {
            System.out.println(++c + ". " + file.getName());
        }
        System.out.println("Total number of files (Уншигдсан файлын тоо): " + c);
    }
}
