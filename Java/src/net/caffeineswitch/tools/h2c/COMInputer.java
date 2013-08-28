package net.caffeineswitch.tools.h2c;

import java.io.IOException;
import java.io.OutputStream;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

public class COMInputer {
	private static OutputStream out = null;
	static{
		CommPortIdentifier portId = null;
		try {
			portId = CommPortIdentifier.getPortIdentifier("COM4");
		} catch (NoSuchPortException e1) {
			e1.printStackTrace();
			System.err.println("終了");
			System.exit(1);
		}
		SerialPort port = null;
		try {
			port = (SerialPort)portId.open("serial",2000);
		} catch (PortInUseException e1) {
			e1.printStackTrace();
			System.err.println("終了");
			System.exit(1);
		}
		try {
			port.setSerialPortParams(9600, // 通信速度[bps]
					SerialPort.DATABITS_8, // データビット数
					SerialPort.STOPBITS_1, // ストップビット
					SerialPort.PARITY_NONE // パリティ
			);
			port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
		} catch (UnsupportedCommOperationException e1) {
			e1.printStackTrace();
			System.err.println("終了");
			System.exit(1);
		}

		try {
			out = port.getOutputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
			System.err.println("終了");
			System.exit(1);
		}
	}
	static void input(int i){
		try {
			out.write(String.format("%03d",i).concat(";").getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
