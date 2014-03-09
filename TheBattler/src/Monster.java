import java.awt.*;

public interface Monster
{
	Color DeepRed = new Color(220, 0, 0);
	Color YellowFingers = new Color(230, 230, 0);
	Color BlueEyes = new Color(0, 255, 255);
	Color FaceRed = new Color(255, 200, 200);
	Color GoblinGreen = new Color(50, 100, 50);
	Color GoblinRed = new Color(180, 0, 0);
	
	Font StatFont = new Font("Ariel", 1, 16);
	Font AttackFont = new Font("Ariel", 3, 30);
	
	public void drawMonster(Graphics g);
	public void drawStats(Graphics g);
	public boolean attackBehavior(Graphics g);
	public void reset();
}