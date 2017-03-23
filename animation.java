import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tutorial extends JPanel implements ActionListener{

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(30,30,40,40);
	}
}
