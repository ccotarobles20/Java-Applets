package mainRobot;
import java.awt.*;

public class Laser
{
	int x = 1000; int y = 1000, OriginY = 1000;
	int size = 12;
	int speed = 2;
	boolean drawn = false; //tells program if the laser is being drawn.
	
	boolean Draw = false; //tells program if the laser should be drawn for the first time.
	
	Font RegFont = new Font("Times New Roman", 0, 12);
	
	public Laser()
	{
	}
	
	public boolean collision(Rectangle enemy)
	{
		Rectangle Laser = new Rectangle(x+size, y-size, size/4, size);
		
		//Detects if the laser hits any enemies.  If it does, destroys it (by sending it to 1000,1000) and sets all the draw values to false.
		if (Laser.intersects(enemy))
		{
			Draw = false;
			drawn = false;
			x = 1000;
			y = 1000;
			return true;
		}
		else
			return false;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.red);
		g.fillOval(x+size, y-size, size/4, size);
		g.setColor(Color.black);
		g.drawOval(x+size, y-size, size/4, size);
	}
	
	public void shoot()
	{
		y -= speed;
		
		//behavior of the laser as it is shot.
		if (y + size < 0)
		{
			y = OriginY;
			Draw = false;
			drawn = false;
		}
		
	}
	
	public boolean createLaser(Graphics g, boolean space, int x, int y)
	{
		//creates the laser if space is pressed, draws the laser if drawn == true
		if (space)
		{
			Draw = true;
			this.x = x;
			this.y = y;
			OriginY = y;
			drawn = true;
		}
		
		if (Draw)
		{
			draw(g);
			shoot();
		}
		
		return drawn;
	}
}
