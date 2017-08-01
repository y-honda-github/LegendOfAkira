package legendofakira;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Game {
	GameFrame frame;
	SoundTestWav opening = new SoundTestWav("opening.wav");
	SoundTestWav bgm = new SoundTestWav("bgm.wav");
	long start;
	long end;
	int state;
	public int flg = 0;
	public static final int STATE0 = 0;
	public static final int STATE1 = 1;
	public static final int STATE2 = 2;
	public static final int STATE3 = 3;
	public static final int STATE4 = 4;
	public static final int STATE5 = 5;
	public static final int STATE6 = 6;
	public static final int STATE7 = 7;
	SoundTestWav find;
	
	public Game(GameFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
		state = Game.STATE0;
		this.find = new SoundTestWav("find.wav");
	}

	public void start() {
		frame.img[0]= new ImageIcon("src/legendofakira/img/story1.png");
		frame.img[1]= new ImageIcon("src/legendofakira/img/story2.png");
		frame.time.start();
		frame.textPane.setText("ジグビ は オムツ を さがして いる");
		state = Game.STATE1;
		start = System.currentTimeMillis();
	}
	
	public void findOmutsu() {
		frame.time.stop();
		find.loop();
		frame.img[0]= new ImageIcon("src/legendofakira/img/omutsu1.png");
		frame.img[1]= new ImageIcon("src/legendofakira/img/omutsu2.png");
		frame.find.start();
		frame.textPane.setText("ジグビ は オムツ を みつけた !!\n"+frame.textPane.getText());
		try{
			Thread.sleep(4000); //3000ミリ秒Sleepする
			}catch(InterruptedException e){}
		find.stop();
		state = Game.STATE2;
		frame.find.stop();
		frame.img[0]= new ImageIcon("src/legendofakira/img/story3.png");
		frame.img[1]= new ImageIcon("src/legendofakira/img/story4.png");
		frame.time.start();
		frame.textPane.setText("ジグビ は おしゃぶり を さがして いる\n"+frame.textPane.getText());
	}
	
	public void findOsyaburi() {
		frame.time.stop();
		find.loop();
		frame.img[0]= new ImageIcon("src/legendofakira/img/osyaburi1.png");
		frame.img[1]= new ImageIcon("src/legendofakira/img/osyaburi2.png");
		frame.find.start();
		frame.textPane.setText("ジグビ は おしゃぶり を みつけた !!\n"+frame.textPane.getText());
		try{
			Thread.sleep(4000); //3000ミリ秒Sleepする
			}catch(InterruptedException e){}
		find.stop();
		state = Game.STATE3;
		frame.find.stop();
		frame.img[0]= new ImageIcon("src/legendofakira/img/story5.png");
		frame.img[1]= new ImageIcon("src/legendofakira/img/story6.png");
		frame.time.start();
		frame.textPane.setText("ジグビ は ほにゅうびん を さがして いる\n"+frame.textPane.getText());
	}
	
	public void findHonyubin() {
		end = System.currentTimeMillis();
		frame.time.stop();
		find.loop();
		frame.img[0]= new ImageIcon("src/legendofakira/img/honyubin1.png");
		frame.img[1]= new ImageIcon("src/legendofakira/img/honyubin2.png");
		frame.find.start();
		frame.textPane.setText("ジグビ は ほにゅうびん を みつけた !!\n"+frame.textPane.getText());
		try{
			Thread.sleep(4000); //3000ミリ秒Sleepする
			}catch(InterruptedException e){}
		find.stop();
		state = Game.STATE4;
		frame.find.stop();
		frame.setEnding();
		frame.textPane.setText("ジグビ の ぼうけん は つづく...\n"+frame.textPane.getText());
	}
	
	public void setScene(ImageIcon img[]){
		frame.img = img;
	}
}
