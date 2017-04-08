import java.util.*;
import java.awt.Point;

public class Serpent implements Creature
// implemente le serpent en utilisant des listes doublement chainees
{
	private Cellule premier;
	private Cellule dernier;

	public Serpent(Point x){
		premier = new Cellule(x);
		dernier = premier;
	}
	public Serpent(Point p, int j){
		premier = new Cellule(p);
		dernier = premier;
		for(int i=0;i<j;i++)
		{
			this.grandit(Creature.down);
		}
	}

	public Serpent(){
	}

	public Serpent copie(){
		Serpent s = new Serpent();
		Cellule[] cop = premier.copie();
		s.premier = cop[0];
		s.dernier = cop[1];
		return s;
	}

	public void grandit(int[] vel){
		Point head_pos = premier.getPosition();
		Point new_pos = new Point(head_pos.x + vel[0], head_pos.y + vel[1]);
		Cellule a = new Cellule(new_pos, null, premier);
		premier.setPrecedent(a);
		premier = a;

	}

	public void bouge(int[] vel){
		grandit(vel);
		dernier = dernier.getPrecedent();
		dernier.setSuivant(null);
	}

	public void afficher(){
		premier.afficher();
	}

	public ArrayList<Point> pourAfficher()
	{
		ArrayList<Point> res = new ArrayList<Point>();
		premier.pourAfficher(res);
		return res;
	}

	public Cellule getPremier(){
		return premier;
	}


	public Point getPosition(){
		return premier.getPosition();
	}

	public int distance(int x, int y)
	{
		int xx = this.getPremier().getPosition().x;
		int yy = this.getPremier().getPosition().y;
		return Math.abs(xx - x) + Math.abs(yy - y);

	}

	public boolean seMord(){
		return premier.appartient(premier.getPosition());
	}

	public boolean seCogne(ArrayList<Point> t)
	{
		return t.contains(premier.getPosition());
	}

}