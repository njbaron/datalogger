package com.devknight.datalogger.LoggerDevice;

import com.devknight.datalogger.IODevices.mpu6050;

public class DevMPU6050 extends LoggerDevice{

    private mpu6050 mpu;

    public DevMPU6050(String deviceName) {
        super(deviceName);
        try {
            this.mpu = new mpu6050((byte) 0x69);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        headers = new String[] {"Temp", "x", "y", "z","pitch","roll"};
    }

    public String[] getData() {
        double[] accelData;
        double x = 0;
        double y = 0;
        double z = 0;
        double temp = 0;
        try {
            accelData = mpu.get_accel_data();
            x = accelData[0];
            y = accelData[1];
            z = accelData[2];
            temp = mpu.get_temp();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new String[] {String.format("%.3f", temp), String.format("%.3f", x),String.format("%.3f", y),String.format("%.3f", z),String.format("%.3f", mpu.getPitch()),String.format("%.3f", mpu.getRoll())};
    }


}
