package legendofakira;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Game {
	GameFrame frame;
	int state;
	public static final int STATE0 = 0;
	public static final int STATE1 = 1;
	public static final int STATE2 = 2;
	public static final int STATE3 = 3;
	public static final int STATE4 = 4;
	public static final int STATE5 = 5;
	public static final int STATE6 = 6;
	public static final int STATE7 = 7;
	
	public Game(GameFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
		state = Game.STATE0;
	}

	public void start() {
		frame.img[0]= new ImageIcon("src/legendofakira/img/story1.png");
		frame.img[1]= new ImageIcon("src/legendofakira/img/story2.png");
		frame.time.start();
		frame.textPane.setText("アツシ は オムツ を さがして いる");
		state = Game.STATE1;
		
	}
	
	public void setScene(ImageIcon img[]){
		frame.img = img;
	}
}
