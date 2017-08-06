package legendofakira;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import javazoom.jlgui.basicplayer.BasicPlayerException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) throws IOException, BasicPlayerException {
		
		GameFrame frame = new GameFrame();
		Game game = new Game(frame);
		frame.start_btn.addActionListener(new TimeAttackListener(game));
		frame.setVisible(true);
		game.opening.loop();
		
		//while(game.state == Game.STATE0) {
		//	System.out.print("");
		//}
		
		try
		{
			CommPortIdentifier portID = CommPortIdentifier.getPortIdentifier("/dev/tty.usbserial-A8005A4S");
			SerialPort port = (SerialPort)portID.open("Sample", 5000); //waiting5000ms
		       
			port.setSerialPortParams(19200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
		    
			InputStream is = port.getInputStream();
			
			int c;
			
			while((c = is.read()) != -1){
			//while(true) {
				System.out.print((char)c);
				//try{
				//	Thread.sleep(10000); //3000ミリ秒Sleepする
				//	}catch(InterruptedException e){}
				if((char)c == '^') {
					ImageIcon img[] = new ImageIcon[2];
					if(game.state == Game.STATE1) {
						//オムツ見つけた
						game.bgm.stop();
						game.findOmutsu();
						game.bgm.loop();
					}else if(game.state == Game.STATE2) {
						//おしゃぶり見つけた
						game.bgm.stop();
						game.findOsyaburi();
						game.bgm.loop();
					}else if(game.state == Game.STATE3) {
						//哺乳瓶を見つけた
						game.bgm.stop();
						//long end = System.currentTimeMillis();
						game.findHonyubin();
						frame.textPane.setText("タイムは" + ((game.end - game.start) / 1000) + "秒でした！！\n"+frame.textPane.getText());
						System.out.println("実行時間：" + ((game.end - game.start) / 1000) + "秒");
						
					}else if(game.state == Game.STATE4) {
						break;
						
					}else {
						break;
					}
				}
			}
			System.out.println("okok");
			
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

	private static BgmEngine BgmEngine(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
