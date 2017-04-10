import java.util.*;
import java.awt.Point;
import java.awt.Color;

public class Serpent implements Creature
{
	private Cellule premier;
	private Cellule dernier;

	public Serpent(Point x){
		premier = new Cellule(x);
		dernier = premier;
		for(int i=0;i<40;i++)
		{
			this.grandit(Creature.down);
		}
	}

	public Serpent(){
	}

	public void changeTete(){}

	public void grandit(int[] v){
		Point head_pos = premier.getPosition();
		Point new_pos = new Point(head_pos.x + v[0], head_pos.y + v[1]);
		Cellule a = new Cellule(new_pos, null, premier);
		premier.setPrecedent(a);
		premier = a;

	}

	public void bouge(int[] v){
		grandit(v);
		dernier = dernier.getPrecedent();
		dernier.setSuivant(null);
	}

	public void afficher(){
		premier.afficher();
	}

	public ArrayList<ColorPoint> pourAfficher()
	{
		ArrayList<ColorPoint> res = new ArrayList<ColorPoint>();
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
	{// cette méthode n'est pas à faire dans le TP
		int xx = this.getPremier().getPosition().x;
		int yy = this.getPremier().getPosition().y;
		return Math.abs(xx - x) + Math.abs(yy - y);

	}

	public Serpent copie()
	{// cette méthode n'est pas à faire dans le TP
		Serpent s = new Serpent();
		Cellule[] cop = premier.copie();
		s.premier = cop[0];
		s.dernier = cop[1];
		return s;
	}

	public boolean seMord(){
		return premier.appartient(premier.getPosition());
	}

	public boolean seCogne(ArrayList<Point> murs)
	{
		return murs.contains(premier.getPosition());
	}

	public String debugInfo(){return "";}

}