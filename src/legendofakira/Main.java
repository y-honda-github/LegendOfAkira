package legendofakira;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		GameFrame frame = new GameFrame();
		frame.setVisible(true);
		Game game = new Game(frame);
		game.start();
		try
		{
			CommPortIdentifier portID = CommPortIdentifier.getPortIdentifier("/dev/tty.usbserial-A8005A4S");
			SerialPort port = (SerialPort)portID.open("Sample", 5000); //waiting5000ms
		       
			port.setSerialPortParams(19200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
		    
			InputStream is = port.getInputStream();
			
			int c;
			while((c = is.read()) != -1){
				System.out.print((char)c);
			}
			is.close();
			port.close();
		}catch(NoSuchPortException e){
			System.err.println("Can Not Find Device");
			e.printStackTrace();
		}catch(PortInUseException e){
			System.err.println("Can Not Open Device");
			e.printStackTrace();
		}catch(UnsupportedCommOperationException e){
			System.err.println("Invalid Parameter");
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
