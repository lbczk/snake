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
	}

	public Config(Etat present, int[] v){
		this.present = new Etat(present, v);
	}

	public void ajout(int k){
		if(suivants == null&& k>0){
			suivants = new Etat[4];
			for(int i=0; i < 4;i++){
				int[] v = Config.vitesses.get(i);
				suivants[i] = new Config(present, v);
			}
		}
		else if (k>0){
			for(int i=0; i < 4;i++){
				suivants[i].ajout(k-1);
			}
		}
	}
}