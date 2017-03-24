import java.util.*;

public class Cellule
{
	private Cellule precedent;
	private Cellule suivant;
	private int[] position = new int[2];

	public Cellule(int[] position){
		this(position, null, null);
	}

	public Cellule(int[] position, Cellule precedent, Cellule suivant){
		this.position = position;
		this.precedent = precedent;
		this.suivant = suivant;
	}

	public Cellule getSuivant(){
		return suivant;
	}

	public int[] getPosition(){
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
		System.out.println(position[0] + "," + position[1]);
		if(suivant != null){suivant.afficher();}
	}

	public boolean appartient(int x, int y){
		if(suivant != null && suivant.position[0]==x && suivant.position[1]==y){
			return true;
		}
		if(suivant != null){
			return suivant.appartient(x,y);
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

	public void pourAfficher(ArrayList<int[]> res)
	{
		res.add(position);
		if(suivant != null)
		{
			suivant.pourAfficher(res);
		}
	}
}