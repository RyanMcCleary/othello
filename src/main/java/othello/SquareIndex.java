package othello;

public class SquareIndex  {

	private int row;
	private int column;
	
	public SquareIndex(int row, int column)  {
		this.row = row;
		this.column = column;
	}
	
	public int getRow()  {
		return this.row;	
	}
	
	
	public int getColumn()  {
		return this.column;	
	}

	@Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ( ! (other instanceof SquareIndex)) {
            return false;
        }
        SquareIndex otherSquareIndex = (SquareIndex) other;
        return this.row == otherSquareIndex.getRow() && this.column == otherSquareIndex.getColumn();
	}

}