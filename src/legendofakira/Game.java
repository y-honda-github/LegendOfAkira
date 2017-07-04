package legendofakira;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Game {
	GameFrame frame;
	public Game(GameFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
		
	}

	public void start() {
		frame.textArea.setText("aiuoe");
	}
	
	public void change() {
		 ImageIcon icon = new ImageIcon("src/legendofakira/img/story2.png");
		 frame.label = new JLabel(icon);//ここで画像を設定しています。(ImageIconに画像のパスを渡します。)
		 
	}
}
