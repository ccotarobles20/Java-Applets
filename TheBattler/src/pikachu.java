import java.awt.*;

public class pikachu implements Monster
{
	public static int Health = 9;
	public static int FullHealth = 9;
	public static int Attack = 6;
	public static int Defense = 1;
	public static int Speed = 5;
	
	int x, y, size, startX, startY, characterH, xOffSet;
	int change = 2;
	int Damage = 0;
	
	public pikachu(int x, int y, int size)
	{
		this.x = x;
		this.y = y;
		this.size = size;
		this.startX = x;
		this.startY = y;
		this.characterH = 3*size;
		this.xOffSet = 2*size;
	}
	
	public boolean attackBehavior(Graphics g)
	{
		return false;
	}
	
	public void drawMonster(Graphics g)
	{
		//Draws the Pikachu.
		g.setColor(Color.yellow);
		g.fillRect(x+size/2, y-size/2, size, size); //tail 1
		g.fillRect(x+size, y, size, size); //tail 2
		g.fillRect(x+size*3/2, y+size/2, size, size); //tail 3
		g.fillOval(x+size*2, y-size, size*2, size*3); //body
		g.fillOval(x+size*2, y-size*3/2, size*2, size*2); //head
		g.fillOval(x+size*2, y+size*3/2, size, size); //left leg
		g.fillOval(x+size*3, y+size*3/2, size, size); //right leg
		g.fillOval(x+size*13/4, y-size*5/2, size*2/3, size*2); //right ear
		g.fillOval(x+size*8/4, y-size*5/2, size*2/3, size*2); //left ear
		g.setColor(Color.black);
		g.fillOval(x+size*5/2, y-size, size/4, size/4); //left eye
		g.fillOval(x+size*13/4, y-size, size/4, size/4); //right eye
		g.drawArc(x+size*10/4, y-size/2, size, size/2, 180, 180); //smile
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
	
	public void reset()
	{
		x = startX;
		y = startY;
		change = 2;
	}
}
