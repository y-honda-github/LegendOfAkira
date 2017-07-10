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
		/*
		 * try {
		 * BgmEngine.load("src/legendofakira/bgm.mid");
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		BgmEngine.play(0);
		*/
		SoundTestWav bgm = new SoundTestWav("bgm.wav");
		SoundTestWav find = new SoundTestWav("find.wab");
		GameFrame frame = new GameFrame();
		Game game = new Game(frame);
		frame.start_btn.addActionListener(new TimeAttackListener(game));
		frame.setVisible(true);
		while(game.state == Game.STATE0) {
			System.out.print("");
		}
		game.start();
		bgm.loop();
		
		try
		{
			CommPortIdentifier portID = CommPortIdentifier.getPortIdentifier("/dev/tty.usbserial-A8005A4S");
			SerialPort port = (SerialPort)portID.open("Sample", 5000); //waiting5000ms
		       
			port.setSerialPortParams(19200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
		    
			InputStream is = port.getInputStream();
			
			int c;
			System.out.println("okok");
			while((c = is.read()) != -1){
				System.out.print((char)c);
				ImageIcon img[] = new ImageIcon[2];
				if(game.state == Game.STATE1) {
					//オムツ見つけた
					bgm.stop();
					frame.img[0]= new ImageIcon("src/legendofakira/img/story3.png");
					frame.img[1]= new ImageIcon("src/legendofakira/img/story4.png");
					bgm.loop();
				}else if(game.state == Game.STATE2) {
					//おしゃぶり見つけた
					bgm.stop();
					frame.img[0]= new ImageIcon("src/legendofakira/img/story5.png");
					frame.img[1]= new ImageIcon("src/legendofakira/img/story6.png");
					bgm.loop();
				}else if(game.state == Game.STATE3) {
					//哺乳瓶を見つけた
					bgm.stop();
					frame.img[0]= new ImageIcon("src/legendofakira/img/story5.png");
					frame.img[1]= new ImageIcon("src/legendofakira/img/story6.png");
					
				}else if(game.state == Game.STATE4) {
					frame.img[0]= new ImageIcon("src/legendofakira/img/story5.png");
					frame.img[1]= new ImageIcon("src/legendofakira/img/story6.png");
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
