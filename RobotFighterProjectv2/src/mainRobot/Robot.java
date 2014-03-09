package mainRobot;
import java.awt.*;

import abstractObjects.Scores;

public class Robot
//This class draws the main Robot, capable of moving left to right and using a jet ability to fly around at will for a specified amount of time.
{
	int x, y, size, RoboHeight, RoboWidth;
	int changeInY = 1, changeInX = 0;
	int AccelSpeed = 0, AccelFrequency = 2; //Gravity variables, help accelerate the user as he/she falls
	
	int MOV = 0, speed = 2, DIR = speed; //Code for the animation of the Robot's eye
	
	boolean JET = false;
	int JetWait = 1000, JetTime = JetWait, JetLength = 200;
	
	Font JetLoadingFont = new Font("Comic Sans MS", 0, 20);
	Font RegFont = new Font("Times New Roman", 0, 12);

	String JetText = "READY! (hit the 'w' key to use your power)";

	Color RobotOrange = new Color(216, 125, 24);
	
	LaserCreator Shoot = new LaserCreator();
	
	public Robot(int x, int y, int size)
	{
		this.x = x;
		this.y = y;
		this.size = size;
		this.RoboHeight = 5*size;
		this.RoboWidth = 2*size;
	}
	
	public boolean collision(Rectangle enemy)
	{
		//detects if the main Robot is colliding with the enemy robot, also passes through information to help detect if the enemy and laser have collided.
		Rectangle Bob = new Rectangle(x-size/4, y, size*5/2, size*4);
		if (Bob.intersects(enemy))
		{
			Scores.Health -= 1;
			return true;
		}
		
		return Shoot.collision(enemy);
	}
	
	public void drawBasic(Graphics g)
	{
		//  THE ROBOT HEAD //
		g.setColor(RobotOrange);
		g.fillOval(x, y, size*2, size*2);
		g.setColor(Color.black);
		g.drawOval(x, y, size*2, size*2);
		
		//  THE ROBOT EYE //
		g.setColor(Color.red);
		g.fillOval(x+size+MOV, y+size/3, size/4, size/4);
		g.setColor(Color.black);
		g.drawOval(x+size+MOV, y+size/3, size/4, size/4);
		
		//  THE ROBOT LEFT FOOT //
		g.setColor(RobotOrange);
		g.fillRect(x+size/3, y+5*size/2, size/2, size);
		g.setColor(Color.black);
		g.drawRect(x+size/3, y+5*size/2, size/2, size);
		
		//  THE ROBOT RIGHT FOOT //
		g.setColor(RobotOrange);
		g.fillRect(x+4*size/3, y+5*size/2, size/2, size);
		g.setColor(Color.black);
		g.drawRect(x+4*size/3, y+5*size/2, size/2, size);
		
		//  THE ROBOT BODY //
		g.setColor(RobotOrange);
		g.fillRect(x, y+size, size*2, size*2);
		g.setColor(Color.black);
		g.drawRect(x, y+size, size*2, size*2);
		
		//  THE ROBOT LEFT SHOULDER //
		g.setColor(RobotOrange);
		g.fillOval(x-size/4, y+size, size/2, size);
		g.setColor(Color.black);
		g.drawOval(x-size/4, y+size, size/2, size);
		
		//  THE ROBOT LEFT ARM, HAND //
		int uppity = 1;
		int uppity2 = 1;
		g.setColor(Color.black);
		g.drawOval(x-size/4, y+size*2, size/2, size); //Robot Left Hand
		g.drawRect(x-size/4, y+size*3/2, size/2, size); //Robot Left Arm
		g.setColor(RobotOrange);
		g.fillOval(x-size/4, y+size*2-uppity, size/2, size); //Robot Left Hand
		g.fillRect(x-size/4+uppity2, y+size*3/2, size/2-uppity2, size); //Robot Left Arm
		
		//  THE ROBOT RIGHT SHOULDER //
		g.setColor(RobotOrange);
		g.fillOval(x+size*7/4, y+size, size/2, size);
		g.setColor(Color.black);
		g.drawOval(x+size*7/4, y+size, size/2, size);
		
		//  THE ROBOT RIGHT ARM, HAND //
		g.setColor(Color.black);
		g.drawOval(x+size*7/4, y+size*2, size/2, size); //Robot Left Hand
		g.drawRect(x+size*7/4, y+size*3/2, size/2, size); //Robot Left Arm
		g.setColor(RobotOrange);
		g.fillOval(x+size*7/4, y+size*2-uppity, size/2, size); //Robot Left Hand
		g.fillRect(x+size*7/4+uppity2, y+size*3/2, size/2-uppity2, size); //Robot Left Arm
	}
	
	public void behavior(Graphics g, int w, int h, boolean up, boolean down, boolean left, boolean right, boolean space)
	{
		Jet(g, up, down, h); //runs the jet code.
		
		Shoot.create(g, space, x, y); //creates lasers if space is pressed.
		
		if (x < 0)
		{
			changeInX = 0;
			x++;
		}
		
		if (x+RoboWidth > w)
		{
			changeInX = 0;
			x--;
		}
		
		// ANIMATION CODE //
		//---------------//
		MOV += DIR;
		
		if (MOV > size/2)
		{
			DIR = -speed;
		}
		
		if (MOV < -size/2)
		{
			DIR = speed;
		}
		//------------//
		
		//CODE FOR THE GAME'S GRAVITY
		//-------------------------//
		y += changeInY;
		x += changeInX;
		
		AccelSpeed += 1;
		if (AccelSpeed == AccelFrequency)
		{
			changeInY += 1;
			AccelSpeed = 0;
		}
		//----------------------//
		
		//Code for the Robot movement//
		if (y + RoboHeight > h)
		{
			changeInY = 0;
		}
		
		if (left)
		{
			changeInX = -speed;
		}
		
		if (right)
		{
			changeInX = speed;
		}
		
		if (!left && !right)
		{
			changeInX = 0;
		}
		//---------------------//
	}
	
	public void Jet(Graphics g, boolean up, boolean down, int h)
	{
		g.setFont(JetLoadingFont);
		g.setColor(Color.black);
		//Determines if the Jet Loader is charging, being used, or done charging, and stores that information in a string called JetText.
		if (JetTime == JetWait);
		{
			JetText = "READY! (hit the 'w' key to use your power)";
		}
		if (JetTime < JetWait && !JET)
		{
			JetText = "Charging...";
		}
		if (JetTime < JetWait && JET)
		{
			JetText = "JET!";
		}
		
		g.drawString("Jet Loader: " + JetText, 10, 20); //Draws the text that tells the user about the Jet Loader.
		
		if (up && JetTime == JetWait)
			//If the Jet is charged and the user presses the up (w) key, then starts the jet.
		{
			JetTime = JetLength;
			JET = true;
			speed = speed*4;
		}
		
		if (!JET)
			// When jet is not being used, charges the jet.
			JetTime += 1;
		
		if (JetTime > JetWait)
			//automatically sets JetTime to JetWait if the user has waited longer than the required time to use the jet.
		{
			JetTime = JetWait;
		}
		
		if (JET)
			//when the jet is activated, allows the user to move freely up and down at an accelerated pace.  JetTime also goes down steadily until it hits 0, and the jet is deactivated.
		{
			if (y < 0)
			{
				y=0;
			}
			
			if (y+RoboHeight > h)
			{
				y=h-RoboHeight;
			}
			
			JetTime -= 1;
			changeInY = 0;
			
			if (up)
			{
				y -= speed;
			}
			
			if (down)
			{
				y += speed;
			}

			if (JetTime == 0)
			{
				JET = false;
				JetTime = 0;
				speed = speed/4;
			}
		}
	}
}