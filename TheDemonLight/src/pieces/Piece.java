package pieces;

import java.awt.*;

import board.Board;

import java.util.Random;


public class Piece
{
	public final static int LORD = 0;
	public final static int ARCHER = 1;
	public final static int SWORDSMAN = 2;
	public final static int GOBLIN = 3;
	public final static int DEMONBAT = 4;
	
	public final static int HUMAN = 0;
	public final static int COMPUTER = 1;
	public final static int DEAD = 3;
	
	public final static int NullItem = 0;
	public final static int SmPOTION = 1;
	public final static int HEADSTART = 2;
	public final static int TORTOISESHELL = 3;
	public static int totalitems = 4;
	
	Color color = new Color(0, 0, 255);
	Color originColor = new Color(0, 0, 255);
	Color fadeColor = new Color(0, 0, 200);
	
	Color HumanColor = Color.blue;
	Color HumanFade = new Color(22, 17, 85);
	Color ComputerColor = Color.red;
	Color ComputerFade = new Color(125, 32, 4);
	Color DeadColor = new Color(94, 94, 94);
	
	int row = -1, col = -1, heading = 0;
	int type = SWORDSMAN; int team = HUMAN;
	Board board;
	int time = 0;
	
	Random random = new Random();
	
	//STATS, these are actually reset depending on the type of unit.
	int move = 3;
	int health = 10;
	int attack = 5;
	int defense = 3;
	int maxHealth = 10;
	
	//These stats start at the same value for every unit.
	int exp = 0;
	int level = 1;
	int levelUpAmount = 25;
	boolean levelText = false;
	boolean movable = true; //tells the program if the piece can be moved or not.
	
	int maxItems = 2;
	boolean[] allItems = new boolean[totalitems];
	int[] items;
	int count = 0;
	
	//Types of pieces
	
	java.awt.Image picture;

	public Piece(java.awt.Image picture, int type, int team, Board board, int row, int col)
	{
		if (board.getPiece(row, col)!=null || board.getObject(row, col)!=null)
		{
			System.err.println("Multiple units have placed on the square ("+row+", "+col+").");
		}
				
		this.type = type;
		this.team = team;
		this.board = board;
		this.row = row;
		this.col = col;
		this.picture = picture;
		
		setPosition(row, col);
		
		for (int i = 0; i < totalitems; i++)
		{
			allItems[i] = false;
		}
		
		switch(team)
		{
		case HUMAN:
			originColor = HumanColor;
			fadeColor = HumanFade;
			break;
			
		case COMPUTER:
			originColor = ComputerColor;
			fadeColor = ComputerFade;
			movable = false;
			break;
		}
				
		color = originColor;
		
		
		//AT THE START, sets all Stats differently depending on what the piece's type is.
		switch(type)
		{
		case SWORDSMAN:
			move = Swordsman.move;
			allItems[SmPOTION] = true;
			health = Swordsman.health;
			attack = Swordsman.attack;
			defense = Swordsman.defense;
			break;
			
		case LORD:
			move = Lord.move;
			allItems[SmPOTION] = true;
			allItems[TORTOISESHELL] = true;
			health = Lord.health;
			attack = Lord.attack;
			defense = Lord.defense;
			break;
			
		case ARCHER:
			move = Archer.move;
			allItems[SmPOTION] = true;
			health = Archer.health;
			attack = Archer.attack;
			defense = Archer.defense;
			break;
		
		case GOBLIN:
			move = Goblin.move;
			health = Goblin.health;
			attack = Goblin.attack;
			defense = Goblin.defense;
			break;
		
		case DEMONBAT:
			move = DemonBat.move;
			health = DemonBat.health;
			attack = DemonBat.attack;
			defense = DemonBat.defense;
			break;
		}
		
		maxHealth = health;  //user can never go over maxHealth/the starting health value.
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	public int getMove()
	{
		return move;
	}
	
	public void setMove(int move)
	{
		this.move = move;
	}

	public int getType()
	{
		return type;
	}
	
	public int getTeam()
	{
		return team;
	}
	
	public void setMovable(boolean movable)
	{
		this.movable = movable;
	}
	
	public boolean getMovable()
	{
		return movable;
	}
	
	public int getFirstItem()
	{
		return items[0];
	}
	
	public int getSecondItem()
	{
		return items[1];
	}
	
	public void setHealth(int damage)
	{
		health += damage;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public int getMaxHealth()
	{
		return maxHealth;
	}
	
	public int getAttack()
	{
		return attack;
	}
	
	public int getDefense()
	{
		return defense;
	}
	
	public void setAddDefense(int add)
	{
		defense += add;
	}
	
	public void setAddMove(int add)
	{
		move += add;
	}
	
	public void deleteItem(int item)
	{
		allItems[item] = false;
	}
	
	public void setExp(int change)
	{
		exp += change;
	}
	
	public int getExp()
	{
		return exp;
	}
	
	public int getlevelUpAmount()
	{
		return levelUpAmount;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public boolean getLevelText()
	{
		return levelText;
	}
	
	public void setLevelText(boolean levelText)
	{
		this.levelText = levelText;
	}

	public boolean setPosition(int row, int col)
	{
		setMovable(true);
		if (board.isValid(row, col) && board.getPiece(row, col) == null)
		{
			board.setPiece(this.row, this.col, null);
			this.row = row;
			this.col = col;
			board.setPiece(row, col, this);
			return true;
		}
		return false;
	}
	
	public void update(Graphics g)
	{	
		int s = board.getSquareWidth();
		int x = s * col;
		int y = s * row;
		
		// BEHAVIOR
		
		items = new int[maxItems];
		count = 0;
		//Depending on which items the user has, sets the item's value of firstItem and secondItem accordingly.
		for (int i = 0; i < totalitems; i++)
		{
			if (allItems[i] == true)
			{
				items[count] = i;
				count++;
			}
		}
		
		//Depending on if the piece has been moved or not, changes the color of the piece.
		if (!movable)
		{
			color = fadeColor;
		}

		if (movable)
		{
			color = originColor;
		}
		
		if (health > maxHealth)
		{
			health = maxHealth;
		}
		
		if (exp >= levelUpAmount)
		{
			exp -= levelUpAmount;
			level += 1;
			levelText = true;
			attack += 1+random.nextInt(2);
			defense += 1+random.nextInt(2);
			int healthAdd = 2+random.nextInt(3);
			health += healthAdd;
			maxHealth += healthAdd;
		}
		
		//Destroys the unit by making it of team and type dead when its health is less than or equal to 0.
		if (health <= 0)
		{
			switch(team)
			{
			case HUMAN:
				board.subtractHumanUnits(1);
				break;
			case COMPUTER:
				board.subtractComputerUnits(1);
				break;
			}
			team = DEAD;
			type = DEAD;
			color = Color.gray;
		}

		// DRAWING
		if (health > 0)
		{
			g.drawImage(picture, x, y, s, s, color, null);
			g.drawString(""+health, x, y);
		}
		else
		{
			g.setColor(DeadColor);
			g.drawOval(x, y, s, s);
		}
	}
}