package startPack;


import java.awt.*;

import board.Board;
import board.Board.BasicMenuButtons;

import pieces.Piece;
import pieces.Object;

import board.StatBoxes;

public class Game2 extends BufferedApplet
{	
	int w = 0, h = 0;
	int boardSize = 20;
	Board board = new Board(boardSize, this);
	BasicMenuButtons basicmenubuttons = board.new BasicMenuButtons();
	Color Green = new Color(16, 72, 13);
	
	Piece Battler; //Used for all enemy/temporary pieces.
	
	Piece Steven;
	Piece Suzanne;
	Piece Rachel;
	Piece John;
	
	Object Map;
	int HumanUnits = 0;
	int ComputerUnits = 0;
	
	Piece lastClicked; //Stores the last piece to be clicked On.
	
	StatBoxes stats;
	int clickOn = 0;
	
	int statText = StatBoxes.ORIGIN;
	boolean battleText = false; //variable which tells the computer if the user is able to battle or not.
	boolean moving = false;  //variable which tell the computer if the user is moving their piece or not.
		
	Piece piece;  //another instance of piece, this is used for storing the next-to-last piece you clicked on.
	
	boolean DamageScreen = false;
	
	int Level = 0;
	
	public Game2()
	{
	}

	public void render(Graphics g)
	{
		// INITIALIZE THINGS THE FIRST TIME THROUGH

		if (w == 0)
		{
			w = getWidth();
			h = getHeight();
			board.setWidth(h);
			Level += 1;
			int startX = boardSize*board.getSquareWidth();
			int boxSize = (w-startX)/2;
			stats = new StatBoxes(startX, 5, boxSize);
			board.clear(boardSize);
			
			//CREATE THE UNITS:
			if (Level==1)
			{
				Level1();
			}
			else if (Level==2)
			{
				Level2();
			}
			
			lastClicked = Battler;
			board.setHumanUnits(HumanUnits);
			board.setComputerUnits(ComputerUnits);
		}

		g.setColor(Green);
		g.fillRect(0, 0, w, h);
		
		statText = board.getstatText();
		battleText = board.getBattleText();
		moving = board.getMoving();

		if (statText == StatBoxes.DAMAGESCREEN)
		{
			DamageScreen = true;
		}
		else
		{
			DamageScreen = false;
		}
		
		//---------------------CHEATS--------------------------//
		if (!keyDown['s'] && wasKeyDown['s'])
		{
			w = 0;
		}
		//----------------------------------------------------//
		
		//board.update(g, mouseX, mouseY, DamageScreen);
		respondToMouse(g);
		
		stats.draw(g, statText, battleText, board, lastClicked);
		
		g.drawImage(drawLord, 5, 5, 100, 100, this);

		if (Level>=2)
		{
   			g.drawString("This level is not yet complete!", 150, 250);
   			g.drawString("Please wait until I've had more time to update the game.", 150, 270);
		}
		
		animating = true;
	}

	private void respondToMouse(Graphics g)
	{
		int s = board.getSquareWidth();
		int mCol = mouseX / s;
		int mRow = mouseY / s;
		
		// MOUSE PRESSED
		
		if (!wasMouseDown && mouseDown)
		{
			lastClicked.setLevelText(false);
			if (board.getComputerUnits()==0)
			{
				w=0;
				board.setstatText(StatBoxes.ORIGIN);
			}
			
			if (board.getTurn() == Board.HUMAN)
			{
				if (piece != null && piece.getTeam() == Piece.HUMAN)
				{
					lastClicked = piece;
				}
				piece = board.getPiece(mRow, mCol);
				
				if (moving)
				{
					basicmenubuttons.moving(mRow, mCol);
				}
				else
				{
					basicmenubuttons.begin(mRow, mCol);
				}
			}
			
			clickOn = stats.behavior(mouseX, mouseY, lastClicked.getFirstItem(), lastClicked.getSecondItem(), Piece.totalitems, lastClicked);

			//BLOCK OF CODE WHICH TELLS THE COMPUTER WHICH MENU SCREEN TO GO TO, BASED ON WHAT THE USER CLICKS ON AS STORED IN THE VARIABLE CLICKON.

			if (clickOn == StatBoxes.ENDTURN)
			{
				board.unselectPieces();
			}
			else if (clickOn == StatBoxes.USEITEM)
			{
				board.setstatText(StatBoxes.ITEMLIST);
			}
			else if (clickOn == StatBoxes.WAIT)
			{
				basicmenubuttons.selected();
			}
			else if (clickOn == StatBoxes.EXIT)
			{
				basicmenubuttons.exit(mRow, mCol);
			}
			else if (clickOn == StatBoxes.MENU)
			{
				board.setstatText(StatBoxes.OPTIONS);
			}
			else if (clickOn == StatBoxes.BATTLECHOICE)
			{
				board.setstatText(StatBoxes.BATTLEOPTIONS);
			}
			else if (clickOn == StatBoxes.STATS)
			{
				board.setstatText(StatBoxes.STATSVIEW);
			}
			else if (clickOn == StatBoxes.DAMAGE)
			{
				if (board.getTurn()==Board.HUMAN)
				{
					board.setstatText(StatBoxes.ORIGIN);
				}
				else if (board.getTurn()==Board.COMPUTER)
				{
					board.setstatText(StatBoxes.ENEMYMOVE);
				}
			}
			
			board.damage(piece, lastClicked, Piece.COMPUTER);
		}
	}
	
	private void Level2()
	{
		Steven.setPosition(19, 3);
		Suzanne.setPosition(19, 5);
		Rachel.setPosition(17, 4);
		John.setPosition(18, 2);
		HumanUnits = 4;	
	}
	
	private void Level1()
	{
		Steven = new Piece(drawLord, Piece.LORD, Piece.HUMAN, board, 18, 3);
		Suzanne = new Piece(drawSwordsman, Piece.SWORDSMAN, Piece.HUMAN, board, 17, 4);
		Rachel = new Piece(drawSwordsman, Piece.SWORDSMAN, Piece.HUMAN, board, 17, 2);
		John = new Piece(drawArcher, Piece.ARCHER, Piece.HUMAN, board, 18, 5);
		HumanUnits = 4;

		Battler = new Piece(drawEnemyDemon, Piece.DEMONBAT, Piece.COMPUTER, board, 2, 13);
		Battler = new Piece(drawEnemyDemon, Piece.DEMONBAT, Piece.COMPUTER, board, 2, 10);
		Battler = new Piece(drawEnemySwordsman, Piece.GOBLIN, Piece.COMPUTER, board, 10, 5);
		Battler = new Piece(drawEnemySwordsman, Piece.GOBLIN, Piece.COMPUTER, board, 10, 8);
		Battler = new Piece(drawEnemySwordsman, Piece.GOBLIN, Piece.COMPUTER, board, 6, 14);
		Battler = new Piece(drawEnemySwordsman, Piece.GOBLIN, Piece.COMPUTER, board, 4, 18);
		Battler = new Piece(drawEnemySwordsman, Piece.GOBLIN, Piece.COMPUTER, board, 4, 6);
		Battler = new Piece(drawEnemySwordsman, Piece.GOBLIN, Piece.COMPUTER, board, 7, 2);
		ComputerUnits = 8;
				
		for (int i=0;i<16;i++)
		{
			if (i==5)
			{
				i+=2;
			}
			Map = new Object(fence, board, 15, 2+i, 10);
		}

		for (int i=0;i<16;i++)
			for (int j=0;j<3;j++)
			{
				if (i==6)
				{
					i+=6;
				}
				Map = new Object(mountains, board, 1+j, 3+i, 6);
			}
		
		for (int i=0; i<2; i++)
			for (int j=0; j<4; j++)
			{
				Map = new Object(sandbox, board, 6+i, 3+j, 2);
				Map = new Object(sandbox, board, 12+i, 15+j, 2);
				Map = new Object(sandbox, board, 13+i, 0+j, 2);
				Map = new Object(sandbox, board, 4+i, 10+j, 2);
			}
	}
}