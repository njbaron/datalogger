package com.devknight.datalogger.LoggerDevice;


import com.devknight.datalogger.FileWriter.FileIO;

public abstract class LoggerDevice {

    private String deviceName;
    private FileIO writer;
    private String[] headers;

    public LoggerDevice(String deviceName) {
        this.deviceName = deviceName;
    }

    abstract public String[] getData();

    public String[] getHeaders() {
        return headers;
    }



}
