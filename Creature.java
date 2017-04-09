import java.util.*;
import java.awt.Point;

public interface Creature
{
	public static final int nb_vitesses = 4;
	static final int[] down={0,10}, right = {10,0};
	static final int[] left={-10,0}, up={0,-10};

	public void grandit(int[] vel);
	public void bouge(int[] vel);
	public ArrayList<ColorPoint> pourAfficher();
	public Point getPosition();
	public boolean seMord();
	public boolean seCogne(ArrayList<Point> t);
	public void changeTete();
	public String debugInfo();

}