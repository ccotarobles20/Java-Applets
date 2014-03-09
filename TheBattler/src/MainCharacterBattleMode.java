import java.awt.*;

public class MainCharacterBattleMode implements Monster
{
	public static int Health = 12;
	public static int FullHealth = 12;
	public static int Attack = 4;
	public static int Defense = 2;
	public static int Speed = 2;
	
	int x, y, size;
	int AttackChoice = 1;
	int change = 2;
	int Damage = 0;
	
	public MainCharacterBattleMode(int x, int y, int size)
	{
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	public void drawAttacks(Graphics g, boolean AttackChosen, boolean down)
	{
		if (down == true)
		{
			AttackChoice += 1;
			down = false;
		}
		
		if (AttackChoice > 2)
		{
			AttackChoice = 1;
		}
		
		if (AttackChosen == false && Health > 0)
		{
			g.setColor(Color.white);
			if (AttackChoice == 1)
			{
				g.setColor(Color.red);
			}
			g.fillRoundRect(x+3*size/2-20, y+40, 150, 30, 5, 5);
			g.setColor(Color.white);
			if (AttackChoice == 2)
			{
				g.setColor(Color.red);
			}
			g.fillRoundRect(x+3*size/2-20, y+80, 150, 30, 5, 5);
			
			g.setColor(Color.red);
			g.setFont(AttackFont);
			g.drawString("Choose an", x+5*size/4, y);
			g.drawString("Attack Below:", x+5*size/4, y+30);
			g.setColor(Color.black);
			g.setFont(StatFont);
			g.drawString("Punch Attack", x+3*size/2, y+60);
			g.drawString("Power Up", x+3*size/2, y+100);
			g.drawString("Scroll to the Attack you wish to use with the (s) key, then hit (Space) to Attack.", 200, 150);
		}
		
		if (AttackChosen == false && Health ==  0)
		{
			y += 200;
			g.setColor(Color.red);
			g.setFont(AttackFont);
			g.drawString("You have been killed.", 250, 300);
		}
	}
	
	public boolean attackBehavior(Graphics g)
	{
		if (AttackChoice == 1)
		{
			g.drawString("Rezin used Punch Attack!", 300, 150);
			x += change;
			if (x > 200)
			{
				change = 0;
				Damage = Attack - goblin.Defense;
				goblin.Health -= Damage;
				if (goblin.Health < 0)
				{
					goblin.Health = 0;
				}
				x = 200;
			}
			if (x > 199)
			{
				g.drawString("Punch Attack did " + Damage + " Damage!", 300, 170);
				g.drawString("Hit (space) to continue.", 300, 190);
			}
		}
		if (AttackChoice == 2)
		{
			g.drawString("Rezin used Power Up!", 300, 150);
			y -= change/2;
			if (y < 300)
			{
				change = 0;
				Attack += 1;
				y = 300;
			}
			if (y < 301)
			{
				g.drawString("You Gained 1 Attack Power!", 300, 170);
				g.drawString("Hit (space) to continue.", 300, 190);
			}
		}
		
		if (Health <= 0)
		{
			return true;
		}
		
		return false;
	}
	
	public void reset()
	{
		//Resets the Main Character to his/her initial x and y position and their initial change value.
		x = 100;
		y = 350;
		change = 2;
	}
	
	public void drawStats(Graphics g)
	{
		//Draws the Main Character's Stats.
		g.setColor(Color.black);
		g.setFont(StatFont);
		g.drawString("Health:      " + Health + " / " + FullHealth, x, y-80);
		g.drawString("Attack:       " + Attack, x, y-60);
		g.drawString("Defense:    " + Defense, x, y-40);
		g.drawString("Speed:       " + Speed, x, y-20);
	}
	
	public void drawMonster(Graphics g)
	{
		//Draws the Main Character in the Battle Screen.
		g.setColor(YellowFingers);
		g.fillOval(x+(3*size/4), y+(size/6), size/3, size); //Left Arm
		g.setColor(Color.black);
		g.drawOval(x+(3*size/4), y+(size/6), size/3, size); //Left Arm Outline
		
		g.setColor(YellowFingers);
		g.fillOval(x, y+size, size*2, size/3); //Right Arm
		g.setColor(Color.black);
		g.drawOval(x, y+size, size*2, size/3); //Right Arm Outline
		
		g.setColor(DeepRed);
		g.fillOval(x, y, size, size*2); //Body
		g.setColor(Color.black);
		g.drawOval(x, y, size, size*2);  //Body Outline
		
		g.setColor(FaceRed);
		g.fillOval(x, y, size, size); //Face
		g.setColor(Color.black);
		g.drawOval(x, y, size, size); //Face Outline
		
		g.setColor(BlueEyes);
		g.fillOval(x+(5*size/8), y+size/4, size/4, size/6); //Eye
		g.setColor(Color.black);
		g.drawOval(x+(5*size/8), y+size/4, size/4, size/6); //Eye Outline
		
		g.drawArc(x+size/2, y+size/2, size/2, size/4, 180, 120); //Smile
	}
}
