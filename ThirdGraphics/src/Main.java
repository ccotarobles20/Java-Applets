import java.awt.*;

public class Main extends BufferedApplet
{
	Font MainFont = new Font("Helvetica", 1, 24);                             //Sets up the Main Font we'll use throughout the program.
	Font WinnerFont = new Font("Windings", 0, 40);                            //Winner Font for the end of the program.
	Font StatsFont = new Font("Helvetica", 0, 14);                            // Font for Stats at End of the Game.
	Font SliderFont = new Font("Helvetica", 0, 12);                           // Font for the Sliders.
	String text = "Destroy the spinning aliens by clicking on them!!";        //Instructions for the user.
	int NumberOfAliens = 10;                                                  //Number of aliens we start with.
	int currentAliens = NumberOfAliens;                                       //Number of aliens currently on the screen.
	int speed = 2;                                                            //the starting speed for the aliens.
	boolean TitlePage = true;
	int InitialSpeed = speed;
	
	spinalien[] create = new spinalien[10];
	{
		for (int i = 0 ; i < 10 ; i++)
			create[i] = new spinalien(500-50*i, 50*i, 20, speed, 210-20*i, 10*i, 0, 1, 1);
	}
	
	MouseShooter click = new MouseShooter();
	Timer time = new Timer();
	TestSliders MySlider1 = new TestSliders(150, 150);	
	TestSliders MySlider2 = new TestSliders(150, 300);
	
	public void render(Graphics g)
	{
		int w = getWidth();
		int h = getHeight();
		
		g.setColor(Color.white);
		g.fillRect(0, 0, w, h);
		
		boolean clickedUp = wasMouseDown && ! mouseDown;
		
		if (keyDown[' '])
		{
			TitlePage = false;
		}
		
		if (TitlePage == true)
		{
			MySlider1.DrawSlider(g);
			MySlider2.DrawSlider(g);
			NumberOfAliens = MySlider1.behavior(mouseDown, mouseX, mouseY);
			speed = MySlider2.behavior(mouseDown, mouseX, mouseY);
			
			g.setFont(MainFont);
			g.setColor(Color.black);
			g.drawString("Drag each slider to choose the number of aliens", 5, 25);
			g.drawString("and their initial speed before starting.", 5, 50);
			g.setFont(SliderFont);
			g.drawString(" (hit the 'space' bar to begin the game)", 150, 80);
			g.drawString("Number Of Aliens: " + Integer.toString(NumberOfAliens), 150, 130);
			g.drawString("Starting Speed of Aliens: " + Integer.toString(speed), 150, 280);
			
			currentAliens = NumberOfAliens;
			InitialSpeed = speed;
		}
		
		if (TitlePage == false)
		{
			g.setColor(Color.black);
			g.setFont(MainFont);
			g.drawString(text, 5, 25);
		
			click.ShouldDraw(g, clickedUp, mouseX, mouseY);
		
			double PassedTime = time.RunTimer(currentAliens);
			time.DrawTimer(g, w, h);
			
			for (int i = 0 ; i < create.length ; i++)
			{
				boolean overObject = create[i].draw(g, mouseX, mouseY, clickedUp);
				create[i].behavior(0, 0, w, h, killAlien(clickedUp, overObject), speed, NumberOfAliens, i);
			}
			
			// When the Aliens have all been destroyed, run this code to tell the user the game's over:
			if (currentAliens == 0)
			{
				g.setColor(Color.red);
				g.setFont(WinnerFont);
				g.drawString("Congratulations, YOU WON!!!", 20, h/2);
				g.setColor(Color.black);
				g.setFont(StatsFont);
				g.drawString("Completed in " + PassedTime + " seconds.", w/2-250, h/2+30);
				g.drawString("Killed " + NumberOfAliens + " aliens total.", w/2-250, h/2+60);
				g.drawString("Aliens moved at a minimum speed of " + InitialSpeed + " and a maximum speed of " + speed + " .", w/2-250, h/2+90);
				g.drawString("Refresh your browser to play again.", w/2-250, h/2+120);
			}
		}
		
		animating = true;
	}
	
	//tells the program if an Alien's been killed.  If so, reduces currentAliens by 1 and returns a true to spinalien's behavior function.
	public boolean killAlien(boolean clickedUp, boolean overObject)
	{
		if (clickedUp == true && overObject)
		{
			currentAliens -= 1;
			speed += 1;  //increases the alien speed each time one dies.
			return true;
		}
		else
			return false;
	}
}