package legendofakira;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.Timer;

import java.awt.Container;

public class GameFrame extends JFrame implements ActionListener{
	
	JLabel label;
	JTextPane textPane;
	JButton start_btn;
	
	ImageIcon img[] = new ImageIcon[2];
    Timer time;
    Timer find;
    int currFrame;
	public GameFrame() {
		// TODO Auto-generated constructor stub
		img[0]= new ImageIcon("src/legendofakira/img/story1.png");
		img[1]= new ImageIcon("src/legendofakira/img/story2.png");
		 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("アキラの伝説");
		setBounds(100,100,1000,800);
		setResizable(false); //フレームの大きさは変えられない
		setLayout(new FlowLayout());

	    JPanel p = new JPanel();
	    p.setPreferredSize(new Dimension(1000, 750));
	    p.setLayout(new BorderLayout());
	    
	    ImageIcon icon = new ImageIcon("src/legendofakira/img/start.png");
	    label = new JLabel(icon);//ここで画像を設定しています。(ImageIconに画像のパスを渡します。)
	    label.setBounds(10, 10, icon.getIconWidth(), icon.getIconHeight());
	    p.add(label, BorderLayout.NORTH);
	    
	    textPane = new JTextPane();
	    textPane.setText("スタート ボタン を おして ください．");
	    textPane.setEditable(false);//ここでJTextPaneを編集できないように設定しています。
	    textPane.setBounds(10, 10, 310, 240);
	    textPane.setFont(new Font("Arial", Font.PLAIN, 20));
	    p.add(textPane, BorderLayout.CENTER);
	    
	    start_btn = new JButton("START");
	    p.add(start_btn, BorderLayout.SOUTH);

	    Container contentPane = getContentPane();
	    contentPane.add(p);
	    currFrame = 0;
        time = new Timer(500, this);
        find = new Timer(350, this);
        
	}
	
	public void setEnding() {
		img[0]= new ImageIcon("src/legendofakira/img/ending.png");
		label.setIcon(img[0]);
	}
	
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		if (source == time){
			if (isShowing()){
				currFrame = (++currFrame) % 2;	
				label.setIcon(img[currFrame]);//ここで画像を設定しています。(ImageIconに画像のパスを渡します。)
	            repaint();
	        }
	    	}else if (source == find) {
	    		if (isShowing()){
					currFrame = (++currFrame) % 2;	
					label.setIcon(img[currFrame]);//ここで画像を設定しています。(ImageIconに画像のパスを渡します。)
		            repaint();
		        }
	    	}
	}
	
	

}
