import java.util.*;


public interface Creature
{
	public static final int nb_vitesses = 4;
	static final int[] vel_down = {0,10}, vel_right = {10,0};
	static final int[] vel_left = {-10,0}, vel_up={0,-10};

	public void grandit(int[] vel);
	public void bouge(int[] vel);
	public int distance(int x, int y);
	public Creature copie();
	public boolean seMord();
	public ArrayList<int[]> pourAfficher();
	public int[] getPosition();

}