import java.util.*;
import java.awt.Point;
import java.awt.Color;

public class Cellule
{
	private Cellule precedent;
	private Cellule suivant;
	private Point position;

	public Cellule(Point position){
		this(position, null, null);
	}

	public Cellule(Point position, Cellule precedent, Cellule suivant){
		this.position = position;
		this.precedent = precedent;
		this.suivant = suivant;
	}

	public Cellule getSuivant(){
		return suivant;
	}

	public Point getPosition(){
		return position;
	}

	public Cellule getPrecedent(){
		return precedent;
	}

	public void setSuivant(Cellule d){
		suivant = d;
	}

	public void setPrecedent(Cellule d){
		precedent = d;
	}

	public void afficher(){
		System.out.println(position.x + "," + position.y);
		if(suivant != null){suivant.afficher();}
	}

	public boolean appartient(Point p){
		if(suivant != null && suivant.position.equals(p)){
			return true;
		}
		if(suivant != null){
			return suivant.appartient(p);
		}
		return false;
	}

	public Cellule[] copie(){
		Cellule aux=this;
		Cellule init= new Cellule(position);
		Cellule last = init;
		while(aux.getSuivant() != null)
		{
			aux = aux.getSuivant();
			last.setSuivant(new Cellule(aux.position, last, null));
			last = last.getSuivant();
		}
		Cellule[] res = {init, last};
		return res;
	}

	public void pourAfficher(ArrayList<ColorPoint> res)
	{
		res.add(new ColorPoint(Color.RED, position));
		if(suivant != null)
		{
			suivant.pourAfficher(res);
		}
	}
}