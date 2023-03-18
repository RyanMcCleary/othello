package othello;

public class SquareIndex  {
	private int row;
	private int column;
	
	public SquareIndex(int row, int column)  {
		this.row = row;
		this.column = column;
	}
	
	
	public int row()  {
		return this.row;	
	}
	
	
	public int column()  {
		return this.column;	
	}
}
