import java.awt.*;

public class CreateBattle
{
	int x = 100;
	int y = 350;
	int Ex = 600;
	int Ey = 300;
	int size = 150;
	int Area = 1;
	
	int TimeToBattle = 35;
	int CountToBattle = 0;
	
	boolean GoblinDead = false;
	boolean RezinDead = false;
	
	Font BattleFont = new Font("Helvetica", 1, 40);
	
	MainCharacterBattleMode Rezin = new MainCharacterBattleMode(x, y, size);
	goblin Enemy = new goblin(x, y, size);
	
	public CreateBattle()
	{
	}
	
	public void draw(Graphics g, boolean AttackChosen, boolean EnemyAttack)
	{
		g.setFont(BattleFont);
		g.setColor(Color.blue);
		g.drawString("BATTLE TIME!!", 300, 100);
		
		CountToBattle += 1;
		if (CountToBattle > TimeToBattle)
		{
			Rezin.drawMonster(g);
			Rezin.drawStats(g);
			Enemy.drawStats(g);
			Enemy.drawMonster(g);
		}
	}
	
	public boolean behavior(Graphics g, boolean down, boolean AttackChosen, boolean EnemyAttack)
	{
		if (CountToBattle > TimeToBattle)
		{
			Rezin.drawAttacks(g, AttackChosen, down);
		}
		if (AttackChosen && !EnemyAttack && !GoblinDead)
		{
			RezinDead = Rezin.attackBehavior(g);
		}
		
		if (RezinDead)
		{
			AttackChosen = false;
			EnemyAttack = false;
			g.setColor(Color.white);
			g.fillRect(0, 0, 1000, 600);
			g.setColor(Color.black);
			g.drawString("GAME OVER", 400, 300);
			g.drawString("Refresh the Page to Play Again", 320, 320);
		}
		
		if (!AttackChosen && GoblinDead)
		{
			goblin.Health = goblin.FullHealth;
			GoblinDead = false;
			return false;
		}
		
		if (EnemyAttack)
		{
			GoblinDead = Enemy.attackBehavior(g);
		}
		
		if (!AttackChosen && !EnemyAttack)
		{
			Rezin.reset();
			Enemy.reset();
		}
		return true;
	}
}
