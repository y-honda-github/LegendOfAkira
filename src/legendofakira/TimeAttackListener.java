package legendofakira;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class TimeAttackListener implements ActionListener{
	Game game;
	public TimeAttackListener(Game game) {
		this.game = game;
	}
	public void actionPerformed(ActionEvent e){
	    /* 処理したい内容をここに記述する */
		System.out.println("ok");
		//game.frame.start_btn.setEnabled(false);
		game.state = Game.STAGE1;
	  }
}
