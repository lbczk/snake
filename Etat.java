public class Etat{
	private Creature s;
	private int x;
	private int y;
	private int dist;
	private boolean seMord;

	public Etat(Creature s, int x, int y){
		this.s = s;
		this.x = x;
		this.y = y;
		this.seMord = s.seMord();
		this.dist = this.distance();
	}

	public boolean getSeMord(){
		return this.seMord;
	}

	public Etat(Etat present, int[] v){
		this.x = present.x;
		this.y = present.y;
		s = present.s.copie();
		s.bouge(v);
		this.seMord = s.seMord();
		this.dist = this.distance();
	}

	public int getDistance()
	{
		return dist;
	}

	public void setDistance(int d)
	{
		dist = d;
	}

	public void setSeMord(boolean b)
	{
		seMord = b;
	}

	public int distance(){
		return s.distance(x, y);
	}
}