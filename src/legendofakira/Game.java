package legendofakira;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Game {
	GameFrame frame;
	int state;
	public static final int STAGE0 = 0;
	public static final int STAGE1 = 1;
	public static final int STAGE2 = 2;
	public static final int STAGE3 = 3;
	public Game(GameFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
		state = 0;
	}

	public void start() {
		frame.img[0]= new ImageIcon("src/legendofakira/img/story1.png");
		frame.img[1]= new ImageIcon("src/legendofakira/img/story2.png");
		frame.time.start();
		
	}
	
	public void setScene(ImageIcon img[]){
		frame.img = img;
	}
}
