import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

import java.util.*;
import java.io.IOException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import decor.Decor;

public class Tutorial extends JPanel implements ActionListener, KeyListener 
{

	Timer tm = new Timer(50, this);

	Point t = new Point(30,10), tt = new Point(60,10);

	Point target = new Point(150,200);
	
	int dim=600;

	ArrayList<Point> targets = new ArrayList<Point>();
	ArrayList<Point> walls = new ArrayList<Point>();
	ArrayList<Point> aux = new ArrayList<Point>();

	Creature s;

	// Config c = new Config(new Etat(st, target.x, target.y));

	int score=0, ticks=0, cc=0;
	
	int[] direction = Creature.down;

	Decor im;

	public static final Color SEASHELL = new Color(251, 242, 158);
	public static final Color SOMECOLOR = new Color(200, 20, 158);
	public static final Color OTHERCOLOR = new Color(1, 11, 1);


	boolean over,paused;

	public Tutorial() throws IOException 
	{
		JFrame jf = new JFrame();
		jf.setTitle("Snake 1.0");
		jf.setVisible(true);
		jf.setSize(dim,dim);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(this);
		jf.addKeyListener(this);
		im = new Decor("ip2.png", 60, 60);
		targets = im.toPointArray(200,300);
		walls = im.toPointArray(0,170);
		walls.add(new Point(0,0));
		startGame();
	}

	public void startGame()  throws  IOException 
	{
		over = false;
		paused = false;
		score = 0;
		ticks = 0;
		direction = Creature.down;
		s = new Hydre(t, 20);
		tm.start();
		System.out.println("SIZE of targets : " + targets.size());
		System.out.println("SIZE of walls : " + walls.size());
	}

	public void paintComponent(Graphics g)  
	{
		super.paintComponent(g);
		g.setColor(SEASHELL);
		g.fillRect(0, 0, dim, dim);
		afficherEnv(g, targets, SOMECOLOR);
		afficherEnv(g, walls, OTHERCOLOR);
		g.setColor(Color.RED);
		g.fillRect(target.x,target.y, 8,8);
		g.setColor(Color.BLUE);
		if(ticks>0){afficherCreature(g);}
		String string = "Score: " + score;
		g.drawString(string, (int) (550 - string.length() * 2.5f), 550);
		tm.start();

	}

	public void afficherCreature(Graphics g)
	{
		aux = s.pourAfficher();
		g.setColor(Color.MAGENTA);
		g.fillRect(aux.get(0).x, aux.get(0).y, 8, 8);
		g.setColor(Color.BLUE);
		for(int i=1;i<aux.size(); i++)
		{			
			g.fillRect(aux.get(i).x, aux.get(i).y, 8, 8);
		}
	}

	public void afficherEnv(Graphics g, ArrayList<Point> t, Color c)
	{
		g.setColor(c);	
		for(int i=1;i<t.size(); i++)
		{		
			g.fillRect(t.get(i).x, t.get(i).y, 8, 8);
		}
	}

	public void actionPerformed(ActionEvent e){
		repaint();
		ticks++;
		Point pos = s.getPosition();
		boolean b = s.seMord();
		if(b || s.seCogne(walls))over = true;
		if (!over && !paused)
		{
		if(!b && (targets.contains(pos))){
			score +=1;
			cc++;
			if(cc %8 == 0){s.grandit(direction);}
			else{s.bouge(direction);}
			targets.remove(pos);			
		}
		else if (!b){
			s.bouge(direction);
		}
		}

		// int[] direction2 = c.prochaineVitesse();
		// pos = st.getPosition();
		// if(b && (pos.x == x && pos.y == y)){
		// 	score +=10;
		// 	st.grandit(direction2);
		// 	x = 10 + (int) (Math.random() * 40)*10;
		// 	y = 50 + (int) (Math.random() * 40)*10;
		// 	c = new Config(new Etat(st, x, y));
		// 	System.out.println(x + " " + y);
		// }
		// else if (b){
		// 	st.bouge(direction2);
		// }

	}

	public static void main(String[] args)  throws  IOException {
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
		int key_code = e.getKeyCode();
		if(key_code == KeyEvent.VK_LEFT && direction != Creature.right){direction=Creature.left;}
		if(key_code == KeyEvent.VK_RIGHT && direction != Creature.left){direction=Creature.right;}
		if(key_code == KeyEvent.VK_UP && direction != Creature.down){direction=Creature.up;}
		if(key_code == KeyEvent.VK_DOWN && direction != Creature.up){direction=Creature.down;}

		if (key_code == KeyEvent.VK_SPACE)
		{
			if (over)
			{
				try{startGame();} catch(IOException ee){}
			}
			else
			{
				paused = !paused;
			}
		}
	}
}


