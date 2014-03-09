import java.awt.*;

public class goblin implements Monster
{
	public static int Health = 7;
	public static int FullHealth = 7;
	public static int Attack = 4;
	public static int Defense = 2;
	public static int Speed = 1;
	
	int x, y, size, StartX, startY, characterH, xOffSet;
	int change = 2;
	int Damage = 0;
	
	public goblin(int x, int y, int size)
	{
		this.x = x;
		this.y = y;
		this.size = size;
		this.StartX = x;
		this.startY = y;
		this.characterH = 27*size/15;
		this.xOffSet = 2*size/15;
	}
	
	public boolean attackBehavior(Graphics g)
	{
		if (Health > 0)
		{
			g.setColor(Color.black);
			g.drawString("Goblin used Charge!", 300, 150);
			x -= change;
			if (x < 500)
			{
				change = 0;
				Damage = Attack - MainCharacterBattleMode.Defense;
				MainCharacterBattleMode.Health -= Damage;
				if (MainCharacterBattleMode.Health < 0)
				{
					MainCharacterBattleMode.Health = 0;
				}
				x = 500;
			}
			if (x < 501)
			{
				g.drawString("Goblin Charge did " + Damage + " Damage!", 300, 170);
				g.drawString("Hit (space) to continue.", 300, 190);
			}
		}
		
		if (Health <= 0)
		{
			y = 2000;
			g.setFont(AttackFont);
			g.drawString("Congratulations!", x, 200);
			g.drawString("You killed the Goblin!", x, 230);
			return true;
		}
		
		return false;
	}
	
	public void reset()
	{
		x = 600;
		y = 200;
		change = 2;
	}
	
	public void drawStats(Graphics g)
	{
		g.setColor(Color.black);
		g.setFont(StatFont);
		g.drawString("Health:        " + Health, x+xOffSet, y+characterH);
		g.drawString("Attack:        " + Attack, x+xOffSet, y+characterH+20);
		g.drawString("Defense:      " + Defense, x+xOffSet, y+characterH+40);
		g.drawString("Speed:        " + Speed, x+xOffSet, y+characterH+60);
	}
	
	public void drawMonster(Graphics g)
	{	
		g.setColor(GoblinGreen);
		g.fillOval(x-size/4, y+size, 3*size/4, size/6); //Left Hand
		g.setColor(Color.black);
		g.drawOval(x-size/4, y+size, 3*size/4, size/6); //Left Hand Outline
		
		g.setColor(GoblinGreen);
		g.fillOval(x+size/6, y+11*size/8, size/5, size/5); //Left Foot
		g.setColor(Color.black);
		g.drawOval(x+size/6, y+11*size/8, size/5, size/5); //Left Foot Outline
		
		g.setColor(GoblinGreen);
		g.fillOval(x+size/2, y+11*size/8, size/5, size/5); //Right Foot
		g.setColor(Color.black);
		g.drawOval(x+size/2, y+11*size/8, size/5, size/5); //Right Foot Outline
		
		g.setColor(GoblinGreen);
		g.fillOval(x+size/5, y+size/2, size/2, size); //Body
		g.setColor(Color.black);
		g.drawOval(x+size/5, y+size/2, size/2, size); //Body Outline
		
		g.setColor(GoblinGreen);
		g.fillOval(x, y, size/4, 3*size/5); //Left Horn
		g.fillOval(x+size/2, y, size/4, 3*size/4); //Right Horn
		g.setColor(Color.black);
		g.drawOval(x, y, size/4, 3*size/4); //Left Horn Outline
		g.drawOval(x+size/2, y, size/4, 3*size/4); //Right Horn Outline
		
		g.setColor(GoblinGreen);
		g.fillRect(x-size/7, y+size/4, size, size/2); //Face
		g.setColor(Color.black);
		g.drawRect(x-size/7, y+size/4, size, size/2); //Face Outline
		
		g.setColor(GoblinGreen);
		g.fillOval(x+9*size/16, y+7*size/8, size/6, size/2); //Right Hand
		g.setColor(Color.black);
		g.drawOval(x+9*size/16, y+7*size/8, size/6, size/2); //Right Hand Outline
		
		g.setColor(GoblinRed);
		g.fillOval(x+size/12, y+size/3, size/4, size/10); //Left eye
		g.fillOval(x+5*size/12, y+size/3, size/4, size/10); //Right eye
		g.setColor(Color.black);
		g.drawOval(x+size/12, y+size/3, size/4, size/10); //Left eye Outline
		g.drawOval(x+5*size/12, y+size/3, size/4, size/10); //Right eye Outline
		g.drawArc(x+size/12, y+size/2, size/2, size/10, 180, 180);
	}
}