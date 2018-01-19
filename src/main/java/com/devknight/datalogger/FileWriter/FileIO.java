package com.devknight.datalogger.FileWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class FileIO {

    String filePath;
    String fileName;
    BufferedWriter writer;

    public FileIO(String filePath, String fileName) {
        try {
            this.filePath = filePath;
            this.fileName = getUniqueFileName(fileName);
            writer = new BufferedWriter(new FileWriter(this.fileName));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String[] data) {

    }

    private String getUniqueFileName(String requestFileName) {
        
        String tempPathFile =
    }
}
