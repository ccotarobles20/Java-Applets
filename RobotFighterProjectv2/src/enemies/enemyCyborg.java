package enemies;

import java.awt.*;

public class enemyCyborg implements EnemyInterface
{
	int x, y, size;
	int random1, random2, random3;
	
	public enemyCyborg(int x, int y, int size, int random1, int random2, int random3)
	{
		this.x = x;
		this.y = y;
		this.size = size;
		
		//Random colors
		this.random1 = random1;
		this.random2 = random2;
		this.random3 = random3;
	}
	
	public Rectangle collision()
	{
		Rectangle CyborgRect = new Rectangle(x-size*3, y, size*10, size*8);
		return CyborgRect;
	}
	
	public void draw(Graphics g)
	{
		Color RandomColor = new Color(random1, random2, random3);
		
		// HEAD     //
		g.setColor(RandomColor);
		g.fillRect(x-size/2, y, size*3, size*3);
		g.setColor(Color.black);
		g.drawRect(x-size/2, y, size*3, size*3);
		
		//   BODY   //
		g.setColor(RandomColor);
		g.fillRect(x-size, y+size*2, size*4, size*4);
		g.setColor(Color.black);
		g.drawRect(x-size, y+size*2, size*4, size*4);
		
		//   LEFT ARM   //
		g.setColor(RandomColor);
		g.fillRect(x-size*3, y+size*2, size*2, size*2);
		g.setColor(Color.black);
		g.drawRect(x-size*3, y+size*2, size*2, size*2);
		
		//   RIGHT ARM   //
		g.setColor(RandomColor);
		g.fillRect(x+size*3, y+size*2, size*2, size*2);
		g.setColor(Color.black);
		g.drawRect(x+size*3, y+size*2, size*2, size*2);
		
		//   LEFT LEG   //
		g.setColor(RandomColor);
		g.fillRect(x-size, y+size*6, size, size*2);
		g.setColor(Color.black);
		g.drawRect(x-size, y+size*6, size, size*2);
		
		//   RIGHT LEG   //
		g.setColor(RandomColor);
		g.fillRect(x+2*size, y+size*6, size, size*2);
		g.setColor(Color.black);
		g.drawRect(x+2*size, y+size*6, size, size*2);
		
		// LEFT EYE  //
		g.setColor(Color.cyan);
		g.fillOval(x, y+size/2, size*2/3, size*2/3);
		g.setColor(Color.black);
		g.drawOval(x, y+size/2, size*2/3, size*2/3);
		
		// RIGHT EYE  //
		g.setColor(Color.cyan);
		g.fillOval(x+size, y+size/2, size*2/3, size*2/3);
		g.setColor(Color.black);
		g.drawOval(x+size, y+size/2, size*2/3, size*2/3);
	}
	
	public void behavior(int w, int h, boolean Dead)
	{
		//The enemy cyborgs fall at a set speed.  Once they reach the bottom, they're transported back up and continue to fall through the screen again, essentially creating an infinite loop.
		y += speed;
		if (y/2 > h)
		{
			y = -100;
		}
		if (Dead)
		{
			y = -1300;
		}
	}
}
