package cpsc2150.extendedTicTacToe;

/**
 * Perry Chen 10/18/19
 * @Correspondence Rows = rows
 * Columns = cols
 * Wins = win
 * Board = board
 * @Invariants 3 <= rows <= 100
 *             3 <= cols <= 100
 *             3 <= win <= 25
 *             win < cols
 *             win < rows
 */
public class GameBoard extends AbsGameBoard implements IGameBoard {
    private int rows;
    private int cols;
    private int win;
    private char [][] board;
    /**@pre There is an instance of GameScreen in order to call this
     * The parameter values are valid
     * @post Creates a game board with a 2 dimension rows*cols array of characters.
     * Initializes them to ' '
     */
    public GameBoard(int rows, int cols, int win) {
        this.rows = rows;
        this.cols = cols;
        this.win = win;

        board = new char[rows][cols];
        for (int i=0; i<rows; i++)
        {
            //Setting new game board to be blank
            for (int j=0;j<cols;j++)
            {
                board[i][j] = ' ';
            }
        }
    }


    /**
     * @param marker is the location to place the character for the player
     * @param player is the character that is used to show the player
     * @pre
     * player is a valid character
     * marker location should be available
     * @post Sets the value of the position to the char of the player
     */
    public void placeMarker(BoardPosition marker, char player)
    {
        board[marker.getRow()][marker.getCol()] = player;
    }

    /**
     * @param pos is the position that we want to check the value of
     * @return the character value at the position of pos
     * @pre the position is at a valid position on the board
     * @post finds the value at the position of pos and returns it
     */
    public char whatsAtPos(BoardPosition pos)
    {
        return board[pos.getRow()][pos.getCol()];
    }

    /**
     * @pre the value of Rows has been set
     * Instance of GameBoard has been created
     * @post it will return the value of the number of rows
     * @return value rows
     */
    public int getNumRows()
    {
        return rows;
    }

    /**
     * @pre the value of cols has been set
     * Instance of GameBoard has been created
     * @post it will return the value of the number of columns
     * @return value of cols
     */
    public int getNumColumns()
    {
        return cols;
    }

    /**
     * @pre the value of wins has been set
     * Instance of GameBoard has been created
     * @post it will return the value of the number in a row needed to win
     * @return number of win condition
     */
    public int getNumToWin()
    {
        return win;
    }
}