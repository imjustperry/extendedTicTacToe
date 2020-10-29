package cpsc2150.extendedTicTacToe;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestGameBoard {
    private IGameBoard MakeBoard(int rows, int cols, int wins)
    {
        return new GameBoard(rows, cols, wins);
    }

    private String printBoard(char [][] board)
    {
        String s = "";

        //print the top format to show the columns
        s += "   ";
        for (int i=0; i<board[0].length; i++)
        {
            if (i < 10)
            {
                s += " " + i + "|";
            }
            else
            {
                s += i + "|";
            }
        }
        s += "\n";
        for (int i=0; i<board[0].length; i++)
        {
            for (int j=0; j<=board[0].length; j++)
            {
                //Prints the left format to show the rows
                if (j==0)
                {
                    if (i<10)
                    {
                        s += " " + i + "|";
                    }
                    else {
                        s += i + "|";
                    }
                }
                //Prints the contents of the board with proper layout
                else {
                    s += board[i][j-1] + " " + "|";
                }
            }
            //Creates a new line at the end of each row
            s += "\n";
        }
        //Return one string to the main function
        return s;
    }


    @Test
    public void constructorMoreColumns()
    {
        int row = 3;
        int col = 4;
        int win = 3;
        char arrayBoard[][] = new char[3][4];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<4; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        IGameBoard board = MakeBoard(row, col, win);
        assertEquals(printBoard(arrayBoard), board.toString());

        assertEquals(3, board.getNumRows());
        assertEquals(4, board.getNumColumns());
        assertEquals(3, board.getNumToWin());

    }

    @Test
    public void constructorMoreRows()
    {
        int row = 4;
        int col = 3;
        int win = 3;
        char arrayBoard[][] = new char[4][3];
        for (int i=0; i<4; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        IGameBoard board = MakeBoard(row, col, win);
        assertEquals(printBoard(arrayBoard), board.toString());

        assertEquals(4, board.getNumRows());
        assertEquals(3, board.getNumColumns());
        assertEquals(3, board.getNumToWin());
    }

    @Test
    public void constructorSameRowAndCol()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        IGameBoard board = MakeBoard(row, col, win);
        assertEquals(printBoard(arrayBoard), board.toString());

        assertEquals(3, board.getNumRows());
        assertEquals(3, board.getNumColumns());
        assertEquals(3, board.getNumToWin());
    }

    @Test
    public void checkSpaceBelowBounds()
    {
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }

        BoardPosition pos = new BoardPosition(-3, -3);

        IGameBoard board = MakeBoard(3, 3, 3);

        assertFalse(board.checkSpace(pos));
        assertEquals(board.toString(), printBoard(arrayBoard));
    }

    @Test
    public void checkSpaceAboveBounds()
    {
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }

        BoardPosition pos = new BoardPosition(3, 3);

        IGameBoard board = MakeBoard(3, 3, 3);

        assertFalse(board.checkSpace(pos));
        assertEquals(board.toString(), printBoard(arrayBoard));
    }

    @Test
    public void checkSpaceOccupiedSpace()
    {
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[1][1] = 'X';
        BoardPosition pos = new BoardPosition(1, 1);

        IGameBoard board = MakeBoard(3, 3, 3);
        board.placeMarker(pos, 'X');

        assertFalse(board.checkSpace(pos));
        assertEquals(board.toString(), printBoard(arrayBoard));
    }

    @Test
    public void horizontalRightPlacement()
    {
        char arrayBoard[][] = new char[5][5];
        for (int i=0; i<5; i++)
        {
            for (int j=0;j<5; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[2][1] = 'X';
        arrayBoard[2][2] = 'X';
        arrayBoard[2][3] = 'X';
        IGameBoard board = MakeBoard(5, 5, 3);

        BoardPosition pos = new BoardPosition(2, 1);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(2, 2);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(2, 3);
        board.placeMarker(pos, 'X');

        assertTrue(board.checkHorizontalWin(pos, 'X'));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void horizontalMoreThanOneChar()
    {
        char arrayBoard[][] = new char[5][5];
        for (int i=0; i<5; i++)
        {
            for (int j=0;j<5; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[2][1] = 'X';
        arrayBoard[2][2] = 'O';
        arrayBoard[2][3] = 'X';

        IGameBoard board = MakeBoard(5, 5, 3);

        BoardPosition pos = new BoardPosition(2, 1);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(2, 2);
        board.placeMarker(pos, 'O');

        pos = new BoardPosition(2, 3);
        board.placeMarker(pos, 'X');

        assertFalse(board.checkHorizontalWin(pos, 'X'));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void horizontalNotEnoughInARow()
    {
        char arrayBoard[][] = new char[5][5];
        for (int i=0; i<5; i++)
        {
            for (int j=0;j<5; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[2][1] = 'X';
        arrayBoard[2][2] = 'X';

        IGameBoard board = MakeBoard(5, 5, 3);

        BoardPosition pos = new BoardPosition(2, 1);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(2, 2);
        board.placeMarker(pos, 'X');

        assertFalse(board.checkHorizontalWin(pos, 'X'));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void horizontalMoreThanWin()
    {
        char arrayBoard[][] = new char[5][5];
        for (int i=0; i<5; i++)
        {
            for (int j=0;j<5; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[2][1] = 'X';
        arrayBoard[2][2] = 'X';
        arrayBoard[2][4] = 'X';
        arrayBoard[2][3] = 'X';

        IGameBoard board = MakeBoard(5, 5, 3);

        BoardPosition pos = new BoardPosition(2, 1);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(2, 2);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(2, 4);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(2, 3);
        board.placeMarker(pos, 'X');

        assertTrue(board.checkHorizontalWin(pos, 'X'));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void verticalNotEnoughInARow()
    {
        char arrayBoard[][] = new char[5][5];
        for (int i=0; i<5; i++)
        {
            for (int j=0;j<5; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[1][2] = 'X';
        arrayBoard[2][2] = 'X';

        IGameBoard board = MakeBoard(5, 5, 3);

        BoardPosition pos = new BoardPosition(1, 2);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(2, 2);
        board.placeMarker(pos, 'X');

        assertFalse(board.checkVerticalWin(pos, 'X'));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void verticalMoreThanOneChar()
    {
        char arrayBoard[][] = new char[5][5];
        for (int i=0; i<5; i++)
        {
            for (int j=0;j<5; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[1][2] = 'X';
        arrayBoard[2][2] = 'O';
        arrayBoard[3][2] = 'X';

        IGameBoard board = MakeBoard(5, 5, 3);

        BoardPosition pos = new BoardPosition(1, 2);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(2, 2);
        board.placeMarker(pos, 'O');

        pos = new BoardPosition(3, 2);
        board.placeMarker(pos, 'X');

        assertFalse(board.checkVerticalWin(pos, 'X'));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void verticalNormal()
    {
        char arrayBoard[][] = new char[5][5];
        for (int i=0; i<5; i++)
        {
            for (int j=0;j<5; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[1][2] = 'X';
        arrayBoard[2][2] = 'X';
        arrayBoard[3][2] = 'X';
        IGameBoard board = MakeBoard(5, 5, 3);

        BoardPosition pos = new BoardPosition(1, 2);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(2, 2);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(3, 2);
        board.placeMarker(pos, 'X');

        assertTrue(board.checkVerticalWin(pos, 'X'));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void verticalMoreThanWin()
    {
        char arrayBoard[][] = new char[5][5];
        for (int i=0; i<5; i++)
        {
            for (int j=0;j<5; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[1][2] = 'X';
        arrayBoard[2][2] = 'X';
        arrayBoard[4][2] = 'X';
        arrayBoard[3][2] = 'X';
        IGameBoard board = MakeBoard(5, 5, 3);

        BoardPosition pos = new BoardPosition(1, 2);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(2, 2);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(4, 2);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(3, 2);
        board.placeMarker(pos, 'X');

        assertTrue(board.checkVerticalWin(pos, 'X'));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void diagonalNormal()
    {
        char arrayBoard[][] = new char[5][5];
        for (int i=0; i<5; i++)
        {
            for (int j=0;j<5; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[1][1] = 'X';
        arrayBoard[2][2] = 'X';
        arrayBoard[3][3] = 'X';
        IGameBoard board = MakeBoard(5, 5, 3);

        BoardPosition pos = new BoardPosition(1, 1);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(2, 2);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(3, 3);
        board.placeMarker(pos, 'X');

        assertTrue(board.checkDiagonalWin(pos, 'X'));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void diagonalMoreCharThanWin()
    {
        char arrayBoard[][] = new char[5][5];
        for (int i=0; i<5; i++)
        {
            for (int j=0;j<5; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[1][1] = 'X';
        arrayBoard[2][2] = 'X';
        arrayBoard[3][3] = 'X';
        arrayBoard[4][4] = 'X';
        IGameBoard board = MakeBoard(5, 5, 3);

        BoardPosition pos = new BoardPosition(1, 1);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(2, 2);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(4, 4);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(3, 3);
        board.placeMarker(pos, 'X');

        assertTrue(board.checkDiagonalWin(pos, 'X'));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void diagonalLowerRight()
    {
        char arrayBoard[][] = new char[5][5];
        for (int i=0; i<5; i++)
        {
            for (int j=0;j<5; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[0][0] = 'X';
        arrayBoard[1][1] = 'X';
        arrayBoard[2][2] = 'X';
        IGameBoard board = MakeBoard(5, 5, 3);

        BoardPosition pos = new BoardPosition(0, 0);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(1, 1);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(2,2);
        board.placeMarker(pos, 'X');

        assertTrue(board.checkDiagonalWin(pos, 'X'));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void diagonalLowerLeft()
    {
        char arrayBoard[][] = new char[5][5];
        for (int i=0; i<5; i++)
        {
            for (int j=0;j<5; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[0][4] = 'X';
        arrayBoard[1][3] = 'X';
        arrayBoard[2][2] = 'X';
        IGameBoard board = MakeBoard(5, 5, 3);

        BoardPosition pos = new BoardPosition(0, 4);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(1, 3);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(2,2);
        board.placeMarker(pos, 'X');

        assertTrue(board.checkDiagonalWin(pos, 'X'));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void diagonalLowerRightBoundary()
    {
        char arrayBoard[][] = new char[5][5];
        for (int i=0; i<5; i++)
        {
            for (int j=0;j<5; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[4][4] = 'X';
        arrayBoard[3][3] = 'X';
        arrayBoard[2][2] = 'X';
        IGameBoard board = MakeBoard(5, 5, 3);

        BoardPosition pos = new BoardPosition(4, 4);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(3, 3);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(2,2);
        board.placeMarker(pos, 'X');

        assertTrue(board.checkDiagonalWin(pos, 'X'));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void diagonalLowerLeftBoundary()
    {
        char arrayBoard[][] = new char[5][5];
        for (int i=0; i<5; i++)
        {
            for (int j=0;j<5; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[4][0] = 'X';
        arrayBoard[3][1] = 'X';
        arrayBoard[2][2] = 'X';
        IGameBoard board = MakeBoard(5, 5, 3);

        BoardPosition pos = new BoardPosition(4, 0);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(3, 1);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(2,2);
        board.placeMarker(pos, 'X');

        assertTrue(board.checkDiagonalWin(pos, 'X'));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void diagonalTwoBoundaries()
    {
        char arrayBoard[][] = new char[5][5];
        for (int i=0; i<5; i++)
        {
            for (int j=0;j<5; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[4][2] = 'X';
        arrayBoard[3][3] = 'X';
        arrayBoard[2][4] = 'X';
        IGameBoard board = MakeBoard(5, 5, 3);

        BoardPosition pos = new BoardPosition(4, 2);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(3, 3);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(2,4);
        board.placeMarker(pos, 'X');

        assertTrue(board.checkDiagonalWin(pos, 'X'));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void drawFullBoardWithTwoCharacters()
    {
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[0][0] = 'O';
        arrayBoard[1][0] = 'O';
        arrayBoard[2][1] = 'O';
        arrayBoard[0][2] = 'O';
        arrayBoard[2][0] = 'X';
        arrayBoard[1][1] = 'X';
        arrayBoard[0][1] = 'X';
        arrayBoard[1][2] = 'X';
        arrayBoard[2][2] = 'X';

        IGameBoard board = MakeBoard(3, 3, 3);

        BoardPosition pos = new BoardPosition(2, 0);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(1, 1);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(0, 1);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(1, 2);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(2, 2);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(0, 0);
        board.placeMarker(pos, 'O');

        pos = new BoardPosition(1, 0);
        board.placeMarker(pos, 'O');

        pos = new BoardPosition(2, 1);
        board.placeMarker(pos, 'O');

        pos = new BoardPosition(0, 2);
        board.placeMarker(pos, 'O');

        assertTrue(board.checkForDraw());
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void drawNonFullBoard()
    {
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[1][1] = 'X';
        arrayBoard[1][2] = 'O';

        IGameBoard board = MakeBoard(3, 3, 3);

        BoardPosition pos = new BoardPosition(1, 1);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(1, 2);
        board.placeMarker(pos, 'O');

        assertFalse(board.checkForDraw());
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void drawEmptyBoard()
    {
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }

        IGameBoard board = MakeBoard(3, 3, 3);

        assertFalse(board.checkForDraw());
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void drawMoreThanTwoPlayers()
    {
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[0][0] = 'O';
        arrayBoard[1][0] = 'O';
        arrayBoard[1][2] = 'O';
        arrayBoard[0][2] = 'T';
        arrayBoard[2][0] = 'T';
        arrayBoard[0][1] = 'T';
        arrayBoard[1][1] = 'X';
        arrayBoard[2][1] = 'X';
        arrayBoard[2][2] = 'X';

        IGameBoard board = MakeBoard(3, 3, 3);

        BoardPosition pos = new BoardPosition(2, 0);
        board.placeMarker(pos, 'T');

        pos = new BoardPosition(1, 1);
        board.placeMarker(pos, 'T');

        pos = new BoardPosition(0, 1);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(1, 2);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(2, 2);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(0, 0);
        board.placeMarker(pos, 'O');

        pos = new BoardPosition(1, 0);
        board.placeMarker(pos, 'O');

        pos = new BoardPosition(2, 1);
        board.placeMarker(pos, 'O');

        pos = new BoardPosition(0, 2);
        board.placeMarker(pos, 'T');

        assertTrue(board.checkForDraw());
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void placeMarkerBlankBoard()
    {
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[1][1] = 'X';

        IGameBoard board = MakeBoard(3, 3, 3);

        BoardPosition pos = new BoardPosition(1, 1);
        board.placeMarker(pos, 'X');

        assertEquals(arrayBoard[1][1], board.whatsAtPos(pos));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void placeMarkerUpperBound()
    {
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[2][2] = 'X';

        IGameBoard board = MakeBoard(3, 3, 3);

        BoardPosition pos = new BoardPosition(2, 2);
        board.placeMarker(pos, 'X');

        assertEquals(arrayBoard[2][2], board.whatsAtPos(pos));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void placeMarkerLowerBound()
    {
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[0][0] = 'X';

        IGameBoard board = MakeBoard(3, 3, 3);

        BoardPosition pos = new BoardPosition(0, 0);
        board.placeMarker(pos, 'X');

        assertEquals(arrayBoard[0][0], board.whatsAtPos(pos));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void placeMarkerNewCharacter()
    {
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[1][1] = 'X';
        arrayBoard[2][2] = 'O';

        IGameBoard board = MakeBoard(3, 3, 3);

        BoardPosition pos = new BoardPosition(1, 1);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(2, 2);
        board.placeMarker(pos, 'O');

        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void placeMarkerThatWillFillBoard()
    {
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }
        arrayBoard[0][0] = 'O';
        arrayBoard[1][0] = 'O';
        arrayBoard[2][1] = 'O';
        arrayBoard[0][2] = 'O';
        arrayBoard[2][0] = 'X';
        arrayBoard[1][1] = 'X';
        arrayBoard[0][1] = 'X';
        arrayBoard[1][2] = 'X';

        IGameBoard board = MakeBoard(3, 3, 3);

        BoardPosition pos = new BoardPosition(2, 0);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(1, 1);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(0, 1);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(1, 2);
        board.placeMarker(pos, 'X');

        pos = new BoardPosition(0, 0);
        board.placeMarker(pos, 'O');

        pos = new BoardPosition(1, 0);
        board.placeMarker(pos, 'O');

        pos = new BoardPosition(2, 1);
        board.placeMarker(pos, 'O');

        pos = new BoardPosition(0, 2);
        board.placeMarker(pos, 'O');

        assertEquals(printBoard(arrayBoard), board.toString());

        arrayBoard[2][2] = 'X';
        pos = new BoardPosition(2, 2);
        board.placeMarker(pos, 'X');

        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void isPlayerAtBlankSpot()
    {
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }

        IGameBoard board = MakeBoard(3, 3, 3);

        BoardPosition pos = new BoardPosition(1,1);

        assertEquals(printBoard(arrayBoard), board.toString());
        assertFalse(board.isPlayerAtPos(pos, 'X'));
    }

    @Test
    public void isPlayerAtSameCharSpot()
    {
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }

        IGameBoard board = MakeBoard(3, 3, 3);
        BoardPosition pos = new BoardPosition(1,1);
        arrayBoard[1][1] = 'O';
        board.placeMarker(pos, 'O');

        assertTrue(board.isPlayerAtPos(pos, arrayBoard[1][1]));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void isPlayerAtPos_BoardWithMoreThanOneChar()
    {
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }

        IGameBoard board = MakeBoard(3, 3, 3);

        BoardPosition pos = new BoardPosition(1,1);
        board.placeMarker(pos, 'X');
        arrayBoard[1][1] = 'X';

        pos = new BoardPosition(1,2);
        board.placeMarker(pos, 'O');
        arrayBoard[1][2] = 'O';

        assertTrue(board.isPlayerAtPos(pos, arrayBoard[1][2]));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void isPlayerAtPosAtUpperBound()
    {
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }

        IGameBoard board = MakeBoard(3, 3, 3);

        BoardPosition pos = new BoardPosition(2,2);
        board.placeMarker(pos, 'X');
        arrayBoard[2][2] = 'X';

        assertTrue(board.isPlayerAtPos(pos, arrayBoard[2][2]));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void isPlayerAtPosAtLowerBound()
    {
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }

        IGameBoard board = MakeBoard(3, 3, 3);

        BoardPosition pos = new BoardPosition(0,0);
        board.placeMarker(pos, 'X');
        arrayBoard[0][0] = 'X';

        assertTrue(board.isPlayerAtPos(pos, arrayBoard[0][0]));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void whatsAtBlankSpot()
    {
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }

        IGameBoard board = MakeBoard(3, 3, 3);

        BoardPosition pos = new BoardPosition(1,1);

        assertEquals(arrayBoard[1][1], board.whatsAtPos(pos));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void whatsAtOccupiedSpot()
    {
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }

        IGameBoard board = MakeBoard(3, 3, 3);

        BoardPosition pos = new BoardPosition(1,1);
        board.placeMarker(pos, 'X');
        arrayBoard[1][1] = 'X';

        assertEquals(arrayBoard[1][1], board.whatsAtPos(pos));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void whatsAtPos_BoardWithMoreThanOneChar()
    {
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }

        IGameBoard board = MakeBoard(3, 3, 3);

        BoardPosition pos = new BoardPosition(1,1);
        board.placeMarker(pos, 'X');
        arrayBoard[1][1] = 'X';

        pos = new BoardPosition(1,2);
        board.placeMarker(pos, 'O');
        arrayBoard[1][2] = 'O';

        assertEquals(arrayBoard[1][2], board.whatsAtPos(pos));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void whatsAtPosAtUpperBound()
    {
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }

        IGameBoard board = MakeBoard(3, 3, 3);

        BoardPosition pos = new BoardPosition(2,2);
        board.placeMarker(pos, 'X');
        arrayBoard[2][2] = 'X';

        assertEquals(arrayBoard[2][2], board.whatsAtPos(pos));
        assertEquals(printBoard(arrayBoard), board.toString());
    }

    @Test
    public void whatsAtPosAtLowerBound()
    {
        char arrayBoard[][] = new char[3][3];
        for (int i=0; i<3; i++)
        {
            for (int j=0;j<3; j++)
            {
                arrayBoard[i][j] = ' ';
            }
        }

        IGameBoard board = MakeBoard(3, 3, 3);

        BoardPosition pos = new BoardPosition(0,0);
        board.placeMarker(pos, 'X');
        arrayBoard[0][0] = 'X';

        assertEquals(arrayBoard[0][0], board.whatsAtPos(pos));
        assertEquals(printBoard(arrayBoard), board.toString());
    }
}
