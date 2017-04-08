import java.util.*;
import java.awt.Point;

public class Hydre implements Creature
{
	public ArrayList<CelluleH> tetes = new ArrayList<CelluleH>();
	private int i=0;


	public Hydre(Point p){
		CelluleH c = new CelluleH(p);
		Point pp = new Point(p.x + Creature.left[0],p.y + Creature.left[1]);
		CelluleH d = new CelluleH(pp,c, null);
		tetes.add(c);
		tetes.add(d);
	}

	public Hydre(Point p, int j){
		this(p);
		for(int i=0;i<j;i++)
		{
			this.grandit(Creature.down);
		}
	}

	public void grandit(int[] vel)
	{
		CelluleH tetei = tetes.get(i);
		Point head_pos = tetei.getPosition();
		Point new_pos = new Point(head_pos.x + vel[0], head_pos.y + vel[1]);
		CelluleH a = new CelluleH(new_pos, tetei, null);
		tetei.setSuivantG(a);
		tetes.set(i, a);
	}
	
	public void bouge(int[] vel)
	{
		grandit(vel);
		int j=(i+1) % tetes.size();
		tetes.set(j, tetes.get(j).getPrecedent());
		// dernier.setSuivant(null);
	}

	public boolean seMord(){
		boolean b = tetes.get(i).appartient(tetes.get(i).getPosition());
		return b;

	}

	public boolean seCogne(ArrayList<Point> t)
	{
		return t.contains(tetes.get(i).getPosition());
	}

	public Point getPosition(){
		return tetes.get(i).getPosition();
	}
	public ArrayList<Point> pourAfficher()
	{
		ArrayList<Point> res = new ArrayList<Point>();
		tetes.get(i).pourAfficher(res);
		return res;
	}


	public int distance(int x, int y){return 0;}
	public Creature copie(){return new Hydre(new Point(0,0));}

}