import java.awt.*;

public class MouseShooter
{
	boolean runAnimation = false;
	boolean clickedUp = false;
	int Shrink = 0;
	
	public void ShouldDraw(Graphics g, boolean clickedUp, int mouseX, int mouseY)
	{
		if (clickedUp == true)
			runAnimation = true;
		
		if (runAnimation == true)
		{
			int CircSize = 30 - Shrink;
			Shrink += 5;
			g.drawOval(mouseX-(CircSize/2), mouseY-(CircSize/2), CircSize, CircSize);
			if (Shrink == 50)
			{
				Shrink = 0;
				runAnimation = false;
			}
		}
	}
}
