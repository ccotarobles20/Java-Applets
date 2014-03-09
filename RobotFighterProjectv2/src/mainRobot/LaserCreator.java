package mainRobot;
import java.awt.*;

public class LaserCreator
{
	int ArrayNum = 0;

	boolean[] Drawn = new boolean[7];
	Laser[] Fire = new Laser[7];
	
	public LaserCreator()
	{
		for (int i = 0; i < 7; i++)
		{
			Fire[i] = new Laser();
			Drawn[i] = false;
		}
	}
	
	public boolean collision(Rectangle enemy)
	{
		//detects if each laser collides with each enemy.
		boolean hit1 = Fire[ArrayNum].collision(enemy);
		boolean hit2 = Fire[ArrayNum+1].collision(enemy);
		boolean hit3 = Fire[ArrayNum+2].collision(enemy);
		boolean hit4 = Fire[ArrayNum+3].collision(enemy);
		boolean hit5 = Fire[ArrayNum+4].collision(enemy);
		boolean hit6 = Fire[ArrayNum+5].collision(enemy);
		boolean hit7 = Fire[ArrayNum+6].collision(enemy);
		
		if (hit1 || hit2|| hit3 || hit4 || hit5 || hit6 || hit7)
		{
			return true;
		}
		else
			return false;
	}
	
	public void create(Graphics g, boolean space, int x, int y)
	{
		//Code draws up to 7 lasers at once.  I feel like there is a more efficient/easier way to do this, but I am not sure how.  Basically, this code stores each laser as a
		// boolean number (1-7).  If the number is true or drawn, then draws the next one and so on till it hits 7.  However, once the 7th shot is drawn, you will be unable to fire
		//another shot until the 7th laser is destroyed.
		if (Drawn[ArrayNum+6])
		{
			Drawn[ArrayNum+6] = Fire[ArrayNum+6].createLaser(g, false, x, y);
			Drawn[ArrayNum+5] = Fire[ArrayNum+5].createLaser(g, false, x, y);
			Drawn[ArrayNum+4] = Fire[ArrayNum+4].createLaser(g, false, x, y);
			Drawn[ArrayNum+3] = Fire[ArrayNum+3].createLaser(g, false, x, y);
			Drawn[ArrayNum+2] = Fire[ArrayNum+2].createLaser(g, false, x, y);
			Drawn[ArrayNum+1] = Fire[ArrayNum+1].createLaser(g, false, x, y);
			Drawn[ArrayNum] = Fire[ArrayNum].createLaser(g, false, x, y);
		}
		
		if (Drawn[ArrayNum+5] && !Drawn[ArrayNum+6])  // Draws the seventh shot, continuation of other shots.
		{
			Drawn[ArrayNum+6] = Fire[ArrayNum+6].createLaser(g, space, x, y);
			Drawn[ArrayNum+5] = Fire[ArrayNum+5].createLaser(g, false, x, y);
			Drawn[ArrayNum+4] = Fire[ArrayNum+4].createLaser(g, false, x, y);
			Drawn[ArrayNum+3] = Fire[ArrayNum+3].createLaser(g, false, x, y);
			Drawn[ArrayNum+2] = Fire[ArrayNum+2].createLaser(g, false, x, y);
			Drawn[ArrayNum+1] = Fire[ArrayNum+1].createLaser(g, false, x, y);
			Drawn[ArrayNum] = Fire[ArrayNum].createLaser(g, false, x, y);
		}
		
		if (Drawn[ArrayNum+4] && !Drawn[ArrayNum+5])  // Draws the sixth shot, continuation of other shots.
		{
			Drawn[ArrayNum+5] = Fire[ArrayNum+5].createLaser(g, space, x, y);
			Drawn[ArrayNum+4] = Fire[ArrayNum+4].createLaser(g, false, x, y);
			Drawn[ArrayNum+3] = Fire[ArrayNum+3].createLaser(g, false, x, y);
			Drawn[ArrayNum+2] = Fire[ArrayNum+2].createLaser(g, false, x, y);
			Drawn[ArrayNum+1] = Fire[ArrayNum+1].createLaser(g, false, x, y);
			Drawn[ArrayNum] = Fire[ArrayNum].createLaser(g, false, x, y);
			for (int i = 6; i < 7; i++)
			{
				Drawn[ArrayNum+i] = Fire[ArrayNum+i].createLaser(g, false, x, y);
			}
		}
		
		if (Drawn[ArrayNum+3] && !Drawn[ArrayNum+4])  // Draws the fifth shot, continuation of other shots.
		{
			Drawn[ArrayNum+4] = Fire[ArrayNum+4].createLaser(g, space, x, y);
			Drawn[ArrayNum+3] = Fire[ArrayNum+3].createLaser(g, false, x, y);
			Drawn[ArrayNum+2] = Fire[ArrayNum+2].createLaser(g, false, x, y);
			Drawn[ArrayNum+1] = Fire[ArrayNum+1].createLaser(g, false, x, y);
			Drawn[ArrayNum] = Fire[ArrayNum].createLaser(g, false, x, y);
			for (int i = 5; i < 7; i++)
			{
				Drawn[ArrayNum+i] = Fire[ArrayNum+i].createLaser(g, false, x, y);
			}
		}
		
		if (Drawn[ArrayNum+2] && !Drawn[ArrayNum+3])  // Draws the fourth shot, continuation of other shots.
		{
			Drawn[ArrayNum+3] = Fire[ArrayNum+3].createLaser(g, space, x, y);
			Drawn[ArrayNum+2] = Fire[ArrayNum+2].createLaser(g, false, x, y);
			Drawn[ArrayNum+1] = Fire[ArrayNum+1].createLaser(g, false, x, y);
			Drawn[ArrayNum] = Fire[ArrayNum].createLaser(g, false, x, y);
			for (int i = 4; i < 7; i++)
			{
				Drawn[ArrayNum+i] = Fire[ArrayNum+i].createLaser(g, false, x, y);
			}
		}
		
		if (Drawn[ArrayNum+1] && !Drawn[ArrayNum+2])  // Draws the third shot, continuation of other shots.
		{
			Drawn[ArrayNum+2] = Fire[ArrayNum+2].createLaser(g, space, x, y);
			Drawn[ArrayNum+1] = Fire[ArrayNum+1].createLaser(g, false, x, y);
			Drawn[ArrayNum] = Fire[ArrayNum].createLaser(g, false, x, y);
			for (int i = 3; i < 7; i++)
			{
				Drawn[ArrayNum+i] = Fire[ArrayNum+i].createLaser(g, false, x, y);
			}
		}
		
		if (Drawn[ArrayNum] && !Drawn[ArrayNum+1]) //Draws the second shot, continuation of other shots.
		{
			Drawn[ArrayNum+1] = Fire[ArrayNum+1].createLaser(g, space, x, y);
			Drawn[ArrayNum] = Fire[ArrayNum].createLaser(g, false, x, y);
			for (int i = 2; i < 7; i++)
			{
				Drawn[ArrayNum+i] = Fire[ArrayNum+i].createLaser(g, false, x, y);
			}
		}
		
		if (!Drawn[ArrayNum] && !Drawn[ArrayNum+1] && !Drawn[ArrayNum+2] && !Drawn[ArrayNum+3] && !Drawn[ArrayNum+4] &&!Drawn[ArrayNum+5] && !Drawn[ArrayNum+6])  //Draws the first shot, continuation of other shots.
		{
			Drawn[ArrayNum] = Fire[ArrayNum].createLaser(g, space, x, y);
			for (int i = 1; i < 7; i++)
			{
				Drawn[ArrayNum+i] = Fire[ArrayNum+i].createLaser(g, false, x, y);
			}
		}
		
		if (ArrayNum >= 7)
		{
			ArrayNum = 0;
		}
	}
}
