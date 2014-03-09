import java.awt.*;

public class Mud implements EnemyFinder
{
	int x, y, size, MudW, MudH;
	
	public Mud(int x, int y, int size)
	{
		this.x = x;
		this.y = y;
		this.size = size;
		this.MudW = 3*size;
		this.MudH = 3*size;
	}
	
	Color MudBrown = new Color(145, 89, 60);
	
	public boolean detect(Graphics g, Rectangle Rezin)
	{
		//Detecs if the Player is intersecting the Grass (Findable).  If they are, runs randomfind and returns that value, otherwise returns false.
		Rectangle Findable = new Rectangle(x, y, MudW, MudH);
		
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
		//Draws the mud, basically a whole bunch of really tiny ovals.
		for (int j = 0; j < 5*size; j += size)
		{
			for (int i = 0; i < 15*size; i += 4*size)
			{
				g.setColor(Color.black);
				g.drawOval(x+i, y+2*j, size*3, size);
				g.setColor(MudBrown);
				g.fillOval(x+i, y+2*j, size*3, size);
			}
		}
	}
}