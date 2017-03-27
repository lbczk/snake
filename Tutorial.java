import java.awt.Graphics;
import java.awt.Color;
import java.util.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tutorial extends JPanel implements ActionListener, KeyListener
{

	Timer tm = new Timer(50, this);

	int[] t = {30,10};

	int x=150, y=250;

	ArrayList<int[]> targets = new ArrayList<int[]>();

	Creature s = new Serpent(t, 30);

	Config c = new Config(new Etat(s, x, y));

	int score=0, ticks=0;


	public Tutorial()
	{
		JFrame jf = new JFrame();
		jf.setTitle("Snake 1.0");
		jf.setVisible(true);
		jf.setSize(500,500);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(this);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(x,y, 8,8);
		g.setColor(Color.BLUE);
		afficherCreature(g);
		String string = "Score: " + score +", Time: " + ticks++ ;
		g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), 10);
		tm.start();

	}

	public void afficherCreature(Graphics g){
		ArrayList<int[]> aux = s.pourAfficher();
		g.setColor(Color.MAGENTA);
		g.fillRect(aux.get(0)[0], aux.get(0)[1], 8, 8);
		g.setColor(Color.BLUE);
		for(int i=1;i<aux.size(); i++)
		{			
			g.fillRect(aux.get(i)[0], aux.get(i)[1], 8, 8);
		}
	}

	public void actionPerformed(ActionEvent e){
		ticks++;
		int[] vitesse = c.prochaineVitesse();
		int[] pos = s.getPosition();
		boolean b = !s.seMord();
		if(b && (pos[0] == x && pos[1] == y)){
			score +=10;
			s.grandit(vitesse);
			x = 10 + (int) (Math.random() * 40)*10;
			y = 50 + (int) (Math.random() * 40)*10;
			c = new Config(new Etat(s, x, y));
			System.out.println(x + " " + y);
		}
		else if (b){
			s.bouge(vitesse);
		}
		repaint();
	}

	public static void main(String[] args) {
		new Tutorial();
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
	}
}


