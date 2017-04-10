import java.util.*;
import java.awt.Point;
import java.awt.Color;

public class Hydre implements Creature
{
	public ArrayList<CelluleB> tetes = new ArrayList<CelluleB>();
	private int tete_index=0;

	public Hydre(Point p){
		CelluleB c = new CelluleB(p);
		Point pp = new Point(p.x + Creature.left[0],p.y + Creature.left[1]);
		CelluleB d = new CelluleB(pp, c);
		tetes.add(c);
		tetes.add(d);
		for(int i=0;i<40;i++)
		{
			this.grandit(Creature.down, false);
		}
	}

	public void changeTete()
	{
		tete_index = (tete_index+1) % tetes.size();
	}

	public ArrayList<CelluleB> getTete(){return tetes;};

	public void grandit(int[] vel, boolean b)
	{
		CelluleB tete_courante = tetes.get(tete_index);
		Point tete_pos = tete_courante.getPosition();
		Point new_pos = new Point(tete_pos.x + vel[0], tete_pos.y + vel[1]);
		CelluleB nouvelle_tete = new CelluleB(new_pos, tete_courante);
		tetes.set(tete_index, nouvelle_tete);
		if(b)
		{
			if(nouvelle_tete.getVoisins().get(0).getVoisins().size()>0){
				tetes.add(nouvelle_tete.getVoisins().get(0));
			};
		}
	}

	public void grandit(int[] vel)
	{
		grandit(vel, true);
	}
	
	public void bouge(int[] vel)
	{
		grandit(vel, false);
		tetes_update();
	}

	public void tetes_update()
	{
		int j = (tete_index+1) % tetes.size();
		int h=0;
		if(Math.random()<0.2)
		{
		while((j==tete_index || tetes.get(j).getVoisins().size() != 1) && h < tetes.size())
		{
			j = (j+1) % tetes.size();
			h++;
		}
		CelluleB tetej = tetes.get(j);
		CelluleB cur = tetes.get(tete_index);
		CelluleB prec = tetej.getVoisins().get(0);
		prec.getVoisins().remove(tetej);
		while(tetes.remove(tetej)){}
		if(prec.getVoisins().size() == 1)
		{
			tetes.add(prec);
		}
		tete_index = tetes.indexOf(cur);
		}
	}

	public boolean seMord(){
		int b = tetes.get(tete_index).appartient(tetes.get(tete_index).getPosition());
		return b > 1 ? true:false;

	}

	public boolean seCogne(ArrayList<Point> t)
	{
		return t.contains(tetes.get(tete_index).getPosition());
	}

	public Point getPosition(){
		return tetes.get(tete_index).getPosition();
	}

	public ArrayList<ColorPoint> pourAfficher()
	{
		ArrayList<ColorPoint> res = new ArrayList<ColorPoint>();
		tetes.get(tete_index).pourAfficher(res);
		for(CelluleB tete:tetes)
		{
			res.add(new ColorPoint(Color.GREEN, tete.getPosition()));
		}
		return res;
	}

	public String debugInfo()
	{
		String res="Il y a "+ tetes.size() +"tetes.";
		for(int i=0; i<tetes.size(); i++){
			res+="\n ELEMENT "+  i+" a " +tetes.get(i).getVoisins().size()+ "voisins.";}
		return res;
	}

}