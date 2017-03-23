import java.util.*;

public class Config{
	Etat present;
	Config[] suivants = new Config[4];

	static ArrayList<int[]> vitesses = new ArrayList<int[]>();

	static void setVitesses(){
		vitesses.add(Serpent.vel_down);
		vitesses.add(Serpent.vel_left);
		vitesses.add(Serpent.vel_right);
		vitesses.add(Serpent.vel_up);
	}

	public Config(Etat present){
		this.present = present;
		Config.setVitesses();
		this.ajout();
		this.ajout();
		this.ajout();
	}

	public Config(Etat present, int[] v){
		this.present = new Etat(present, v);
	}

	public void ajout(){
		if(suivants == null)
		{
			System.out.print("blabla");
		}

		for(int i=0; i < 4;i++){
			if(suivants[i] == null){
				int[] v = Config.vitesses.get(i);
				suivants[i] = new Config(present, v);
				if(suivants[i].present.getSeMord())
				{
					suivants[i] = null;
				}
			}
			else if(!suivants[i].present.getSeMord()){
				suivants[i].ajout();
			}
		}
	
	}

	public int argmin()
	{
		if(present.getSeMord())
		{
			return 1000;
		}
		int res =  present.getDistance();
		if(res == 0 && !present.getSeMord())
		{
			return 0;
		}
		int aux;
		for(int j=0; j<4; j++)
		{
			if(suivants[j] != null && !suivants[j].present.getSeMord() && (aux = suivants[j].argmin()) < res)
			{
				res = aux;
				if(aux == 0)
				{
					return 0;
				}
			}
		}
		return res;
	}

	public int[] find_vel()
	{
		int i=0;
		int d = 1000;
		for(int j=0; j<4;j++)
		{
			if(suivants[j] != null)
			{
			int dd = suivants[j].argmin();
			if(dd < d && !suivants[j].present.getSeMord())
			{
				d = dd;
				i=j;
			}
			}
		}
		if(suivants[i] != null)
		{
		present = suivants[i].present;
		suivants = suivants[i].suivants;
		if(d>0)
		{
		this.ajout();
		}
		}

		if(d==1000)
		{
			System.out.print(" --- ");
			for(int j=0;j<4;j++)
			{
				int [] vel = vitesses.get(j);
				Etat u = new Etat(present, vel);
				if(!u.getSeMord())
				{
					System.out.print("ha");
					return vel;
				}
			}
		}
		return vitesses.get(i);
	}

}