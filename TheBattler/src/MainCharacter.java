import java.awt.*;

public class MainCharacter
{
	int x, y, size;
	int characterW, characterH; //Width and Height of the character
	int speed = 3; //speed at which the character moves
	
	//these variables are used to make the character animate (his arms and legs moving)
	int switcher = 1;
	int switchDir = 1;
	int switchLength = 1;
	
	Color DeepRed = new Color(220, 0, 0);
	Color YellowFingers = new Color(230, 230, 0);
	Color BlueEyes = new Color(0, 255, 255);
	Color FaceRed = new Color(255, 200, 200);
	
	Font OutOfBounds = new Font("Times New Roman", 1, 16);
	
	public MainCharacter(int x, int y, int size)
	{
		this.x = x;
		this.y = y;
		this.size = size;
		this.characterW = 2*size;
		this.characterH = 3*size;
	}
	
	public void draw(Graphics g)
	{
		int switchLleg = switcher%3;
		if (switchLleg > 1)
		{
			switchLleg = 1;
		}
		
		int switchRleg = switcher-1;
		if (switchRleg > 1)
		{
			switchRleg = 1;
		}
		
		g.setColor(YellowFingers);
		g.fillOval(x-size*2/3+3*(switcher-1), y+size*5/6, size/(switcher), size/(4-switcher)); //left hand
		g.fillOval(x+size*2/3+3*(switcher-1), y+size*5/6, size/(switcher), size/(4-switcher)); //right hand
		g.setColor(Color.blue);
		g.fillOval(x, y+size*10/6, (switchLleg)*size/3, (switchLleg%3)*size/3); //left foot
		g.fillOval(x+size*2/3, y+size*10/6, (switchRleg)*size/3, (switchRleg) *size/3); //right foot
		
		g.setColor(Color.black);
		g.drawOval(x-size*2/3+3*(switcher-1), y+size*5/6, size/(switcher), size/(4-switcher)); //left hand
		g.drawOval(x+size*2/3+3*(switcher-1), y+size*5/6, size/(switcher), size/(4-switcher)); //right hand
		g.drawOval(x, y+size*10/6, switchLleg*size/3, switchLleg*size/3); //left foot
		g.drawOval(x+size*2/3, y+size*10/6, (switchRleg)*size/3, (switchRleg)*size/3); //right foot
		
		g.setColor(DeepRed);
		g.fillOval(x, y+size/3, size, size*5/3); //body red fill to get rid of overlaps
		g.setColor(Color.black);
		g.drawOval(x, y+size/3, size, size*5/3); //body black outline
		
		g.setColor(FaceRed);
		g.fillOval(x, y, size, size); //head red fill to get rid of overlaps
		g.setColor(Color.black);
		g.drawOval(x, y, size, size); //head black outline
		
		g.setColor(BlueEyes);
		g.fillOval(x+size/4, y+size/3, size/6, size/6); //left eye
		g.fillOval(x+size*5/8, y+size/3, size/6, size/6); //right eye
		
		g.setColor(Color.black);
		g.drawOval(x+size/4, y+size/3, size/6, size/6); //left eye
		g.drawOval(x+size*5/8, y+size/3, size/6, size/6); //right eye
		g.drawArc(x+size/4, y+size/2, size/2, size/4, 180, 180); //smile
	}
	
	public void behavior(Graphics g, int h, int w, boolean up, boolean down, boolean right, boolean left)
	{
		//Character animation code, slows down the animation so it's easier for the user to see:
		//--------------------------//
		switchLength += 1;
		if (switchLength == 10)
		{
			if (switcher == 1)
			{
				switchDir = 1;
			}
			
			if (switcher == 3)
			{
				switchDir = -1;
			}
			
			switcher += switchDir;
			switchLength = 0;
		}
		//-----------------------//
		
		// Sets the screen boundaries so you can't walk off-screen//
		//------------------------------------------------------//
		if (x + characterW > w)
		{
			g.setColor(Color.black);
			g.setFont(OutOfBounds);
			g.drawString("Oops!  You can't go there yet.", 400, 100);
			x -= speed;
		}
		
		if (x-size < 0)
		{
			g.setColor(Color.black);
			g.setFont(OutOfBounds);
			g.drawString("Oops!  You can't go there yet.", 400, 100);
			x += speed;
		}
		
		if (y + characterH > h)
		{
			g.setColor(Color.black);
			g.setFont(OutOfBounds);
			g.drawString("Oops!  You can't go there yet.", 400, 100);
			y -= speed;
		}
		
		if (y < 0)
		{
			g.setColor(Color.black);
			g.setFont(OutOfBounds);
			g.drawString("Oops!  You can't go there yet.", 400, 100);
			y += speed;
		}
		//-------------------------------------------------------//
		
		// Basic Movement Code
		//-------------------//
		if (up == true)
		{
			y -= speed;
		}
		
		if (down == true)
		{
			y += speed;
		}
		
		if (left == true)
		{
			x -= speed;
		}
		
		if (right == true)
		{
			x += speed;
		}
		//------------------//
	}
}