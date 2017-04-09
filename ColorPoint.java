import java.awt.Color;
import java.awt.Point;

public class ColorPoint extends Point
{
	private Color c;

	public ColorPoint(Color c, int x, int y)
	{
		super(x, y);
		this.c = c;
	}

	public ColorPoint(Color c, Point p)
	{
		this(c, p.x, p.y);
	}

	public Color getColor()
	{
		return c;
	}

}