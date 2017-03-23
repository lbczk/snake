public class Etat{
	private Serpent s;
	private int x;
	private int y;
	private int dist;
	private boolean seMord;

	public Etat(Serpent s, int x, int y){
		this.s = s;
		this.x = x;
		this.y = y;
		this.seMord = s.bitesItself();
		this.dist = this.distance();
	}

	public boolean getSeMord(){
		return this.seMord;
	}

	public Etat(Etat present, int[] v){
		this.x = present.x;
		this.y = present.y;
		s = present.s.copy();
		s.move(v);
		this.seMord = s.bitesItself();
		this.dist = this.distance();
	}

	public int getDistance()
	{
		return dist;
	}

	public int distance(){
		int xx = s.getPremier().getPosition()[0];
		int yy = s.getPremier().getPosition()[1];
		return Math.abs(xx - x) + Math.abs(yy - y);
	}
}