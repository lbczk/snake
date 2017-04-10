/**
 * Jeu de Snake/Hydre inspiré de la version de Jaryt Bustard, 
 * disponible sur 
 * https://github.com/Jaryt/SnakeTutorial
 */

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.Font;

import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Game extends JPanel implements ActionListener, KeyListener 
{

	Timer tm = new Timer(80, this);

	Point t = new Point(100,20);

	Point target = new Point(150,200);
	
	int tailleBoite=600, tailleCarre=8;
	int niveau=0;

	ArrayList<Point> targets = new ArrayList<Point>();
	ArrayList<Point> murs = new ArrayList<Point>();
	ArrayList<ColorPoint> aux = new ArrayList<ColorPoint>();

	Creature s;

	int score=0, cc=0;
	
	int[] direction = Creature.down;

	String[] args;

	String[] maps = {"empty.data", "ip22.data", "lab.data", "sierp.data"};

	public static final Color BACKGROUND = new Color(251, 242, 158);
	public static final Color CHERRY = new Color(100, 20, 158);
	public static final Color MURS = new Color(1, 11, 1);
 	Font scoreFont = new Font ("Courier New", 1, 19);
	boolean started, over, paused, seMord, keyP;

	public Game(String[] args) throws IOException 
	{
		JFrame jf = new JFrame();
		jf.setTitle("Snake 1.0");
		jf.setVisible(true);
		jf.setSize(tailleBoite,tailleBoite+21);
		jf.setResizable(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(this);
		jf.addKeyListener(this);
		this.args = args;
		if(args.length > 1){niveau=Integer.parseInt(args[1]);}
		startGame(niveau);
	}

	public void startGame(int niveau)  throws  IOException 
	{
		over = false;
		paused = true;
		started=false;
		score = 0;
		direction = Creature.down;
		recupererEnv(maps[niveau]);
		if(args.length>0  && args[0].equals("h")){
			s = new Hydre(t);
		}
		else{s=new Serpent(t);}
		targets.clear();
		nouvelle_fraise(100);
		tm.start();
	}

	public void paintComponent(Graphics g)  
	{
		super.paintComponent(g);
		tm.start();
		g.setColor(BACKGROUND);
		g.fillRect(0, 0, tailleBoite, tailleBoite);
		afficherEnv(g, targets, CHERRY);
		afficherEnv(g, murs, MURS);
		g.setColor(Color.BLUE);
		if(started){afficherCreature(g);}

		// To print the score
		String string = "Score:"+ score;
		g.setColor(BACKGROUND);
		g.fillRect(470, 570, 100, 19);
		g.setColor(Color.RED);
		g.setFont(scoreFont);
		g.drawString(string, 471, 585);

	}

	public void afficherCreature(Graphics g)
	{
		aux = s.pourAfficher();
		for(int i=0;i<aux.size(); i++)
		{			
			g.setColor(aux.get(i).getColor());
			g.fillRect(aux.get(i).x, aux.get(i).y, tailleCarre, tailleCarre);
		}
		g.setColor(Color.MAGENTA);
		g.fillRect(s.getPosition().x, s.getPosition().y, tailleCarre, tailleCarre);
	}

	public void afficherEnv(Graphics g, ArrayList<Point> t, Color c)
	{
		g.setColor(c);	
		for(Point p : t)
		{		
			g.fillRect(p.x, p.y, tailleCarre, tailleCarre);
		}
	}


	public void recupererEnv(String mapname)
	{
		// lit le fichier mapname dans maps et ajoute les points correspondants aux murs
		murs.clear();
		try {

			String s;
			BufferedReader br = new BufferedReader(new FileReader("maps/"+ mapname));

			while ((s = br.readLine()) != null) {
				murs.add(new Point(Integer.valueOf(s.split(",")[0]), Integer.valueOf(s.split(",")[1])));
			}
			br.close();

		} catch (IOException e) {e.printStackTrace();}

	}

	public void actionPerformed(ActionEvent e){
		keyP=false;
		started=true;
		if(!paused)
		{
		Point pos = s.getPosition();
		seMord = s.seMord();
		if(seMord || s.seCogne(murs))over=true;
		if (!over && !paused)
		{
		if(!seMord && (targets.contains(pos))){
			score +=1;
			cc++;
			if(cc % 1 == 0){s.grandit(direction);}
			else{s.bouge(direction);}
			targets.remove(pos);
			if(targets.size()<30){
			nouvelle_fraise();
			}
		}
		else if (!seMord){
			s.bouge(direction);
		}
		if(score >50){
			try{niveau = (niveau + 1)% maps.length;
				startGame((niveau));} catch(IOException ee){}}
		}
		}
		repaint();
	}

	public void nouvelle_fraise()
	{
		// ajoute une fraise a un endroit aleatoire sur la carte, en dehors des murs/obstacles
		boolean b = true;
		Point p = new Point(0,0);
		while(b)
		{
			int x = (int) (Math.random() * tailleBoite/10) *10;
			int y = (int) (Math.random() * tailleBoite/10) *10;
			p = new Point(x,y);
			b = murs.contains(p);
		}
		targets.add(p);
	}

	public void nouvelle_fraise(int n)
	{
		for(int i=0;i<n;i++){nouvelle_fraise();}
	}

	@Override
	public void keyReleased(KeyEvent e){}

	@Override
	public void keyTyped(KeyEvent e){}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int key_code = e.getKeyCode();
		if(!keyP)
		{
		
		if(key_code == KeyEvent.VK_LEFT && (paused || direction != Creature.right)){direction=Creature.left;keyP=true;}
		if(key_code == KeyEvent.VK_RIGHT && (paused || direction != Creature.left)){direction=Creature.right;keyP=true;}
		if(key_code == KeyEvent.VK_UP && (paused || direction != Creature.down)){direction=Creature.up;keyP=true;}
		if(key_code == KeyEvent.VK_DOWN && (paused || direction != Creature.up)){direction=Creature.down;keyP=true;}
		}
		if (key_code == KeyEvent.VK_SPACE)
		{
			if (over)
			{
				try{startGame(this.niveau);} catch(IOException ee){}
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
		if(paused && key_code == KeyEvent.VK_D)
		{
			System.out.println(s.debugInfo());
		}
	}

	public static void main(String[] args)  throws  IOException {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            try{
                new Game(args);
            }catch(IOException e){};
            }
        });
    }

}


