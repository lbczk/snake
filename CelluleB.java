import java.util.*;
import java.awt.Point;
import java.awt.Color;

public class CelluleB
{
	private ArrayList<CelluleB> voisins = new ArrayList<CelluleB>();
	private Point position;
	boolean mark;

	public CelluleB(Point position){
		this.position = position;
		this.mark = false;
	}

	public CelluleB(Point position, ArrayList<CelluleB> voisins){
		this.position = position;
		this.voisins = voisins;
		this.mark = false;
	}

	public CelluleB(Point position, CelluleB autre)
	{
		this.position = position;
		this.voisins.add(autre);
		autre.voisins.add(this);
		this.mark = false;
	}

	public Point getPosition(){
		return position;
	}

	public ArrayList<CelluleB> getVoisins(){
		return voisins;
	}

	public void pourAfficher(ArrayList<ColorPoint> res)
	{
		res.add(new ColorPoint(Color.RED, position));
		this.mark = true;
		for(int i=0;i<voisins.size();i++)
		{
			if(voisins.get(i) != null && voisins.get(i).mark == false)
			{
				voisins.get(i).pourAfficher(res);
			}
		}
		this.mark = false;
	}

	public int appartient(Point p){
		int res=position.equals(p) ? 1:0;
		mark = true;
		for(int i=0;i<voisins.size();i++)
		{
			if(voisins.get(i) != null && voisins.get(i).mark==false)
			{
				res += voisins.get(i).appartient(p);
			}
		}
		mark = false;
		return res;
	}

	public void ajout(CelluleB c)
	{
		voisins.add(c);
	}

}