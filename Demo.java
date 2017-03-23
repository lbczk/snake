public class Demo
{
	public static void main(String[] args) {
		int[] t = {10,200};
		Serpent s = new Serpent(t);
		s.grow(t);
		s.move(t);
		s.move(t);
		s.afficher();

		// Serpent tt = s.copy();
		// tt.move(t);
		// tt.afficher();

		Etat e = new Etat(s, 30,20);
		Config c = new Config(e);
		Config.setVitesses();
		System.out.println(e.distance());
		System.out.println(c.argmin());
	}
}