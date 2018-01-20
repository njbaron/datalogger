package com.devknight.datalogger.LoggerDevice;

public abstract class LoggerDevice {

    protected String deviceName;
    protected String[] headers;

    public LoggerDevice(String deviceName) {
        this.deviceName = deviceName;
    }

    abstract public String[] getData();

    public String[] getHeaders() {
        return headers;
    }



}
