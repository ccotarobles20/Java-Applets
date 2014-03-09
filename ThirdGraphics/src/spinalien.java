import java.awt.*;

public class spinalien
{
	int x, y, Size, couleur1, couleur2, couleur3;
	int growth = 3;  // indicates the size of the alien arms.
	int speed;  //keeps track of the speed the alien moves at.
	int directionX = 1; // keeps track of the direction along the x-axis the alien moves.
	int directionY = 1; // keeps track of the direction along the y-axis the alien moves.
	int growthChanger = -1;  //indicates which way (shrink (-) grow(+)) the alien's arms are moving.
	int PermanentDeath = 1; //keeps track of whether this specific alien is dead.  defaults to 1 for alive.
	
	public spinalien(int x, int y, int Size, int speed, int couleur1, int couleur2, int couleur3, int directionX, int directionY)
	{
		this.x = x;
		this.y = y;
		this.Size = Size;
		this.speed = speed;
		this.directionX = directionX;
		this.directionY = directionY;
		this.couleur1 = couleur1;
		this.couleur2 = couleur2;
		this.couleur3 = couleur3;
	}
	
	public boolean draw(Graphics g, int posX, int posY, boolean Up)
	{
		//detects if the mouse is on the alien.
		boolean clickedUp =
			posX >= x && posX < x + (Size*(growth*5/3)/(6-growth)) && posY >= y && posY < y + Size*3;
		
		g.setColor(new Color(couleur1,couleur2,couleur3)); //sets the color to a random color specified by couleur1, couleur2, and couleur3.
		g.fillOval(x, y-growth, Size, Size); // draws the alien head
		g.fillOval(x, y+Size-1, Size, Size*2); // draws the alien body.
		g.fillArc(x-Size*(growth/3)/(6-growth), y+(Size*4/3)+1, Size*growth, Size/2, -90, 180); //draws the alien's right hand.
		g.fillArc(x-Size*(growth*5/3)/(6-growth), y+(Size*4/3)+1, Size*growth, Size/2, 90, 180);  //alien's left hand.
		
		return clickedUp;
	}
	
	public void behavior(int x, int y, int w, int h, boolean dead, int speed, int NumberOfAliens, int AlienNumber)
	{
		if (AlienNumber >= NumberOfAliens)
			dead = true;
		
		//if the alien is dead, sets everything to 0 so it doesn't move and takes the alien off the screen so the user can't see it or click on it anymore.
		if (dead == true)
		{
			PermanentDeath = 0; //if the alien is clicked on, it dies and this value is set to 0.
			speed = 0;
			this.x = 1000;
			this.y = 1000;
		}
		
//------------------------------------------//
		//Changes the arm lengths//
		if (this.growth > 3)
			growthChanger = -1;
		
		if (this.growth < 0)
			growthChanger = 1;
//----------------------------------------//

//---------------------------------------//
		//changes the position of the alien//
		if (this.x + (Size*(growth*5/3)/(6-growth)) + Size >= x + w)
			directionX = -1;
		
		if (this.x - (Size*(growth*5/3)/(6-growth)) <= x)
			directionX = 1;
		
		if (this.y + Size*3 >= y + h)
			directionY = -1;
		
		if (this.y - 30 <= y)
			directionY = 1;
//--------------------------------------//

		this.growth += growthChanger;
		this.x += directionX*speed*PermanentDeath; //multiplies by PermanentDeath each frame so that if the alien is dead, it's multiplied by 0 therefore it doesn't move.
		this.y += directionY*speed*PermanentDeath;
	}
}