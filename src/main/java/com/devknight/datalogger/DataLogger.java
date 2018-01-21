package com.devknight.datalogger;

import com.devknight.datalogger.FileWriter.FileIO;
import com.devknight.datalogger.LoggerDevice.LoggerDevice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.*;

public class DataLogger {

    private FileIO writer;
    private ArrayList<LoggerDevice> devices;
    private ScheduledExecutorService service;
    private Runnable runnable;
    private boolean running = false;

    public DataLogger(final FileIO writer) {
        this.writer = writer;
        devices = new ArrayList<LoggerDevice>();
        service = Executors.newSingleThreadScheduledExecutor();
        runnable = new Runnable() {
            public void run() {
                try {
                    writer.writeData(devices);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
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
            service.scheduleAtFixedRate(runnable,0, loggerInterval, TimeUnit.MILLISECONDS );
        }
        else {
            System.err.println("Warning: Logger is already running.");
        }
    }

    public void end() throws IOException{
        running = false;
        writer.close();
        service.shutdown();
        System.exit(0);
    }


}
