package com.devknight.datalogger.FileWriter;

import com.devknight.datalogger.LoggerDevice.LoggerDevice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class FileIO {

    private String fileName;
    private BufferedWriter writer;
    private ArrayList<LoggerDevice> devices;

    public FileIO(String fileName, String[] headers) throws IOException {
        this.fileName = fileName;
        writer = new BufferedWriter(new FileWriter(this.fileName));
        write(headers);
    }

    abstract public void write(ArrayList<LoggerDevice> devices);

    abstract public void write(String[] data);

    public String getFileName() {
        return fileName;
    }

    public void close() throws IOException {
        writer.close();
    }

}
