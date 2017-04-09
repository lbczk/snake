import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.Font;

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
@SuppressWarnings("serial")
public class Game extends JPanel implements ActionListener, KeyListener 
{

	Timer tm = new Timer(80, this);

	Point t = new Point(100,20), tt = new Point(60,10);

	Point target = new Point(150,200);
	
	int dim=600;

	ArrayList<Point> targets = new ArrayList<Point>();
	ArrayList<Point> walls = new ArrayList<Point>();
	ArrayList<ColorPoint> aux = new ArrayList<ColorPoint>();

	Creature s;

	int score=0, ticks=0, cc=0;
	
	int[] direction = Creature.down;

	String[] args;

	Decor im;

	public static final Color SEASHELL = new Color(251, 242, 158);
	public static final Color SOMECOLOR = new Color(200, 20, 158);
	public static final Color OTHERCOLOR = new Color(1, 11, 1);
 	Font scoreFont = new Font ("Courier New", 1, 19);
	boolean over,paused;

	public Game(String[] args) throws IOException 
	{
		JFrame jf = new JFrame();
		jf.setTitle("Snake 1.0");
		jf.setVisible(true);
		jf.setSize(dim,dim+21);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(this);
		jf.addKeyListener(this);
		im = new Decor("ip22.png", 60, 60);
		walls = im.toPointArray(200,300);
		walls.addAll(im.toPointArray(0,170));
		walls.add(new Point(0,0));
		this.args = args;
		startGame();
	}

	public void startGame()  throws  IOException 
	{
		over = false;
		paused = false;
		score = 0;
		ticks = 0;
		direction = Creature.down;
		if(args.length>0  && args[0].equals("h")){
			s = new Hydre(t);
		}
		else{s=new Serpent(t);}
		targets.clear();
		nouvelle_fraise(100);
		tm.start();
		// System.out.println("SIZE of targets : " + targets.size());
		// System.out.println("SIZE of walls : " + walls.size());
	}

	public void paintComponent(Graphics g)  
	{
		super.paintComponent(g);
		g.setColor(SEASHELL);
		g.fillRect(0, 0, dim, dim);
		afficherEnv(g, targets, SOMECOLOR);
		afficherEnv(g, walls, OTHERCOLOR);
		g.setColor(Color.BLUE);
		if(ticks>0){afficherCreature(g);}
		// write score part
		String string = "Score:"+ score;
		g.setColor(SEASHELL);
		g.fillRect(470, 570, 100, 19);
		g.setColor(Color.RED);
		g.setFont(scoreFont);
		g.drawString(string, 471, 585);

		tm.start();

	}

	public void afficherCreature(Graphics g)
	{
		aux = s.pourAfficher();
		for(int i=0;i<aux.size(); i++)
		{			
			g.setColor(aux.get(i).getColor());
			g.fillRect(aux.get(i).x, aux.get(i).y, 8, 8);
		}
		g.setColor(Color.MAGENTA);
		g.fillRect(s.getPosition().x, s.getPosition().y, 8, 8);
	}

	public void afficherEnv(Graphics g, ArrayList<Point> t, Color c)
	{
		g.setColor(c);	
		for(Point p : t)
		{		
			g.fillRect(p.x, p.y, 8, 8);
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
			if(cc % 1 == 0){s.grandit(direction);}
			else{s.bouge(direction);}
			targets.remove(pos);
			if(targets.size()<30){
			nouvelle_fraise();
			}

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
		new Game(args);
	}

	public void nouvelle_fraise()
	{
		boolean b = true;
		Point p = new Point(0,0);
		while(b)
		{
			int x = (int) (Math.random() * 60) *10;
			int y = (int) (Math.random() * 60) *10;
			p = new Point(x,y);
			b = walls.contains(p);
		}
		targets.add(p);
	}

	public void nouvelle_fraise(int n)
	{
		for(int i=0;i<n;i++){nouvelle_fraise();}
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
		if(key_code == KeyEvent.VK_LEFT && (paused || direction != Creature.right)){direction=Creature.left;}
		if(key_code == KeyEvent.VK_RIGHT && (paused || direction != Creature.left)){direction=Creature.right;}
		if(key_code == KeyEvent.VK_UP && (paused || direction != Creature.down)){direction=Creature.up;}
		if(key_code == KeyEvent.VK_DOWN && (paused || direction != Creature.up)){direction=Creature.down;}

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
		if(paused && key_code == KeyEvent.VK_C)
		{
			s.changeTete();
		}
		if(paused && key_code == KeyEvent.VK_P)
		{
			System.out.println(s.debugInfo());
		}
	}
}


