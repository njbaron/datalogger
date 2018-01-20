package com.devknight.datalogger;

import com.devknight.datalogger.FileWriter.FileIO;
import com.devknight.datalogger.LoggerDevice.LoggerDevice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DataLogger {

    private FileIO writer;
    private ArrayList<LoggerDevice> devices;
    private ScheduledExecutorService service;
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
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        writer.writeData(devices);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            service = Executors.newSingleThreadScheduledExecutor();
            service.scheduleAtFixedRate(runnable, 0, loggerInterval, TimeUnit.SECONDS);
        }
        else {
            System.err.println("Warning: Logger is already running.");
        }
    }

    public void end() throws IOException{
        running = false;
        service.shutdown();
        writer.close();
    }


}
