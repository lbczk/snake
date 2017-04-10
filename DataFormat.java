import java.io.IOException;

import decor.Decor;
public class DataFormat
{
	static final String[] maps = {"empty.png", "ip22.png", "lab.png", "sierp.png"};
	int w=60, h=60;

	public DataFormat() throws IOException
	{
		for(int i=0; i < maps.length; i++)
		{
			String s= maps[i];
			System.out.println(s);
			new Decor(s, w, h).toDataFormat(200, 300);
		}
	}

	public static void main(String[] args) throws IOException {
		new DataFormat();
	}
}