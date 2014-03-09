package abstractObjects;
import java.awt.*;

public class Scores
{
	public static int Health = 3; // Your total Health
	public static int points = 0; // Your total points
	
	Font ScoreFont = new Font("Comic Sans MS", 0, 14);
	
	public void draw(Graphics g)
	{
		g.setFont(ScoreFont);
		g.setColor(Color.black);
		g.drawString("Score: " + points, 20, 40);
		g.drawString("Health: " + Health, 20, 60);
	}
	
	public void increase(int addPoints)
	{
		// Runs when an enemy is killed, increases your number of points.
		points += addPoints;
	}
}
