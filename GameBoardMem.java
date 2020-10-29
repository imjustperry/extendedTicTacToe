package cpsc2150.extendedTicTacToe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Perry Chen 11/3/19
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
public class GameBoardMem extends AbsGameBoard implements IGameBoard {
    private int rows;
    private int cols;
    private int win;
    private Map<Character, List<BoardPosition>> board;
    /**@pre There is an instance of GameScreen in order to call this
     * The parameter values are valid
     * @post Creates a game board with a 2 dimension rows*cols array of characters.
     * Initializes them to ' '
     */
    public GameBoardMem(int rows, int cols, int win) {
        this.rows = rows;
        this.cols = cols;
        this.win = win;

        board = new HashMap<Character, List<BoardPosition> >();
    }


    /**
     * @param marker is the location to place the character for the player
     * @param player is the character that is used to show the player
     * @pre
     * player == " " or player == "X" or player == "O"
     * marker location should be available
     * @post Sets the value of the position to the char of the player
     */
    public void placeMarker(BoardPosition marker, char player)
    {
        if (!board.containsKey(player))
        {
            board.put(player, new ArrayList());
        }
        board.get(player).add(marker);
    }

    /**
     * @param pos is the position that we want to check the value of
     * @return the character value at the position of pos
     * @pre the position is at a valid position on the board
     * @post finds the value at the position of pos and returns it
     */
    public char whatsAtPos(BoardPosition pos)
    {
        //Goes through every key in the map and then every value in the list to see if which key pos is part of
        for (Map.Entry<Character, List<BoardPosition> > m: board.entrySet())
        {
            for (int i =0 ; i < m.getValue().size(); i++)
            {
                if (m.getValue().get(i).equals(pos))
                {
                    return m.getKey();
                }
            }
        }
        return ' ';
    }

    /**
     * @param pos is the position that we want to check the value of
     * @param player is the character that we are looking for
     * @return true or false depending on if the player's character is found
     * @pre valid position to look for player and player character is valid
     * @post finds the value at the position of pos and compares it to the player char
     */
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player)
    {
        if (!board.containsKey(player))
        {
            return false;
        }
        //Goes through every member in the list and check if it is equal to pos
        for (int i =0 ; i < board.get(player).size(); i++)
        {
            if (board.get(player).get(i).equals(pos))
            {
                return true;
            }
        }
        return false;
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
