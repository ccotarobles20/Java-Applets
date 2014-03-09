import java.awt.*;

public class TestSliders
{
	int posX = 0;
	int posY = 0;
	int ovalX, ovalY, w, h;
	
	public TestSliders(int posX, int posY)
	{
		this.posX = posX;
		this.posY = posY;
		this.ovalX = posX;
		this.ovalY = posY;
		this.w = 200;
		this.h = 80;
	}
	
	public void DrawSlider(Graphics g)
	{
		g.setColor(Color.gray);
		g.fillRect(posX, posY, w, h);
		g.setColor(Color.red);
		g.fillOval(ovalX, ovalY, w/3, h);
	}
	
	public int behavior(boolean mouseDown, int mouseX, int mouseY)
	{
		boolean mouseOver =
				mouseX >= posX && mouseX < posX + w && mouseY >= posY && mouseY < posY + h;
		
		if (mouseDown && mouseOver)
		{
			ovalX = mouseX - w/6;
			if (ovalX > posX+w - w/3)
			{
				ovalX = posX + w - w/3;
			}
			
			if (ovalX < posX)
			{
				ovalX = posX;
			}
		}
		
		return (ovalX - posX)/14 + 1;
	}
}
