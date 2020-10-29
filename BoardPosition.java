package cpsc2150.extendedTicTacToe;

public class BoardPosition {
    private int row;
    private int col;
    /**
     * @pre a value of row has already been set
     * @post
     * Will return the current value of column
     * @return the value of row
     */
    public int getRow()
    {
        return row;
    }

    /**
     * @pre a value of column has already been set
     * @post
     * Will return the current value of column
     * @return the value of col
     */
    public int getCol()
    {
        return col;
    }

    /**
     * @param row for the row position
     * @param col for the column position
     * @pre
     * row >=3 and row <= 100
     * col >=3 and col <= 100
     * @post the row and col values of BoardPosition will be set to the row and col values
     * in the parameters
     */
    public BoardPosition(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    /**
     * @pre there is an object of BoardPosition
     * @post toString will print out the row and columns in the correct format
     * @return returns string in the format "<row>,<column>"
     */
    public String toString()
    {
        String s;
        s =  row + ", " + col;
        return s;
    }

    /**
     * @pre both the positions being compared are valid
     * @post returns true if the row and column of the two BoardPositions are equal
     * @param pos is the position that we are comparing to
     * @return returns true if the two Board Positions are equal
     */
    public boolean equals(BoardPosition pos)
    {
        if (this.row == pos.getRow() && this.col == pos.getCol())
        {
            return true;
        }
        return false;
    }
}