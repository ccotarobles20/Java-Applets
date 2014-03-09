package board;

import java.awt.*;

import pieces.Piece;

import java.util.Random;

public class StatBoxes
{
	public final static int ORIGIN = 0;
	public final static int OPTIONS = 1;
	public final static int ITEMLIST = 2;
	public final static int ENEMYMOVE = 3;
	public final static int BATTLEOPTIONS = 4;
	public final static int STATSVIEW = 5;
	public final static int DAMAGESCREEN = 6;
	
	public final static int NOTHING = 0;
	public final static int BATTLE = 1;
	public final static int USEITEM = 2;
	public final static int WAIT = 3;
	public final static int EXIT = 4;
	public final static int ENDTURN = 5;
	public final static int MENU = 6;
	public final static int BATTLECHOICE = 7;
	public final static int STATS = 8;
	public final static int DAMAGE = 9;
	
	int x, y, size, text;
	boolean battleText;
	
	int item = 0;
	int firstItem = -1;
	int secondItem = -1;
	String itemName = "none";
	String usableItem1 = "none";
	String usableItem2 = "none";
	
	Random random = new Random();
	
	Color lightBlue = new Color(73, 94, 235);
	
	public StatBoxes(int x, int y, int size)
	{
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	public void draw(Graphics g, int text, boolean battleText, Board board, Piece piece)
	{
		this.text = text;
		this.battleText = battleText;
		g.setColor(Color.white);
		g.fillRoundRect(x, y, size*2-2, size*3, size/10, size/10);
		g.setColor(Color.black);
		g.drawRoundRect(x, y, size*2-2, size*3, size/10, size/10);
		
		switch(text)
		{
		case ORIGIN:
			g.drawString("Your Move!  Select an available unit.", x+5, y+15);
			
			g.setColor(lightBlue);
			g.fillRoundRect(x+size/2, y+size*5/2, size, size/4, size/10, size/10);
			g.setColor(Color.black);
			g.drawString("END TURN", x+size/2+15, y+size*5/2+15);
			
			break;
			
		case OPTIONS:
			g.drawString("Move your unit and choose an action below: ", x+5, y+15);
			//DRAWS OPTIONS FOR WHAT THE USER CAN DO (EXIT, WAIT, see STATS, or USE ITEM and if the User is next to an enemy then ATTACK).
			
			if (battleText)
			{
				g.setColor(lightBlue);
				g.fillRoundRect(x+size/2, y+size/2, size, size/4, size/10, size/10);
				g.setColor(Color.black);
				g.drawString("ATTACK", x+size/2+15, y+size/2+15);
			}
			
			g.setColor(lightBlue);
			g.fillRoundRect(x+size/2, y+size, size, size/4, size/10, size/10);
			g.setColor(Color.black);
			g.drawString("STATS", x+size/2+15, y+size+15);
			
			g.setColor(lightBlue);
			g.fillRoundRect(x+size/2, y+size*3/2, size, size/4, size/10, size/10);
			g.setColor(Color.black);
			g.drawString("USE ITEM", x+size/2+15, y+size*3/2+15);
			
			g.setColor(lightBlue);
			g.fillRoundRect(x+size/2, y+size*2, size, size/4, size/10, size/10);
			g.setColor(Color.black);
			g.drawString("WAIT", x+size/2+15, y+size*2+15);
			
			g.setColor(lightBlue);
			g.fillRoundRect(x+size/2, y+size*5/2, size, size/4, size/10, size/10);
			g.setColor(Color.black);
			g.drawString("EXIT", x+size/2+15, y+size*5/2+15);
			
			break;
			
		case ITEMLIST:
			g.drawString("Select an item to use.", x+5, y+15);
			
			//DRAWS THE ITEMS THAT EACH UNIT HAS.
			
			if (firstItem > 0)
			{
				g.setColor(lightBlue);
				g.fillRoundRect(x+size/2, y+size/2, size, size/4, size/10, size/10);
				g.setColor(Color.black);
				g.drawString(usableItem1, x+size/2+15, y+size/2+15);
			}
			
			if (secondItem > 0)
			{
				g.setColor(lightBlue);
				g.fillRoundRect(x+size/2, y+size, size, size/4, size/10, size/10);
				g.setColor(Color.black);
				g.drawString(usableItem2, x+size/2+15, y+size+15);
			}
			
			g.drawString("Used the " + itemName, x+size/2+15, y+3*size/2);
			
			g.setColor(lightBlue);
			g.fillRoundRect(x+size/2, y+5*size/2, size, size/4, size/10, size/10);
			g.setColor(Color.black);
			g.drawString("MENU", x+size/2+15, y+5*size/2+15);
			
			break;
			
		case ENEMYMOVE:
			g.drawString("Your enemy is moving...", x+5, y+15);
			break;
			
		case BATTLEOPTIONS:
			g.drawString("Click on the enemy you wish", x+5, y+15);
			g.drawString("to attack!!", x+5, y+30);
			break;
		
		case STATSVIEW:
			int spacing = 15;
			g.drawString("This unit's stats are:", x+5, y+spacing);
			g.drawString("LEVEL: " + piece.getLevel(), x+5, y+3*spacing);
			g.drawString("Health: " + piece.getHealth() + " / " + piece.getMaxHealth(), x+5, y+4*spacing);
			g.drawString("Exp.: " + piece.getExp() + " / " + piece.getlevelUpAmount(), x+5, y+5*spacing);
			g.drawString("Attack: " + piece.getAttack(), x+5, y+6*spacing);
			g.drawString("Defense: " + piece.getDefense(), x+5, y+7*spacing);
			
			g.setColor(lightBlue);
			g.fillRoundRect(x+size/2, y+5*size/2, size, size/4, size/10, size/10);
			g.setColor(Color.black);
			g.drawString("MENU", x+size/2+15, y+5*size/2+15);
			
			break;
			
		case DAMAGESCREEN:
			//NEED TO UPDATE THIS CODE SO THAT IT REFLECTS DAMAGE CORRECTLY DONE TO OPPONENT WHEN OPPONENT ATTACKS.
			spacing = 15;
			
			if (board.getTurn() == Board.HUMAN)
			{
				Piece Defender = board.getLastEnemy();
				Piece Attacker = board.getLastHuman();
								
				g.drawString("You attacked! You did " + board.getYourDamage() + " damage.", x+5, y+3*spacing);
				if (Defender.getHealth()>0)
				{
					g.drawString("Enemy attacked! Enemy did " + board.getComputerDamage() + " damage.", x+5, y+4*spacing);
					if (Attacker.getHealth()<=0)
					{
						g.drawString("You died!", x+5, y+5*spacing);
					}
				}
				else
				{
					g.drawString("Enemy died!", x+5, y+4*spacing);
				}
				
				if (Attacker.getHealth()>0)
				{
					g.drawString("You gained " + board.getExpGain() + " experience points!", x+5, y+6*spacing);
				}
				
				if (Attacker.getLevelText())
				{
					g.drawString("Congratulations, you just leveled up!", x+5, y+8*spacing);
					g.drawString("Your stats have increased: ", x+5, y+9*spacing);
					g.drawString("LEVEL: " + Attacker.getLevel(), x+5, y+11*spacing);
					g.drawString("Health: " + Attacker.getHealth() + " / " + Attacker.getMaxHealth(), x+5, y+12*spacing);
					g.drawString("Exp.: " + Attacker.getExp() + " / " + Attacker.getlevelUpAmount(), x+5, y+13*spacing);
					g.drawString("Attack: " + Attacker.getAttack(), x+5, y+14*spacing);
					g.drawString("Defense: " + Attacker.getDefense(), x+5, y+15*spacing);
				}
			}
			else if (board.getTurn() == Board.COMPUTER)
			{
				Piece Attacker = board.getLastHuman();
				Piece Defender = board.getLastEnemy();
				
				g.drawString("Enemy attacked! Enemy did " + board.getYourDamage() + " damage.", x+5, y+3*spacing);
				if (Defender.getHealth()>0)
				{
					g.drawString("You attacked! You did " + board.getComputerDamage() + " damage.", x+5, y+4*spacing);
					if (Attacker.getHealth()<=0)
					{
						g.drawString("Enemy died!", x+5, y+5*spacing);
					}
				}
				else
				{
					g.drawString("You died!", x+5, y+4*spacing);
				}
				
				if (Defender.getHealth()>0)
				{
					g.drawString("You gained " + board.getExpGain() + " experience points!", x+5, y+6*spacing);
				}
				
				if (Defender.getLevelText())
				{
					g.drawString("Congratulations, you just leveled up!", x+5, y+8*spacing);
					g.drawString("Your stats have increased: ", x+5, y+9*spacing);
					g.drawString("LEVEL: " + Defender.getLevel(), x+5, y+11*spacing);
					g.drawString("Health: " + Defender.getHealth() + " / " + Defender.getMaxHealth(), x+5, y+12*spacing);
					g.drawString("Exp.: " + Defender.getExp() + " / " + Defender.getlevelUpAmount(), x+5, y+13*spacing);
					g.drawString("Attack: " + Defender.getAttack(), x+5, y+14*spacing);
					g.drawString("Defense: " + Defender.getDefense(), x+5, y+15*spacing);
				}
			}			
			g.setColor(lightBlue);
			g.fillRoundRect(x+size/2, y+5*size/2, size, size/4, size/10, size/10);
			g.setColor(Color.black);
			g.drawString("CONTINUE", x+size/2+15, y+5*size/2+15);
		}
	}
	
	public int behavior(int mouseX, int mouseY, int firstItem, int secondItem, int totalItems, Piece piece)
	{
		this.firstItem = firstItem;
		this.secondItem = secondItem;
		switch(text)
		{
		case ORIGIN:
			if (mouseX > x+size/2 && mouseX < x+3*size/2 && mouseY > y+size*5/2 && mouseY < y+size*11/4)
			{
				return ENDTURN;
			}

		case OPTIONS:
			if (mouseX > x+size/2 && mouseX < x+3*size/2 && mouseY > y+size/2 && mouseY < y+size*3/4 && battleText)
			{
				return BATTLECHOICE;
			}
			if (mouseX > x+size/2 && mouseX < x+3*size/2 && mouseY > y+size && mouseY < y+size*5/4)
			{
				return STATS;
			}
			if (mouseX > x+size/2 && mouseX < x+3*size/2 && mouseY > y+size*3/2 && mouseY < y+size*7/4)
			{
				return USEITEM;
			}
			if (mouseX > x+size/2 && mouseX < x+3*size/2 && mouseY > y+size*2 && mouseY < y+size*9/4)
			{
				return WAIT;
			}
			if (mouseX > x+size/2 && mouseX < x+3*size/2 && mouseY > y+size*5/2 && mouseY < y+size*11/4)
			{
				return EXIT;
			}
			
		case ITEMLIST:
			//GIVES THE ABILITY TO CLICK ON AND USE ITEMS
			if (mouseX > x+size/2 && mouseX < x+3*size/2 && mouseY > y+size/2 && mouseY < y+size*3/4 && firstItem > 0)
			{
				item = firstItem;
			}
			if (mouseX > x+size/2 && mouseX < x+3*size/2 && mouseY > y+size && mouseY < y+size*5/4 && secondItem > 0)
			{
				item = secondItem;
			}
			if (mouseX > x+size/2 && mouseX < x+3*size/2 && mouseY > y+size*5/2 && mouseY < y+size*11/4)
			{
				return MENU;
			}
			
			switch(item)
			{
			case Piece.SmPOTION:
				piece.setHealth(5);
				itemName = "Small Potion";
				break;
				
			case Piece.TORTOISESHELL:
				piece.setAddDefense(1);
				itemName = "Tortoise Shell";
				break;
				
			case Piece.HEADSTART:
				piece.setAddMove(2);
				itemName = "Head Start";
				break;
			}
			
			switch(firstItem)
			{
			case Piece.SmPOTION:
				usableItem1 = "Small Potion";
				break;
				
			case Piece.TORTOISESHELL:
				usableItem1 = "Tortoise Shell";
				break;
				
			case Piece.HEADSTART:
				usableItem1 = "Head Start";
				break;
			}
			
			switch(secondItem)
			{
			case Piece.SmPOTION:
				usableItem2 = "Small Potion";
				break;
				
			case Piece.TORTOISESHELL:
				usableItem2 = "Tortoise Shell";
				break;
				
			case Piece.HEADSTART:
				usableItem2 = "Head Start";
				break;
			}

			piece.deleteItem(item);
			item = 0;
		
		case STATSVIEW:
			if (mouseX > x+size/2 && mouseX < x+3*size/2 && mouseY > y+size*5/2 && mouseY < y+size*11/4)
			{
				return MENU;
			}
		
		case DAMAGESCREEN:
			if (mouseX > x+size/2 && mouseX < x+3*size/2 && mouseY > y+size*5/2 && mouseY < y+size*11/4)
			{
				return DAMAGE;
			}
		}
		return NOTHING;
	}
}