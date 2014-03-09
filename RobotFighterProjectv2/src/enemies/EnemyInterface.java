package enemies;
import java.awt.*;

public interface EnemyInterface
{
	int speed = 2;
	
	public Rectangle collision();
	public void draw(Graphics g);
	public void behavior(int w, int h, boolean Dead);
}
