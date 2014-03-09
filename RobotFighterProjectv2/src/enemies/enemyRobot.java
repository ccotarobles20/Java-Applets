package enemies;

import java.awt.*;

public class enemyRobot implements EnemyInterface
{
	int x, y, size;
	int random1, random2, random3;
	
	public enemyRobot(int x, int y, int size, int random1, int random2, int random3)
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
		Rectangle RoboRect = new Rectangle(x-size, y, 4*size, 4*size);
		return RoboRect;
	}
	
	public void draw(Graphics g)
	{
		Color RandomColor = new Color(random1, random2, random3);
		
		//   HEAD  //
		g.setColor(RandomColor);
		g.fillOval(x, y, size*2, size*2);
		g.setColor(Color.black);
		g.drawOval(x, y, size*2, size*2);
		
		//    BODY  //
		g.setColor(RandomColor);
		g.fillRect(x, y+size, size*2, size*2);
		g.setColor(Color.black);
		g.drawRect(x, y+size, size*2, size*2);
		
		//    LEFT ARM    //
		g.setColor(RandomColor);
		g.fillRect(x-size, y+size*3/2, size, size/2);
		g.setColor(Color.black);
		g.drawRect(x-size, y+size*3/2, size, size/2);
		
		//     RIGHT ARM   //
		g.setColor(RandomColor);
		g.fillRect(x+2*size, y+size*3/2, size, size/2);
		g.setColor(Color.black);
		g.drawRect(x+2*size, y+size*3/2, size, size/2);
		
		//   LEFT EYE     //
		g.setColor(Color.red);
		g.fillOval(x+size/2, y+size/2, size/3, size/3);
		g.setColor(Color.black);
		g.drawOval(x+size/2, y+size/2, size/3, size/3);
		
		//   RIGHT EYE     //
		g.setColor(Color.red);
		g.fillOval(x+size, y+size/2, size/3, size/3);
		g.setColor(Color.black);
		g.drawOval(x+size, y+size/2, size/3, size/3);
		
		// LEFT LEG      //
		g.setColor(RandomColor);
		g.fillRect(x+size/2, y+3*size, size/3, size);
		g.setColor(Color.black);
		g.drawRect(x+size/2, y+3*size, size/3, size);
		
		// RIGHT LEFT   //
		g.setColor(RandomColor);
		g.fillRect(x+size*3/2, y+3*size, size/3, size);
		g.setColor(Color.black);
		g.drawRect(x+size*3/2, y+3*size, size/3, size);
	}
	
	public void behavior(int w, int h, boolean Dead)
	{
		//The enemy robots fall at a set speed.  Once they reach the bottom, they're transported back up and continue to fall through the screen again, essentially creating an infinite loop.
		y += speed;
		if (y/2 > h)
		{
			y = -100;
		}
		if (Dead)
		{
			y = -1000;
		}
	}
}
