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
        headers = new String[] {"x", "y", "z","pitch","roll"};
    }

    public String[] getData() {
        double[] accelData;
        double x = 0;
        double y = 0;
        double z = 0;
        try {
            accelData = mpu.get_accel_data();
            x = Math.round(accelData[0]);
            y = Math.round(accelData[1]);
            z = Math.round(accelData[2]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new String[] {String.format("%03f", x),Double.toString(Math.round(y)), Double.toString(Math.round(z)), Double.toString(Math.round(mpu.getPitch())),Double.toString(Math.round(mpu.getRoll()))};
    }


}
