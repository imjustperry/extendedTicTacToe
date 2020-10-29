package cpsc2150.extendedTicTacToe;

abstract public class AbsGameBoard implements IGameBoard{
    /**@pre the board has been created
     * @post will create a string that will print the entire game board
     * @return Will return a string with the proper format
     */
    public String toString()
    {

            String s = "";

            //print the top format to show the columns
            s += "   ";
            for (int i=0; i<getNumColumns(); i++)
            {
                if (i < DOUBLE)
                {
                    s += " " + i + "|";
                }
                else
                {
                    s += i + "|";
                }
            }
            s += "\n";
            for (int i=0; i<getNumRows(); i++)
            {
                for (int j=0; j<=getNumColumns(); j++)
                {
                    //Prints the left format to show the rows
                    if (j==0)
                    {
                        if (i<DOUBLE)
                        {
                            s += " " + i + "|";
                        }
                        else {
                            s += i + "|";
                        }
                    }
                    //Prints the contents of the board with proper layout
                    else {
                        BoardPosition pos = new BoardPosition(i, j-1);
                        s += whatsAtPos(pos) + " " + "|";
                    }
                }
                //Creates a new line at the end of each row
                s += "\n";
            }
            //Return one string to the main function
            return s;

    }
}
