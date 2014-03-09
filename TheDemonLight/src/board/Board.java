package board;

import java.awt.*;
import pieces.Piece;
import pieces.Object;
import java.util.Random;

public class Board
{
	Font EndFont = new Font("Helvetica", 1, 32); //FONT TO BE DRAWN AT END OF THE GAME.
	Font RegularFont = new Font("Helvetica", 0, 14); //Regular Font.
	int nRows = 10;
	int w, s = 0;
	Color boardColor = new Color(16, 72, 13); //Color of the board.
	Color selectColor = new Color(252, 64, 1); //Color of whatever square the mouse is hovering over.
	Piece[][] pieces; 
	Object[][] objects;
	boolean[][] square;  //Stores the value of the square based on whether it's selected or not.
	
	int MOVED = 0;
	boolean moving = false;
	int moved = 0;
	int downRow = -1, downCol = -1, downRow2 = -1, downCol2 = -1;
	int statText = StatBoxes.ORIGIN;
	
	public final static int HUMAN = 0;
	public final static int COMPUTER = 1;
	int turn = HUMAN;
	
	Random random = new Random();
	
	int time = 0;
	int pause = 30;
	
	Piece LastHuman = null;
	Piece LastEnemy = null;
	
	boolean BattleText = false;
	int YourDamage = 0;
	int ComputerDamage = 0;
	int ExpGain = 0;
	Piece up, down, left, right;
	
	int HumanUnits = 0;
	int ComputerUnits = 0;
		
	public Board(int nRows)
	{
		this.nRows = nRows;  
		pieces = new Piece[nRows][nRows];
		objects = new Object[nRows][nRows];
		square = new boolean[nRows][nRows];
		unselect();
	}
	
	public void setWidth(int w)
	{
		this.w = w;
		s = w / nRows;
	}
	
	public int getstatText()
	{
		return statText;
	}
	
	public void setstatText(int statText)
	{
		this.statText = statText;
	}

	public int getSquareWidth()
	{
		return s;
	}

	public int getNRows()
	{
		return nRows;
	}
	
	public boolean getMoving()
	{
		return moving;
	}
	
	public boolean getBattleText()
	{
		return BattleText;
	}
	
	public void setHumanUnits(int change)
	{
		HumanUnits = change;
	}
	
	public void setComputerUnits(int change)
	{
		ComputerUnits = change;		
	}
	
	public void subtractHumanUnits(int subtract)
	{
		HumanUnits -= subtract;
	}
	
	public void subtractComputerUnits(int subtract)
	{
		ComputerUnits -= subtract;
	}
	
	public int getComputerUnits()
	{
		return ComputerUnits;
	}
	
	public int getTurn()
	{
		return turn;
	}
	
	public int getExpGain()
	{
		return ExpGain;
	}
	
	public boolean isValid(int row, int col)
	{
		return row >= 0 && row < nRows && col >= 0 && col < nRows;
	}

	public Piece getPiece(int row, int col)
	{
		if (isValid(row, col))
			return pieces[row][col];
		return null;
	}
	
	public Object getObject(int row, int col)
	{
		if (isValid(row, col))
			return objects[row][col];
		return null;
	}
	
	public int getComputerDamage()
	{
		return ComputerDamage;
	}
	
	public int getYourDamage()
	{
		return YourDamage;
	}
	
	public Piece getLastEnemy()
	{
		return LastEnemy;
	}
	
	public Piece getLastHuman()
	{
		return LastHuman;
	}
	
	public boolean legalMove(int row, int col)
	{
		//Determines if the user's move is a legal move or not (the square is selected and there's no piece where the user is trying to move it to.
		if (square[row][col] && getPiece(row, col) == null)
			return true;
		else
			return false;	
	}

   	public boolean setPiece(int row, int col, Piece piece)
   	{
   		if (isValid(row, col))
   		{
   			pieces[row][col] = piece;
   			return true;
   		}
   		return false;
   	}
   	
   	public boolean setObject(int row, int col, Object object)
   	{
   		if (isValid(row, col))
   		{
   			objects[row][col] = object;
   			return true;
   		}
   		return false;
   	}
   	
   	public void clear(int size)
   	{
   		for (int i=0;i<size;i++)
   			for (int j=0;j<size;j++)
   			{
   				if (pieces[i][j] != null)
   				{
   					pieces[i][j] = null;
   				}
   				if (objects[i][j] != null)
   				{
   					objects[i][j] = null;
   				}
   			}
   	}

   	public void unselect()
   	{
   		//Unselects all the squares after the user has moved his/her piece.
   		for (int i = 0; i < nRows; i++)
   			for (int j = 0; j < nRows; j++)
   			{
   				square[i][j] = false;
   			}
   	}

   	public void select(int row, int col, int move)
   	{
   		//Selects the appropriate squares that the user's piece can move to, based on the piece's move variable.
   		
   		if (move > 0)
   		{
   			int slow = 1;
			if (col+1 < nRows && pieces[row][col+1]==null)
			{
				if (objects[row][col+1] != null)
					slow = objects[row][col+1].getSlow();
				if (move-slow >= 0)
					square[row][col+1] = true;
				select(row, col+1, move-slow);
			}
			if (col-1 > -1 && pieces[row][col-1]==null)
			{
				slow = 1;
				if (objects[row][col-1] != null)
					slow = objects[row][col-1].getSlow();
				if (move-slow>=0)
					square[row][col-1] = true;
				select(row, col-1, move-slow);
			}
			if (row+1 < nRows && pieces[row+1][col]==null)
			{
				slow = 1;
				if (objects[row+1][col] != null)
					slow = objects[row+1][col].getSlow();
				if (move-slow >= 0)
					square[row+1][col] = true;
				select(row+1, col, move-slow);
			}
			if (row-1 > -1 && pieces[row-1][col]==null)
			{
				slow = 1;
				if (objects[row-1][col] != null)
					slow = objects[row-1][col].getSlow();
				if (move-slow >= 0)
					square[row-1][col] = true;
				select(row-1, col, move-slow);
			}
   		}
   	}

   	public void update(Graphics g, int mouseX, int mouseY, boolean DamageScreen)
   	{
   		if (!DamageScreen)
   		{
	   		//CHANGES THE TURN (EITHER HUMAN'S or COMPUTER'S) when all the units have been moved.
			switch(turn)
			{
			case HUMAN:
				turn = unitsMoved(HumanUnits, Piece.HUMAN);
				if (statText == StatBoxes.ENEMYMOVE)
					statText = StatBoxes.ORIGIN;
				break;
			case COMPUTER:
				turn = unitsMoved(ComputerUnits, Piece.COMPUTER);
				if (statText != StatBoxes.DAMAGESCREEN)
					statText = StatBoxes.ENEMYMOVE;
				break;
			}
   		}
		
   		g.setFont(RegularFont);
   		for (int row = 0 ; row < nRows ; row++)
   			for (int col = 0 ; col < nRows ; col++)
   			{
   				g.setColor(boardColor);
   				int x = s * col;
   				int y = s * row;
   				if (mouseX > x && mouseX < x+s && mouseY > y && mouseY < y+s)
   				{
   					g.setColor(selectColor);
   				}
   				g.fill3DRect(x, y, s, s, !square[row][col]);
   				if (objects[row][col] != null)
   				{
   					objects[row][col].update(g);
   				}
   				if (pieces[row][col] != null)
   				{
   					pieces[row][col].update(g);
   				}
   			}
   		
   		//THIS CODE RUNS WHEN THE GAME IS OVER:
   		
   		if (HumanUnits == 0)
   		{
   			g.setFont(EndFont);
   			g.setColor(Color.black);
   			g.drawString("GAME OVER, YOU LOSE!!!", 50, 200);
   			g.drawString("Refresh to play again", 150, 250);
   			g.setFont(RegularFont);
   		}
   		if (ComputerUnits == 0)
   		{
   			g.setFont(EndFont);
   			g.setColor(Color.black);
   			g.drawString("CONGRATULATIONS, YOU WIN!!!", 50, 200);
   			g.drawString("Click to Advance to Level 2", 150, 250);
   			g.setFont(RegularFont);
   		}
   		//------------------------------------//
   		
   		if (time==pause)
   		{
   			if (statText != StatBoxes.DAMAGESCREEN)
   				ComputersTurn();
   			time = 0;
   		}
   		else
   		{
   			time++;
   		}
   		
   		g.setColor(Color.black);
   		g.drawString("Humans: " + HumanUnits, 20, 20);
   		g.drawString("Computers: "+ ComputerUnits, 20, 40);
   	}
   	
	public int unitsMoved(int units, int unitType)
	{
		//DETERMINES IF ALL THE UNITS HAVE BEEN MOVED.
		if (MOVED == units)
		{
			unitType = (unitType+1)%2;
			for (int row = 0; row < nRows; row++)
			{
				for (int col = 0; col < nRows; col++)
				{
					Piece piece = getPiece(row, col);
					if (piece != null && piece.getTeam() == unitType)
						piece.setMovable(true);
				}
			}
		}
		
		MOVED = 0;
		
		for (int row = 0; row < nRows; row++)
		{
			for (int col = 0; col < nRows; col++)
			{
				Piece piece = getPiece(row, col);
				if (piece != null && !piece.getMovable() && piece.getTeam() == unitType)
					MOVED += 1;
			}
		}

		return unitType;
	}
	
	public int randomGenerator()
	{
		//CHOOSES A RANDOM DIRECTION:
		return random.nextInt(4);
	}
	
	public void ComputersTurn()
   	{
		//THE COMPUTER MOVES:
		Piece ComputerPiece = null;
   		for (int row2 = 0; row2 < nRows; row2++)
   			for (int col2 = 0; col2 < nRows; col2++)
   			{
				if (pieces[row2][col2] != null && turn == Piece.COMPUTER && getPiece(row2, col2).getMovable())
				{
					ComputerPiece = getPiece(row2, col2);
					select(row2, col2, ComputerPiece.getMove());
					downRow = row2;
					downCol = col2;
					break;
				}
				if (ComputerPiece != null)
				{
					break;
				}
   			}
   		
   		if (ComputerPiece != null)
   		{
			moveComputer(ComputerPiece, downRow, downCol, ComputerPiece.getMove());
   		}
   	}
	
	public void unselectPieces()
	{
		//SETS MOVABLE FOR ALL PIECES TO FALSE
		for (int row = 0; row < nRows; row++)
			for (int col = 0; col < nRows; col++)
			{
				Piece piece = getPiece(row, col);
				if (piece != null)
					piece.setMovable(false);
			}
	}
	
	public void moveComputer(Piece ComputerPiece, int row, int col, int movement)
	{
   		//COMPUTER ATTACKS IF IT IS NEXT TO ONE OF THE HUMAN UNITS:
		BattleText = nextToEnemy(ComputerPiece.getRow(), ComputerPiece.getCol(), Piece.HUMAN);
   		if (BattleText)
   		{
   			statText = StatBoxes.DAMAGE;
   			if (up != null)
   			{
   				damage(up, ComputerPiece, Piece.HUMAN);
   				ComputerPiece.setMovable(false);
   				unselect();
   				return;
   			}
   			else if (right != null)
   			{
   				damage(right, ComputerPiece, Piece.HUMAN);
   				ComputerPiece.setMovable(false);
   				unselect();
   				return;
   			}
   			else if (down != null)
   			{
   				damage(down, ComputerPiece, Piece.HUMAN);
   				ComputerPiece.setMovable(false);
   				unselect();
   				return;
   			}
   			else if (left != null)
   			{
   				damage(left, ComputerPiece, Piece.HUMAN);
   				ComputerPiece.setMovable(false);
   				unselect();
   				return;
   			}
   		}
		
		int randomGenerator = randomGenerator();
		statText = StatBoxes.ENEMYMOVE;
		
		switch(randomGenerator)
		{
		case 0:
			if (row-1>-1 && square[row-1][col])
			{
				row--;
				ComputerPiece.setPosition(row, col);
				movement--;
				break;
			}
		case 1:
			if (row+1<nRows && square[row+1][col])
			{
				row++;
				ComputerPiece.setPosition(row, col);
				movement--;
				break;
			}
		case 2:
			if (col-1>-1 && square[row][col-1])
			{
				col--;
				ComputerPiece.setPosition(row, col);
				movement--;
				break;
			}
		case 3:
			if (col+1<nRows && square[row][col+1])
			{
				col++;
				ComputerPiece.setPosition(row, col);
				movement--;
				break;
			}
		case 4:
			movement--;
			break;
		}
		
		if (movement > 0)
		{
			moveComputer(ComputerPiece, row, col, movement);
		}
		else
		{
			ComputerPiece.setMovable(false);
			unselect();
		}
	}
	
	public boolean nextToEnemy(int row, int col, int oppTeam)
	{
		//STORES THE SQUARES ABOVE, BELOW, TO THE LEFT, AND TO THE RIGHT OF THE ENEMY, then sees if checks to see if any of those squares contain units of the opposite team.
		up = getPiece(row-1, col);
		down = getPiece(row+1, col);
		left = getPiece(row, col-1);
		right = getPiece(row, col+1);
		if (up != null && up.getTeam() == oppTeam || down != null && down.getTeam() == oppTeam || right != null && right.getTeam() == oppTeam || left != null && left.getTeam() == oppTeam)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean piecesTouching(Piece ComputerPiece, Piece YourPiece, int oppTeam)
	{
		//Looks to see if ComputerPiece and YourPiece are touching by checking all pieces/squares around YourPiece and seeing if any of those are equal to the ComputerPiece.
		nextToEnemy(YourPiece.getRow(), YourPiece.getCol(), oppTeam);
		if (up == null)
			up = YourPiece;
		if (down == null)
			down = YourPiece;
		if (right == null)
			right = YourPiece;
		if (left == null)
			left = YourPiece;
		
		if (up.equals(ComputerPiece) || down.equals(ComputerPiece) || right.equals(ComputerPiece) || left.equals(ComputerPiece))
			return true;
		else
			return false;
	}
	
	public void damage(Piece ComputerPiece, Piece YourPiece, int oppTeam)
	{		
		//Calculates the damage for the ComputerPiece, then yourpiece if the computerpiece isn't dead.
		if (BattleText && ComputerPiece != null && ComputerPiece.getTeam() == oppTeam && piecesTouching(ComputerPiece, YourPiece, oppTeam))
		{
			LastHuman = YourPiece;
			LastEnemy = ComputerPiece;
			YourDamage = YourPiece.getAttack() - ComputerPiece.getDefense();
			if (YourDamage < 0)
			{
				YourDamage = 0;
			}
			ComputerDamage = ComputerPiece.getAttack() - YourPiece.getDefense();
			if (ComputerDamage < 0)
			{
				ComputerDamage = 0;
			}
			ComputerPiece.setHealth(-YourDamage);
			
			if (ComputerPiece.getHealth() > 0)
			{
				YourPiece.setHealth(-ComputerDamage);
				if (YourPiece.getTeam()==HUMAN)
				{
					ExpGain = 2+ComputerDamage;
					YourPiece.setExp(ExpGain);
				}
				else if (ComputerPiece.getTeam()==HUMAN)
				{
					ExpGain = 2+YourDamage;
					ComputerPiece.setExp(ExpGain);
				}
			}
			else if (ComputerPiece.getHealth() <= 0 && YourPiece.getTeam()==HUMAN)
			{
				ExpGain = 10+random.nextInt(10);
				YourPiece.setExp(ExpGain);
			}
			else if (YourPiece.getHealth() <= 0 && ComputerPiece.getTeam()==HUMAN)
			{
				ExpGain = 10+random.nextInt(10);
				ComputerPiece.setExp(ExpGain);
			}
			BattleText = false;
			BasicMenuButtons basicmenubuttons = this.new BasicMenuButtons();
			basicmenubuttons.selected();
			statText = StatBoxes.DAMAGESCREEN;
		}
	}
	
	public class BasicMenuButtons
	{
		public void exit(int mRow, int mCol)
		{
			//Returns unit to its original square, unselects every square.
			moving = false;
			unselect();
			statText = StatBoxes.ORIGIN;
			
			if (downRow != downRow2 || downCol != downCol2)
			{
				Piece piece = getPiece(downRow2, downCol2);
				if (piece != null)
					piece.setPosition(downRow, downCol);
			}
		}
		
		public void moving(int mRow, int mCol)
		{
			//Allows unit to move around.
			Piece piece = getPiece(downRow, downCol);
			if (piece == null)
			{
				piece = getPiece(downRow2, downCol2);
			}
			select(downRow, downCol, piece.getMove());
			if (piece != null && mRow < nRows && mCol < nRows && (legalMove(mRow, mCol) || (mRow == downRow && mCol == downCol)))
			{
				downRow2 = mRow;
				downCol2 = mCol;
				piece.setPosition(downRow2, downCol2);
				statText = StatBoxes.OPTIONS;
				BattleText = nextToEnemy(downRow2, downCol2, Piece.COMPUTER);
			}
		}
		
		public void selected()
		{
			//Permanently sets a piece down at the location it's been moved to.
			moving = false;
			unselect();
			statText = StatBoxes.ORIGIN;
			Piece piece = getPiece(downRow2, downCol2);
			if (piece != null)
				piece.setMovable(false);
		}
		
		public void begin(int mRow, int mCol)
		{
			//Displays the original screen, once clicked goes to moving.
			Piece piece = getPiece(mRow, mCol);
			if (piece != null && piece.getMovable())
			{
				moving = true;
				downRow = mRow;
				downCol = mCol;
				downRow2= downRow;
				downCol2 = downCol;
				statText = StatBoxes.OPTIONS;
				select(downRow, downCol, piece.getMove());
				BattleText = nextToEnemy(downRow2, downCol2, Piece.COMPUTER);
			}
		}
	}
}