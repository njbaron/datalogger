package com.devknight.datalogger.FileWriter;

import com.devknight.datalogger.LoggerDevice.LoggerDevice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileIO {

    private String fileName;
    protected BufferedWriter writer;

    public FileIO(String fileName) throws IOException {
        this.fileName = fileName;
        writer = new BufferedWriter(new FileWriter(this.fileName));
    }

    public void writeData(ArrayList<LoggerDevice> devices) throws IOException{
        writeTime();
        for(LoggerDevice device : devices) {
            write(device.getData());
        }
        newLine();
    }

    public void write(String[] data) throws IOException{
        String retString = ",";
        for(int i = 0; i < data.length-1; i++) {
            retString = retString + data[i] + ",";
        }
        retString = retString + data[data.length-1];
        writer.write(retString);
    }

    public void writeHeaders(ArrayList<LoggerDevice> devices) throws IOException{
        writeDate();
        for(LoggerDevice device: devices) {
            write(device.getHeaders());
        }
        newLine();
    }

    public String getFileName() {
        return fileName;
    }

    public void writeTime() throws IOException{
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        writer.write("Time: " + dateFormat.format(date)); //2016/11/16 12:08:43
    }

    public void writeDate() throws IOException{
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        writer.write("Date: " + dateFormat.format(date)); //2016/11/16 12:08:43
    }

    public void newLine() throws IOException{
        writer.write('\n');
    }

    public void close() throws IOException {
        writer.close();
    }
}
