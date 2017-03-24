import java.util.*;

public class Config{
	Etat present;
	Config[] suivants = new Config[4];

	static ArrayList<int[]> vitesses = new ArrayList<int[]>();
	final int PREV_PROF = 6;

	static void setVitesses(){
		vitesses.add(Creature.vel_down);
		vitesses.add(Creature.vel_left);
		vitesses.add(Creature.vel_right);
		vitesses.add(Creature.vel_up);
	}

	public Config(Etat present){
		this.present = present;
		Config.setVitesses();
		for(int i=0;i<PREV_PROF;i++)
		{
			this.ajout();
		}
	}

	public Config(Etat present, int[] v){
		this.present = new Etat(present, v);
	}

	public void ajout(){
		for(int i=0; i <  Creature.nb_vitesses; i++){
			if(suivants[i] == null){
				int[] v = Config.vitesses.get(i);
				suivants[i] = new Config(present, v);
			}
			else if(!suivants[i].present.getSeMord()){
				if(present.getDistance() == 0)
				{
					suivants[i].present.setDistance(0);
				}
				suivants[i].ajout();
			}
		}
	
	}

	public void remove_suivants(int j)
	{
		for(int i=0; i< Creature.nb_vitesses; i++)
		{
			if(i != j && suivants[i] != null){suivants[i].present.setSeMord(true);}
		}
	}

	public int argmin(int k)
	{
		if(present.getSeMord())
		{
			return 1000;
		}

		int res = present.getDistance();
		if(k==0) return res;

		int aux;
		for(int j=0; j < Creature.nb_vitesses; j++)
		{
			if(suivants[j] != null && !suivants[j].present.getSeMord() && (aux = suivants[j].argmin(k-1)) < res)
			{
				res = aux;
				if(aux == 0)
				{
					this.remove_suivants(j);
					return 0;
				}
			}
		}
		return res;
	}

	public int[] find_vel()
	{
		int i=0;
		int d=1000;
		for(int j=0; j < Creature.nb_vitesses;j++)
		{
			if(suivants[j] != null)
			{
			int dd = suivants[j].argmin(PREV_PROF);
			if(dd < d && !suivants[j].present.getSeMord())
			{
				d = dd;
				i=j;
			}
			}
		}
		present = suivants[i].present;
		suivants = suivants[i].suivants;
		this.ajout();

		return vitesses.get(i);
	}

}