import java.awt.*;
import java.text.DecimalFormat;

public class Timer
{
	double time = 0;
	Font TimerFont = new Font("Times New Roman", 0, 18);                      //Font for the Timer.
	
	public double RunTimer(int currentAliens)
	{
		if (currentAliens > 0)
		{
			time += .03;
		}
		
		//Changes the time counter so that it rounds to two decimal places.
		//credit: http://www.java-forums.org/advanced-java/4130-rounding-double-two-decimal-places.html
		DecimalFormat twodigits = new DecimalFormat("#.##");
		time = Double.valueOf(twodigits.format(time));
		
		return time;
	}
	
	public void DrawTimer(Graphics g, int w, int h)
	{
		g.setFont(TimerFont);
		g.drawString(Double.toString(time), w-50, h);  //draws a string that shows the amount of time that's passed in the bottom right-hand corner.
	}
}
