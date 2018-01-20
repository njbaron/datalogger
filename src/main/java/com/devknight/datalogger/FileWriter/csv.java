package com.devknight.datalogger.FileWriter;

import com.devknight.datalogger.LoggerDevice.LoggerDevice;

import java.io.IOException;
import java.util.ArrayList;

public class csv extends FileIO {

    public csv(String fileName, String[] headers) throws IOException {
        super(fileName, headers);
    }

    public void write(ArrayList<LoggerDevice> devices) {

    }

    public void write(String[] data) {
        String retString = "";
        for(int i = 0; i < data.length-1; i++) {
            retString = retString + data[i] + ",";
        }
        retString = retString + data[data.length] + '\n';
    }
}
