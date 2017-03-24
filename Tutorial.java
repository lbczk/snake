import java.awt.Graphics;
import java.awt.Color;
import java.util.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tutorial extends JPanel implements ActionListener{

	Timer tm = new Timer(50, this);

	int[] t = {30,10};

	int x=150, y=250;

	Creature s = new Serpent(t, 30);
	Config c = new Config(new Etat(s, x, y));


	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(x,y, 8,8);
		g.setColor(Color.BLUE);
		afficherCreature(g);
		tm.start();

	}

	public void afficherCreature(Graphics g){
		ArrayList<int[]> aux = s.pourAfficher();
		for(int i=0;i<aux.size(); i++)
		{			
			g.fillRect(aux.get(i)[0], aux.get(i)[1], 8, 8);
		}
	}

	public void actionPerformed(ActionEvent e){
		int[] vel = c.find_vel();
		int[] pos = s.getPosition();
		boolean b = !s.seMord();
		if(b && (pos[0] == x && pos[1] == y)){
			s.grandit(vel);
			x = 10 + (int) (Math.random() * 40)*10;
			y = 30 + (int) (Math.random() * 40)*10;
			c = new Config(new Etat(s, x, y));
			System.out.println(x + " " + y);
		}
		else if (b){
			s.bouge(vel);
		}
		repaint();
	}

	public static void main(String[] args) {
		Tutorial t = new Tutorial();
		JFrame jf = new JFrame();
		jf.setTitle("Tutorial");
		jf.setSize(600,400);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(t);
	}
}
