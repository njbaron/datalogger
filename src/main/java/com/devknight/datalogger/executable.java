package com.devknight.datalogger;

import com.devknight.datalogger.FileWriter.FileIO;
import com.devknight.datalogger.LoggerDevice.DevADC;
import com.devknight.datalogger.LoggerDevice.DevMPU6050;

public class executable {

    public static void main(String[] args) {
        System.out.println("Welcome to datalogger.");
        try {
            DataLogger logger1 = new DataLogger(new FileIO("logger1"));
            logger1.add(new DevMPU6050("MPU6050_0"));
            logger1.add(new DevADC("MCP3008_0"));
            logger1.start(500);
            for(int i = 0; i < 60; i++) {
                Thread.sleep(1000);
            }
            logger1.end();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
