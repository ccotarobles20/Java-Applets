package pieces;

import java.awt.*;

import board.Board;


public class Object
{	
	int row = -1, col = -1;
	int slow = 0;
	Board board;
			
	java.awt.Image picture;

	public Object(java.awt.Image picture, Board board, int row, int col, int slow)
	{
		if (board.getPiece(row, col)!=null || board.getObject(row, col)!=null)
		{
			System.err.println("Multiple units have placed on the square ("+row+", "+col+").");
		}
		
		this.board = board;
		this.row = row;
		this.col = col;
		this.picture = picture;
		this.slow = slow;
		setPosition(row, col);
		board.setObject(row, col, this);		
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public int getSlow()
	{
		return slow;
	}

	public boolean setPosition(int row, int col)
	{
		if (board.isValid(row, col) && board.getObject(row, col) == null)
		{
			board.setObject(this.row, this.col, null);
			this.row = row;
			this.col = col;
			board.setObject(row, col, this);
			return true;
		}
		return false;
	}

	public void update(Graphics g)
	{
		int s = board.getSquareWidth();
		int x = s * col;
		int y = s * row;

		// DRAWING
		g.drawImage(picture, x, y, s, s, null);
	}
}