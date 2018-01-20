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
    ScheduledExecutorService service;
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

    public void start(int loggerInterval) {
        if (!running) {
            running = true;
            Runnable runnable = new Runnable() {
                public void run() {
                    writer.write(devices);
                }
            };

            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            service.scheduleAtFixedRate(runnable, 0, loggerInterval, TimeUnit.SECONDS);
        }
        else {
            System.err.println("Warning: Logger is already running.");
        }
    }

    public void end() {
        running = false;
        service.shutdown();
    }


}
