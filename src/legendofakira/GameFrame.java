package legendofakira;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import java.awt.Container;

public class GameFrame extends JFrame{
	JTextArea textArea = new JTextArea();
	JLabel label;
	public GameFrame() {
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("アキラの伝説");
		setBounds(100,100,1000,800);
		//setResizable(false);
		setLayout(new FlowLayout());

	    JPanel p = new JPanel();
	    p.setPreferredSize(new Dimension(1000, 750));
	    //p.setBackground(Color.ORANGE);
	    p.setLayout(new BorderLayout());
	    
	    ImageIcon icon = new ImageIcon("src/legendofakira/img/story1.png");
	    label = new JLabel(icon);//ここで画像を設定しています。(ImageIconに画像のパスを渡します。)
	    label.setBounds(10, 10, icon.getIconWidth(), icon.getIconHeight());
	    p.add(label, BorderLayout.NORTH);
	    
	    JTextPane textPane = new JTextPane();
	    textPane.setText("アツシ は オムツ を さがして いる．");
	    textPane.setEditable(false);//ここでJTextPaneを編集できないように設定しています。
	    textPane.setBounds(10, 10, 310, 240);
	    textPane.setFont(new Font("Arial", Font.PLAIN, 20));
	    p.add(textPane, BorderLayout.CENTER);
	    
	    JButton start_btn = new JButton("START");
	    p.add(start_btn, BorderLayout.SOUTH);

	    Container contentPane = getContentPane();
	    contentPane.add(p);
	}

}
