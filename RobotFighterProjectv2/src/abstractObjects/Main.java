package abstractObjects;

/*
 List of classes and their functions:
 
 Main: runs the Main program, drawing the enemies and the Main Robot.
 Robot: draws the robot and his behavior, including falling due to gravity and activating the Jet Ability and LaserCreator class.
 
 enemyRobot: Enemy robots, smaller and harder to hit but easier to kill.
 enemyCyborg: Enemy Cyborgs, larger and easier to hit but harder to kill.
 
 Laser: draws the individual lasers each time space is pressed.
 LaserCreator: Code that helps deal with the creation of multiple lasers in an array.
 
 needs to be update, blargh too lazy.
 */

import java.awt.*;

import mainRobot.Robot.*;
import enemies.enemyRobot.*;

public class Main extends BufferedApplet
{
	int x = 100;
	int y = 100;
	int size = 15;
	int EnemyCreation = 0;
	boolean EnemyDestruction = false;
	int Screen = 1;
		
	mainRobot.Robot Bob = new mainRobot.Robot(x, y, size); // Your Robot
	enemies.enemyRobot[] spawnRobot = new enemies.enemyRobot[8]; // Enemy Robot
	enemies.enemyCyborg[] spawnCyborg = new enemies.enemyCyborg[8]; // Enemy Cyborg
	Scores score = new Scores();
	Instructions Intro = new Instructions(20, 125); // Stores all the print statements for instructions and game over screens.
	
	public Main()
	{
		for (int i = 0; i < 8; i++)
		{
			// Creates all the enemies
			spawnRobot[i] = new enemies.enemyRobot(random(i)+52, -100-97*i, 8, 10*i%255, 101*i%255, 31*i%255);
			spawnCyborg[i] = new enemies.enemyCyborg(random(i), -100-201*i, 8, 21*i%255, 10*i%255, 109*i%255);
		}
	}

	public void render(Graphics g)
	{
		int w = getWidth();
		int h = getHeight();
		
		g.setColor(Color.white);
		g.fillRect(0, 0, w, h);
		
		if (Screen == 1)
		{
			//Instructions screen, if you click goes to the mainScreen
			Intro.drawInstructions(g);
			if (mouseDown)
			{
				Screen = 2;
			}
		}
		
		if (Screen == 2)
		{
			Bob.drawBasic(g);
			Bob.behavior(g, w, h, keyDown['w'], keyDown['s'], keyDown['a'], keyDown['d'], !keyDown[' '] && wasKeyDown[' ']);
			
			for (int i = 0; i < spawnRobot.length; i++)
			{
				// for each robot and cyborg, draws the robot, runs its behavior moving down the screen, and detect if it has collided with any of the lasers.
				spawnRobot[i].draw(g);
				spawnRobot[i].behavior(w, h, false);
				Rectangle enemy = spawnRobot[i].collision();
				EnemyDestruction = Bob.collision(enemy);
				if (EnemyDestruction)
				{
					spawnRobot[i].behavior(w, h, true);
					score.increase(1);
				}
				spawnCyborg[i].draw(g);
				spawnCyborg[i].behavior(w, h, false);
				enemy = spawnCyborg[i].collision();
				EnemyDestruction = Bob.collision(enemy);
				if (EnemyDestruction)
				{
					spawnCyborg[i].behavior(w, h, true);
					score.increase(2);
				}
			}
			
			score.draw(g); // draws the score.
			
			if (Scores.Health <= 0)
			{
				//If you run out of Health, goes to the game over screen.
				Screen = 3;
			}
			
			Intro.mainScreen();
		}
		
		if (Screen == 3)
		{
			//Game Over Screen
			Scores.points = 0;
			Scores.Health = 3;
			Intro.drawGameOver(g);
		}
		
		animating = true;
	}
	
	public int random(int i)
	{
		double randomizer = (System.currentTimeMillis() / 1000)*i % 71;
		int random = (int) randomizer;
		
		if (random % 10 == 2)
		{
			return (300 + random);
		}
		else if (random % 10 == 1)
		{
			return 500 + random;
		}
		else if (random % 10 == 3)
		{
			return 1000 + random;
		}
		else if (random % 10 == 4)
		{
			return 400 + random;
		}
		else if (random % 10 == 5)
		{
			return 600 + random;
		}
		else if (random % 10 == 6)
		{
			return 700 + random;
		}
		else if (random % 10 == 7)
		{
			return 100 + random;
		}
		else if (random % 10 == 8)
		{
			return 200 + random;
		}
		else if (random % 10 == 9)
		{
			return 900 + random;
		}
		else
		{
			return random;
		}
	}
}

