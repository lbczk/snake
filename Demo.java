import java.awt.Color;
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

		ColorPoint c = new ColorPoint(Color.BLACK, 0,1);
		System.out.print(c.x);
		// Hydre h = new Hydre(t, 5);
		// System.out.println("--> " + h.tetes.size());
		// h.bouge(Creature.down);
		// h.grandit(Creature.down);
		// System.out.print(h.seMord());
		// System.out.println(h.tetes.size());
		// System.out.println(h.tetes.get(0).getPosition().x + " - "+ h.tetes.get(0).getPosition().y);
		// System.out.println(h.tetes.get(1).getPosition().x + " - "+ h.tetes.get(1).getPosition().y);
	}
}