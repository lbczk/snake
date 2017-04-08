import java.util.*;
import java.awt.Point;

public class CelluleH
{
	private CelluleH precedent;
	private CelluleH suivantG;
	private CelluleH suivantD;
	private Point position;
	boolean bifurcation;
	boolean mark;

	public CelluleH(Point position){
		this(position, null, null);
	}

	public CelluleH(Point position, CelluleH precedent, CelluleH suivantG){
		this.position = position;
		this.precedent = precedent;
		this.suivantG = suivantG;
		this.suivantD = null;
		this.bifurcation = false;
		this.mark = false;
	}


	public Point getPosition(){
		return position;
	}

	public CelluleH getPrecedent(){
		return precedent;
	}

	public void setPrecedent(CelluleH precedent){
		this.precedent = precedent;
	}

	public void setSuivantG(CelluleH suivantG){
		this.suivantG = suivantG;
	}

	public void pourAfficher(ArrayList<Point> res)
	{
		res.add(position);
		this.mark = true;
		if(suivantG != null && suivantG.mark == false)
		{
			suivantG.pourAfficher(res);
		}
		if(suivantD != null && suivantD.mark == false)
		{
			suivantD.pourAfficher(res);
		}
		if(precedent != null && precedent.mark == false)
		{
			precedent.pourAfficher(res);
		}
		this.mark = false;
	}

	public boolean appartient(Point p){
		if(suivantG != null && (suivantG.position.equals(p) || suivantG.appartient(p))){
			return true;
		}
		if(suivantD != null && (suivantD.position.equals(p) || suivantD.appartient(p))){
			return true;
		}
		return false;
	}

}