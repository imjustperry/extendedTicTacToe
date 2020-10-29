package cpsc2150.extendedTicTacToe;
/**
 * Game board of that is used to play tic tac toe, the user can change the size.
 * @Defines: Rows: Z - The number of rows of the board
 *          Columns: Z - The number of columns of the board
 *          Wins: Z - The number of markers in a row that results in a win
 *          Board: - The board where the markers will be placed
 *
 * @Initialization Ensures: [Rows is calculated based on the input number of rows]
 *                          [Cols is calculated based on the input number of columns]
 *                          [Wins is calculated based on the input number of markers in a row to get a win]
 *                          [Board is initialized to the size of the rows and columns]
 *
 * @Constraints: 3 <= Rows <= 100
 *               3 <= Cols <= 100
 *               3 <= Wins <= 25
 *               Wins < Cols
 *               Wins < Rows
 */
public interface IGameBoard {
    public static final int MAX_SIZE = 100;
    public static final int MAX_WIN = 25;
    public static final int MIN_SIZE = 3;
    public static final int DOUBLE = 10;

    /**
     * @param pos is the location to place the character for the player
     * @param marker is the character that is used to show the player
     * @pre
     * player is a valid character
     * marker location should be available
     * @post Sets the value of the position to the char of the player
     */
    public void placeMarker(BoardPosition pos, char marker);

    /**
     * @param pos is the position that we want to check the value of
     * @return the character value at the position of pos
     * @pre the position is at a valid position on the board
     * @post finds the value at the position of pos and returns it
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * @pre the value of Rows has been set
     * Instance of GameBoard has been created
     * @post it will return the value of the number of rows
     * @return value rows
     */
    public int getNumRows();

    /**
     * @pre the value of cols has been set
     * Instance of GameBoard has been created
     * @post it will return the value of the number of columns
     * @return value of cols
     */
    public int getNumColumns();

    /**
     * @pre the value of wins has been set
     * Instance of GameBoard has been created
     * @post it will return the value of the number in a row needed to win
     * @return number of win condition
     */
    public int getNumToWin();

    /**@pre the board has been created
     * @post will create a string that will print the entire game board
     * @return Will return a string with the proper format
     */
    public String toString();

    /**
     * @param pos is the position where we will check for availability
     * @return true or false depending on the availability of the spot
     * @pre the values of row and col for BoardPosition should be valid
     * @post true or false depending on if the space is available
     */
    default public boolean checkSpace(BoardPosition pos)
    {
        //If the row position is not 0<=row<ROWS, it is out of bounds
        if (pos.getRow() < 0 || pos.getRow() >= getNumRows())
        {
            return false;
        }
        //If the column position is not 0<=col<COLS, it is out of bounds
        if (pos.getCol() < 0 || pos.getCol() >= getNumColumns())
        {
            return false;
        }
        //If the position on the board is already occupied by a player, return false
        if (whatsAtPos(pos) != ' ')
        {
            return false;
        }
        return true;
    }

    /**
     * @param pos is the position that we want to check the value of
     * @param player is the character that we are looking for
     * @return true or false depending on if the player's character is found
     * @pre valid position to look for player and player character is valid
     * @post finds the value at the position of pos and compares it to the player char
     */
    default public boolean isPlayerAtPos(BoardPosition pos, char player)
    {
        if (whatsAtPos(pos) == player)
        {
            return true;
        }
        return false;
    }

    /**
     * @param lastPos is the last position that was played
     * @param player is the value of the character that look for 5 in a row of
     * @return true or false depending on if there was a win
     * @pre the last position was a valid position
     * @post returns true or false depending on if there were certain number(win) in a row diagonally, both diagonals
     */
    default public boolean checkDiagonalWin(BoardPosition lastPos, char player)
    {
        int i = lastPos.getRow();
        int j = lastPos.getCol();
        int count = 1;

        boolean valid = true;
        while (valid)
        {
            if (i-1 < 0 || j-1 < 0)
            {
                valid = false;
            }
            else
            {
                i--; j--;
                BoardPosition pos = new BoardPosition(i, j);
                if (isPlayerAtPos(pos, player))
                {
                    count++;
                }
                else
                {
                    valid = false;
                }
                if (count == getNumToWin())
                {
                    return true;
                }
            }
        }

        i = lastPos.getRow();
        j = lastPos.getCol();
        valid = true;
        while (valid)
        {
            if (i+1 == getNumRows() || j+1 == getNumColumns())
            {
                valid = false;
            }
            else
            {
                i++; j++;
                BoardPosition pos = new BoardPosition(i, j);
                if (isPlayerAtPos(pos, player))
                {
                    count++;
                }
                else
                {
                    valid = false;
                }
                if (count == getNumToWin())
                {
                    return true;
                }
            }
        }

        count = 1;
        i = lastPos.getRow();
        j = lastPos.getCol();
        valid = true;
        while (valid)
        {
            if (i-1 < 0 || j+1 == getNumColumns())
            {
                valid = false;
            }
            else
            {
                i--; j++;
                BoardPosition pos = new BoardPosition(i, j);
                if (isPlayerAtPos(pos, player))
                {
                    count++;
                }
                else
                {
                    valid = false;
                }
                if (count == getNumToWin())
                {
                    return true;
                }
            }
        }

        i = lastPos.getRow();
        j = lastPos.getCol();
        valid = true;
        while (valid)
        {
            if (i+1 == getNumRows() || j-1 < 0)
            {
                valid = false;
            }
            else
            {
                i++; j--;
                BoardPosition pos = new BoardPosition(i, j);
                if (isPlayerAtPos(pos, player))
                {
                    count++;
                }
                else
                {
                    valid = false;
                }
                if (count == getNumToWin())
                {
                    return true;
                }
            }
        }
        //If both diagonals did not result in a win with the correct number in a row, return false.
        return false;
    }

    /**
     * @param lastPos is the last position that was played
     * @param player is the value of the character that look for 5 in a row of
     * @return true or false depending on if there was a win
     * @pre the last position was a valid position
     * @post returns true or false depending on if there were a certain number(win) in a row vertically
     */
    default public boolean checkVerticalWin(BoardPosition lastPos, char player)
    {
        int i = lastPos.getRow();
        int j = lastPos.getCol();
        int count = 1;

        boolean valid = true;
        while (valid) {
            if (i - 1 < 0) {
                valid = false;
            }
            else {
                i--;
                BoardPosition pos = new BoardPosition(i, j);
                if (isPlayerAtPos(pos, player)) {
                    count++;
                }
                else
                {
                    valid = false;
                }
                if (count == getNumToWin()) {
                    return true;
                }
            }
        }

        i = lastPos.getRow();
        valid = true;
        while (valid) {
            if (i + 1 == getNumRows()) {
                valid = false;
            } else {
                i++;
                BoardPosition pos = new BoardPosition(i, j);
                if (isPlayerAtPos(pos, player)) {
                    count++;
                }
                else
                {
                    valid = false;
                }
                if (count == getNumToWin()) {
                    return true;
                }
            }
        }

        //Return false if there was not 5 in a row of the player's mark
        return false;
    }

    /**
     * @param lastPos is the last position that was played
     * @return true or false depending on if there was a win
     * @pre the last position was a valid position
     * @post returns true or false depending on if there were certain number(win) in a row horizontally
     */
    default public boolean checkHorizontalWin(BoardPosition lastPos, char player)
    {
        int i = lastPos.getRow();
        int j = lastPos.getCol();
        int count =1;

        boolean valid = true;
        while (valid)
        {
            if (j-1 < 0)
            {
                valid = false;
            }
            else
            {
                j--;
                BoardPosition pos = new BoardPosition(i,j);
                if (isPlayerAtPos(pos, player))
                {
                    count ++;
                }
                else
                {
                    valid = false;
                }
                if(count == getNumToWin())
                {
                    return true;
                }
            }
        }

        valid = true;
        j= lastPos.getCol();
        while (valid)
        {
            if (j+1 == getNumColumns())
            {
                valid = false;
            }
            else
            {
                j++;
                BoardPosition pos = new BoardPosition(i,j);
                if (isPlayerAtPos(pos, player))
                {
                    count ++;
                }
                else
                {
                    valid = false;
                }
                if(count == getNumToWin())
                {
                    return true;
                }
            }
        }

        //Return false if there was not 5 in a row of the player's mark
        return false;
    }

    /**
     * @return true or false depending on if the last move resulted in a draw
     * @pre the last position was valid
     * @post if there are no free board positions remaining, it will return true
     */
    default public boolean checkForDraw()
    {
        //Goes through every spot on the board
        for (int i=0; i<getNumRows();i++)
        {
            for (int j=0; j<getNumColumns();j++)
            {
                BoardPosition pos = new BoardPosition(i, j);
                //If any of the board positions is still empty, return false since it is not a draw
                if (whatsAtPos(pos) == ' ')
                {
                    return false;
                }
            }
        }
        //Since no spots were empty, return true
        return true;
    }

    /**
     * @param lastPos is the last position that was played
     * @return true or false depending on if the last move resulted in player winning
     * @pre the last position was valid
     * @post if the player won, it will return true
     */
    default public boolean checkForWinner(BoardPosition lastPos)
    {
        //Finds the character at the last position
        char player = whatsAtPos(lastPos);
        //Checks all three win conditions using the lastPosition and the player's character
        if (checkHorizontalWin(lastPos, player) || checkDiagonalWin(lastPos, player) || checkVerticalWin(lastPos, player))
        {
            return true;
        }
        return false;
    }
}
