package com.devknight.datalogger;

import com.devknight.datalogger.FileWriter.FileIO;
import com.devknight.datalogger.LoggerDevice.LoggerDevice;

import java.io.IOException;
import java.util.ArrayList;

public class DataLogger {

    private FileIO writer;
    private ArrayList<LoggerDevice> devices;
    private boolean running = false;

    public DataLogger(FileIO writer) {
        this.writer = writer;
        devices = new ArrayList<LoggerDevice>();
    }

    public void add(LoggerDevice device) {
        if (!running) {
            devices.add(device);
        }
        else {
            System.err.println("Warning: Cannot add device when logger is running.");
        }
    }

    public void start(int loggerInterval)  throws IOException{
        if (!running) {
            running = true;
            writer.writeHeaders(devices);
            writer.writeData(devices);
        }
        else {
            System.err.println("Warning: Logger is already running.");
        }
    }

    public void end() throws IOException{
        running = false;
        writer.close();
        System.exit(0);
    }


}
