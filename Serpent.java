import java.util.*;

public class Serpent implements Creature
// implemente le serpent en utilisant des listes doublement chainees
{
	private Cellule premier;
	private Cellule dernier;



	public Serpent(int[] x){
		premier = new Cellule(x);
		dernier = premier;
	}
	public Serpent(int[] x, int j){
		premier = new Cellule(x);
		dernier = premier;
		for(int i=0;i<j;i++)
		{
			this.grandit(Creature.vel_down);
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
		int[] head_pos = premier.getPosition();
		int[] new_pos = {head_pos[0] + vel[0], head_pos[1] + vel[1]};
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

	public ArrayList<int[]> pourAfficher()
	{
		ArrayList<int[]> res = new ArrayList<int[]>();
		premier.pourAfficher(res);
		return res;
	}

	public Cellule getPremier(){
		return premier;
	}

	public int[] find_vel(int x, int y){
		int xx = premier.getPosition()[0];
		int yy = premier.getPosition()[1];

		if(xx > x && !premier.appartient(xx+vel_left[0], yy+vel_left[1])) {
			return vel_left;
		}
		if(xx<x && !premier.appartient(xx+vel_right[0], yy+vel_right[1])) {
			return vel_right;
		}
		if(yy>y && !premier.appartient(xx+vel_up[0], yy+vel_up[1])) {
			return vel_up;
		}
		
		if(yy< y && !premier.appartient(xx+vel_down[0], yy+vel_down[1])){
			return vel_down;
		}
		return vel_down;
	
	}

	public int[] getPosition(){
		return premier.getPosition();
	}

	public int distance(int x, int y)
	{
		int xx = this.getPremier().getPosition()[0];
		int yy = this.getPremier().getPosition()[1];
		return Math.abs(xx - x) + Math.abs(yy - y);

	}

	public boolean seMord(){
		int xx = premier.getPosition()[0];
		int yy = premier.getPosition()[1];
		return premier.appartient(xx, yy);
	}

}