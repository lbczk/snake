import java.io.IOException;
import java.awt.Point;

import java.util.*;

public class Demo
{
	public static void main(String[] args) throws IOException{
		Image im = new Image("test.png");
		ArrayList<Point> t = new ArrayList<Point>();
		t = im.toPointArray();
		System.out.print(t.size());

	}
}