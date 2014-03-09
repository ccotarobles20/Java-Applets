import java.awt.*;

public class Grass implements EnemyFinder
{
	int x, y, size, GrassW, GrassH;
	
	public Grass(int x, int y, int size)
	{
		this.x = x;
		this.y = y;
		this.size = size;
		this.GrassW = 5*size;
		this.GrassH = 12*size;
	}
	
	public boolean detect(Graphics g, Rectangle Rezin)
	{
		//Detecs if the Player is intersecting the Grass (Findable).  If they are, runs randomfind and returns that value, otherwise returns false.
		Rectangle Findable = new Rectangle(x, y, GrassW, GrassH);
		
		if (Rezin.intersects(Findable))
		{
			return randomfind();
		}
		else
			return false;
	}
	
	public boolean randomfind()
	{
		// Randomly decides, using an algorithm I created based on the time passed, if the player should encounter an enemy.  If yes, returns true, otherwise returns false.
		double Randomizer = System.currentTimeMillis() / 1000;
		Randomizer = (Randomizer % 35);
		if (Randomizer > 30 && Randomizer%2 == 1)
		{
			return true;
		}
		else if (Randomizer == 11 || Randomizer == 17 || Randomizer == 21)
		{
			return true;
		}
		else
			return false;
	}
	
	public void draw(Graphics g)
	{
		//Draws the grass, basically a whole bunch of really tiny ovals.
		for (int j = 0; j < 12*size; j += 4*size)
		{
			for (int i = 0; i < 5*size; i += size)
			{
				g.setColor(Color.black);
				g.drawOval(x+2*i, y+j, size, size*3);
				g.setColor(Color.green);
				g.fillOval(x+2*i, y+j, size, size*3);
			}
		}
	}
}
