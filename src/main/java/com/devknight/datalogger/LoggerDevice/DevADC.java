package com.devknight.datalogger.LoggerDevice;

import com.pi4j.gpio.extension.base.AdcGpioProvider;
import com.pi4j.gpio.extension.mcp.MCP3008GpioProvider;
import com.pi4j.gpio.extension.mcp.MCP3008Pin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinAnalogInput;
import com.pi4j.io.spi.SpiChannel;

import java.util.ArrayList;

public class DevADC extends LoggerDevice{

    private AdcGpioProvider provider1;
    private AdcGpioProvider provider2;
    private ArrayList<GpioPinAnalogInput> inputs;
    private double[] values;
    private GpioController gpio;


    public DevADC(String deviceName) {
        super(deviceName);
        headers = makeHeaders();
        values = new double[16];
        gpio = GpioFactory.getInstance();
        try {
            provider1 = new MCP3008GpioProvider(SpiChannel.CS0);
            provider2 = new MCP3008GpioProvider(SpiChannel.CS1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        setInputs();
    }

    private void setInputs() {
        inputs.add(gpio.provisionAnalogInputPin(provider1, MCP3008Pin.CH0, "MyAnalogInput-CH0"));
        inputs.add(gpio.provisionAnalogInputPin(provider1, MCP3008Pin.CH1, "MyAnalogInput-CH1"));
        inputs.add(gpio.provisionAnalogInputPin(provider1, MCP3008Pin.CH2, "MyAnalogInput-CH2"));
        inputs.add(gpio.provisionAnalogInputPin(provider1, MCP3008Pin.CH3, "MyAnalogInput-CH3"));
        inputs.add(gpio.provisionAnalogInputPin(provider1, MCP3008Pin.CH4, "MyAnalogInput-CH4"));
        inputs.add(gpio.provisionAnalogInputPin(provider1, MCP3008Pin.CH5, "MyAnalogInput-CH5"));
        inputs.add(gpio.provisionAnalogInputPin(provider1, MCP3008Pin.CH6, "MyAnalogInput-CH6"));
        inputs.add(gpio.provisionAnalogInputPin(provider1, MCP3008Pin.CH7, "MyAnalogInput-CH7"));
        inputs.add(gpio.provisionAnalogInputPin(provider2, MCP3008Pin.CH0, "MyAnalogInput-CH8"));
        inputs.add(gpio.provisionAnalogInputPin(provider2, MCP3008Pin.CH1, "MyAnalogInput-CH9"));
        inputs.add(gpio.provisionAnalogInputPin(provider2, MCP3008Pin.CH2, "MyAnalogInput-CH10"));
        inputs.add(gpio.provisionAnalogInputPin(provider2, MCP3008Pin.CH3, "MyAnalogInput-CH11"));
        inputs.add(gpio.provisionAnalogInputPin(provider2, MCP3008Pin.CH4, "MyAnalogInput-CH12"));
        inputs.add(gpio.provisionAnalogInputPin(provider2, MCP3008Pin.CH5, "MyAnalogInput-CH13"));
        inputs.add(gpio.provisionAnalogInputPin(provider2, MCP3008Pin.CH6, "MyAnalogInput-CH14"));
        inputs.add(gpio.provisionAnalogInputPin(provider2, MCP3008Pin.CH7, "MyAnalogInput-CH15"));
    }

    private String[] makeHeaders() {
        String[] ret = new String[16];
        for(int i = 0; i < 16; i++) {
            ret[i] = "ADC " + i;
        }
        return ret;
    }

    private void getRoundedValues() {
        for(int i = 0; i < 16; i++) {
            values[i] = inputs.get(i).getValue() * (3.3/1024) * 2;
        }
    }

    public String[] getData() {
        getRoundedValues();
        String[] ret = new String[16];
        for(int i = 0; i < 16; i++) {
            ret[i] = String.format("%.3f", values[i]);
        }
        return ret;
    }
}
