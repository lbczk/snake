import java.awt.Graphics;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tutorial extends JPanel implements ActionListener{

	Timer tm = new Timer(50, this);

	int[] t = {30,10};

	int x=150, y=250;

	Serpent s = new Serpent(t);
	Config c = new Config(new Etat(s, x, y));


	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(x,y, 8,8);
		g.setColor(Color.BLUE);
		afficherSerpent(g);
		tm.start();
	}

	public void afficherSerpent(Graphics g){
		Cellule aux = s.getPremier();
		while(aux != null)
		{			
			g.fillRect(aux.getPosition()[0], aux.getPosition()[1], 8, 8);
			aux = aux.getSuivant();
		}
	}

	public void actionPerformed(ActionEvent e){
		int[] vel = c.find_vel();
		int[] pos = s.getPremier().getPosition();
		boolean b = !s.bitesItself();
		if(b && (pos[0] == x && pos[1] == y)){
			s.grow(vel);
			x = 10 + (int) (Math.random() * 40)*10;
			y = 30 + (int) (Math.random() * 40)*10;
			c = new Config(new Etat(s, x, y));
			System.out.println(x + " " + y);
		}
		else if (b){
			s.move(vel);
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
