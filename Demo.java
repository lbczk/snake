import java.awt.Point;

public class Demo

{
	public static void main(String[] args) {
		Point t = new Point(10,200);
		Serpent s = new Serpent(t);
		s.grandit(Creature.down);
		s.bouge(Creature.down);
		s.bouge(Creature.down);
		s.afficher();

		Hydre h = new Hydre(t, 12);

		h.grandit(Creature.down);
		System.out.print(h.seMord());
		System.out.println(h.tetes.size());
		System.out.println(h.tetes.get(0).getPosition().x + " - "+ h.tetes.get(0).getPosition().y);
		System.out.println(h.tetes.get(1).getPosition().x + " - "+ h.tetes.get(1).getPosition().y);

		// Serpent tt = s.copy();
		// tt.move(t);
		// tt.afficher();

		// Etat e = new Etat(s, 30,20);
		// Config c = new Config(e);
		// Config.setVitesses();
		// System.out.println(e.distance());
		// System.out.println(c.argmin());
	}
}