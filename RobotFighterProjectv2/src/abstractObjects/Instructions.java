package abstractObjects;

import java.awt.*;

public class Instructions
//Stores all the print statements for instructions and game over screens.
{
	Font TitleFont = new Font("Helvetica", 1, 30);
	Font IntroFont = new Font("Joker", 0, 20);
	int x, y;
	int FinalPoints;
	
	public Instructions(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void drawInstructions(Graphics g)
	{
		g.setColor(Color.black);
		g.setFont(TitleFont);
		g.drawString("Welcome to the Robot Fighter Game.", x, y);
		g.setFont(IntroFont);
		g.drawString("The basic idea is to shoot as many robots and accumulate as many points as possible before your health runs out.", x, y+60);
		g.drawString("Small robots are worth 1 point, big robots are worth 2 points.", x, y+85);
		g.drawString("Use the 'space' bar to fire a missile, and use the (w, s, a, d) characters to move around.", x, y+110);
		g.drawString("Careful not to touch the enemies, or you will lose one of three health points.  When you run out, the game's over.", x, y+135);
		g.drawString("Also, you will see an indicator at the top called the Jet Loader.", x, y+160);
		g.drawString("When it is fully charged you may press the 'w' key, enabling you to fly around the screen at your pleasure for a limited time.", x, y+185);
		g.drawString("Careful not to end your flight above an enemy.  Click anywhere to begin.", x, y+210);
	}
	
	public void mainScreen()
	{
		FinalPoints = Scores.points;
	}
	
	public void drawGameOver(Graphics g)
	{
		g.setFont(TitleFont);
		g.setColor(Color.red);
		g.drawString("GAME  OVER !!!", 400, 250);
		g.setColor(Color.black);
		g.setFont(IntroFont);
		g.drawString("Total points accumulated: " + FinalPoints, 400, 285);
		g.drawString("Refresh the page to play again.", 400, 310);
	}
}
