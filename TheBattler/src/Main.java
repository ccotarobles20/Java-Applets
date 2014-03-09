import java.awt.*;

/*
Since I have so many different classes, I'm starting a list here in the Main class to keep track of them all and what their basic functions are:

Main: Runs the program, creates instances of the basic classes and runs their functions.
MainCharacter: The Main Character as seen on the starting screen, is able to walk around.
global: A class that holds the Main Character's stats (health, fullhealth, attack, defense, speed).
BufferedApplet: The BufferedApplet provided for our use.

EnemyFinder: An interface that enables the Main Character to encounter Enemies.
Grass: An class that implements the EnemyFinder interface, meaning you are able to find Enemies in the grass.

CreateBattle: A class that, once the Main Character encounters an enemy, creates the battle screen.
Enemy: An interface that draws the enemy on the battle screen.
goblin: A class that implements the Enemy interface; a type of enemy.

 */

public class Main extends BufferedApplet
{
	boolean up = false, down = false, right = false, left = false; //whether the main character is moving
	//x and y coordinates of the Main Character
	int x = 100;
	int y = 100;
	
	boolean Battle = false; //determines if there should be a battle.
	boolean AttackChosen = false; //determines if, during a battle, the player has chosen their attack.
	boolean EnemyAttack = false; //determines if, during a battle, the enemy is ready to attack.
	
	boolean StartUp = true; //determines whether to draw the instructions
	//x and y coordinates of instructions
	int Sx = 200;
	int Sy = 50;
	
	Font MainFont = new Font("Times New Roman", 0, 16);
	
	MainCharacter Rezin = new MainCharacter(x, y, 20);
	CreateBattle BattleScreen = new CreateBattle();
	
	Grass[] CreateGrass = new Grass[100];
	// I tried putting this code in a constructor, but for some reason I couldn't get it to work.  It wouldn't pass the objects into the render code to draw them.
	{
		int k = 0;
		for (int j = 0; j < 10; j++)
		{
			for (int i = 0; i < 10; i++)
			{
				int size = 2;
				CreateGrass[k] = new Grass(200+size*10*i, 200+size*12*j, size);
				k++;
			}
		}
	}
	
	Mud[] CreateMud = new Mud[100];
	// I tried putting this code in a constructor, but for some reason I couldn't get it to work.  It wouldn't pass the objects into the render code to draw them.
	{
		int k = 0;
		for (int j = 0; j < 10; j++)
		{
			for (int i = 0; i < 10; i++)
			{
				int size = 2;
				CreateMud[k] = new Mud(500+size*12*i, 300+size*10*j, size);
				k++;
			}
		}
	}
	
	public void render(Graphics g)
	{
		int w = getWidth();
		int h = getHeight();
		
		g.setColor(Color.white);
		g.fillRect(0, 0, w, h);
		
		CharacterMove();
		
		if (keyDown['j'])
		{
			Battle = false;
		}
		
		// Start Up Code, tells the user the instructions and how to play the game:
		if (StartUp == true)
		{
			Instructions(g);
		}
		
		if (Battle == false)
		{
			DrawGrassAndBattle(g);
			Rezin.draw(g);
			Rezin.behavior(g, h, w, up, down, right, left);
		}
		
		if (Battle == true)
		{
			BattleScreens();
			BattleScreen.draw(g, AttackChosen, EnemyAttack);
			Battle = BattleScreen.behavior(g, down && !wasKeyDown['s'], AttackChosen, EnemyAttack);
		}
		
		up = false;
		down = false;
		right = false;
		left = false;
		
		animating = true;
	}
	
	public void Instructions(Graphics g)
	{
		g.setFont(MainFont);
		g.setColor(Color.black);
		g.drawString("Welcome to Monster Battles!", Sx, Sy);
		g.drawString("If this is your first time playing, here's what you need to know:", Sx, Sy+20);
		g.drawString("Your name is Rezin, and the world is being invaded by goblins.", Sx, Sy+40);
		g.drawString("Your goal is to stop this invasion by killing as many goblins as possible.", Sx, Sy+60);
		g.drawString("Walk around in the grass using the (a, w, s, d) keys and you will eventually encounter a goblin to fight.", Sx, Sy+80);
		g.drawString("Kill the goblin, and repeat this process.", Sx, Sy+100);
		g.drawString("Good luck on your quest, and click the mouse to begin.", Sx, Sy+120);
		if (mouseDown) //When the user clicks, the Start Up Instructions disappear.
		{
			StartUp = false;
		}
	}
	
	public void BattleScreens()
	{
		if (!keyDown[' '] && wasKeyDown[' '])
		{
			//In the Battle, alternates between your attack, the enemy's attack, and you choosing an attack each time you hit the space bar, depending on whether you've
			//you've attacked (AttackChosen) and whether the enemy has attacked (EnemyAttack).
			if (AttackChosen == false && EnemyAttack == false)
			{
				AttackChosen = true;
			}
			
			else if (AttackChosen == true && EnemyAttack == false)
			{
				EnemyAttack = true;
			}
			
			else if (AttackChosen == true && EnemyAttack == true)
			{
				AttackChosen = false;
				EnemyAttack = false;
			}
		}
	}
	
	public void DrawGrassAndBattle(Graphics g)
	{
		Rectangle Player = new Rectangle(Rezin.x, Rezin.y, Rezin.characterW, Rezin.characterH); //Creates the Rectangle for the Main Character.
		
		//For each instance of grass, draws it at a new location and sets Battle to either true or false depending on if the Main Character is in the grass (using Rectangles)
		for (int i = 0; i < CreateGrass.length && Battle == false; i++)
		{
			CreateGrass[i].draw(g);
			Battle = CreateGrass[i].detect(g, Player);
		}
		
		//For each instance of mud, draws it at a new location and sets Battle to either true or false depending on if the Main Character is in the grass (using Rectangles)
		for (int i = 0; i < CreateMud.length && Battle == false; i++)
		{
			CreateMud[i].draw(g);
			Battle = CreateMud[i].detect(g, Player);
		}
	}
	
	public void CharacterMove()
	{
		//Detects whether the (w, s, a, d) keys are being pressed and stores them in boolean values for easy access.
		if (keyDown['w'])
		{
			up = true;
		}
		
		if (keyDown['s'])
		{
			down = true;
		}
		
		if (keyDown['d'])
		{
			right = true;
		}
		
		if (keyDown['a'])
		{
			left = true;
		}
	}
}
