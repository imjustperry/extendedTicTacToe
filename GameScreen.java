package cpsc2150.extendedTicTacToe;
import java.util.Scanner;

public class GameScreen {
    public static final char one = 'X';
    public static final char two = 'O';
    public static final int MAX_SIZE = 100;
    public static final int MIN_SIZE = 3;
    public static final int MAX_WIN = 25;
    /**
     * @param args takes in the string array of the input file(s)
     * @pre
     * Ask for row value (which is 0,0 in top left corner) before col value
     * User input will be an integer value for row and columns.
     * Player x should go first every game, player o goes second.
     * @post
     * row >= 0 and row <= 7, col >= 0 and col <=7   since game board is 8 by 8
     */
    public static void main(String [] args)
    {
        //Declare boolean statements that will be used for the while loops
        boolean end = false;
        boolean turn = false;
        boolean again = true;
        boolean play = true;
        boolean inputPlayers = true;
        boolean valid = true;

        //Declare variables that will be used to take in values from the input
        int row = 0; int col = 0;
        BoardPosition pos;
        IGameBoard board = null;
        Scanner in = new Scanner(System.in);
        char input = ' ';
        char player[] = null;
        int rows=0;
        int cols=0;
        int win=0;
        int num=0;



        while (end != true) {
            inputPlayers = true;
            while (inputPlayers)
            {
                System.out.println("How many players?");
                num = in.nextInt();
                if (num >10)
                {
                    System.out.println("Must be 10 players or fewer");
                }
                else if(num<2)
                {
                    System.out.println("Must be at least 2 players");
                }
                else
                {
                    player = new char[num];
                    for(int j=0; j<num; j++)
                    {
                        player[j] = ' ';
                    }
                    inputPlayers = false;
                }
            }
            inputPlayers = true;
            int i =0;
            while(inputPlayers)
            {
                valid = true;
                System.out.println("Enter the character to represent player " + (i+1));
                input = in.next().charAt(0);
                input = Character.toUpperCase(input);
                for(int j=0; j<num; j++)
                {
                    if (player[j] == input)
                    {
                        System.out.println(input + " is already taken as a player token!");
                        valid = false;
                        j=num;
                    }
                }
                if(valid)
                {
                    player[i] = input;
                    i++;
                }
                if(i == num)
                {
                    inputPlayers = false;
                }
            }

            rows = 0; win = 0; cols = 0;
            //Continuously checks the rows value that is inputted until there is a valid value
            while (rows<MIN_SIZE || rows >MAX_SIZE)
            {
                System.out.println("How many rows?");
                rows = in.nextInt();
                if (rows<MIN_SIZE || rows >MAX_SIZE)
                {
                    System.out.println("Rows must be between 3 and 100");
                }
            }

            //Continuously checks the columns value is inputted until there is a valid value
            while (cols<MIN_SIZE || cols >MAX_SIZE)
            {
                System.out.println("How many columns?");
                cols = in.nextInt();
                if (cols<MIN_SIZE || cols >MAX_SIZE)
                {
                    System.out.println("Columns must be between 3 and 100");
                }
            }

            //Continuously checks the wins value until there is a valid value
            while (win<MIN_SIZE || win > MAX_WIN)
            {
                System.out.println("How many in a row to win?");
                win = in.nextInt();
                if (win<MIN_SIZE || win > MAX_WIN)
                {
                    System.out.println("Number in a row to win must be between 3 and 25");
                }
                if (win > rows || win > cols)
                {
                    System.out.println("Number in a row to win should not be larger than rows or columns");
                    win = 0;
                }
            }
            valid = true;
            while (valid)
            {
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m?");
                input = in.next().charAt(0);
                input = Character.toUpperCase(input);
                if(input == 'F')
                {
                    board = new GameBoard(rows, cols, win);
                    valid = false;
                }
                else if(input == 'M')
                {
                    board = new GameBoardMem(rows, cols, win);
                    valid = false;
                }
                else
                {
                    System.out.println("Please enter F or M");
                }
            }

            play = true;
            i = 0;
            //Print out board before asking player for move
            System.out.println(board.toString());
            while (play)
            {
                again = true;
                //If the value of the position inserted is not valid, it will loop back to this point
                while (again) {
                    //Ask the player for the row and column values
                    System.out.println("Player " + player[i] + " Please enter your ROW");
                    row = in.nextInt();
                    System.out.println("Player " + player[i] + " Please enter your COLUMN");
                    col = in.nextInt();
                    in.nextLine();

                    //Creates a BoardPosition based off the given values
                    pos = new BoardPosition(row, col);
                    //Checks if it is a valid position
                    if(board.checkSpace(pos)) {
                        //Since it is a valid position, we do not want to keep looping
                        again = false;
                        //Place the valid position on the board
                        board.placeMarker(pos, player[i]);
                        //Check if the placement resulted in a win
                        if (board.checkForWinner(pos)) {
                            System.out.println("Player " + player[i] + " wins!");
                            System.out.println(board.toString());
                            System.out.println("Would you like to play again? Y/N");
                            input = in.next().charAt(0);
                            if (input == 'N' || input == 'n') {
                                end = true;
                                play = false;
                            }
                            else
                            {
                                play = false;
                            }
                        }
                        //Check if the placement resulted in a draw
                        else if (board.checkForDraw()) {
                            System.out.println("The game resulted in a draw!");
                            System.out.println(board.toString());
                            System.out.println("Would you like to play again? Y/N");
                            input = in.next().charAt(0);
                            if (input == 'N' || input == 'n') {
                                end = true;
                                play = false;
                            }
                            else
                            {
                                play = false;
                            }
                        }
                        //If it did not result in a win or a draw, change the turn over to the other player
                        else {
                            if (i+1 == num) {
                                i = 0;
                            }
                            else {
                                i++;
                            }
                            System.out.println(board.toString());
                        }
                    }
                    //If the position was not valid, print the board then ask them to pick again.
                    else {
                        System.out.println(board.toString());
                        System.out.println("That space is unavailable, please pick again");
                    }
                }
            }
        }
    }
}