import java.util.Scanner;
import java.util.ArrayList;

public class Game {

    enum Color {White, Black};

    private Player white = new Player(Color.White);
    private Player black = new Player(Color.Black);
    private Player winner = null;
    private Player currentPlayer = white;
    private Player opponentPlayer = black;
    private Board board = new Board();

    // Method to start the game of EverChess
    public void start() {

        boolean over = false;
        while (!over) {
            over = playerTurn();
        } // while

        board.printBoard();
        
        System.out.println(((winner == white) ? "White" : "Black") + " has won the game!" );

    } // start

    // Determines if a player has won the game by putting a pawn at the other end of the board
    public boolean checkPawnWin() {
        boolean over = false;

        // Check if any white pawns are at the top of the board
        for (int i = 0; i < board.getBoardSize(); i++) {
            // White win
            if (board.getPiece(0, i) == 'W') {
                winner = white;
                over = true;
                break;
            }

            // Black win
            if (board.getPiece(7, i) == 'B') {
                winner = black;
                over = true;
                break;
            }
        }

        return over;
    } // isOver

    public boolean playerTurn() {
        boolean gameOver = false;
        
        Scanner scan;
        boolean anotherMove = true;
        int moveCount = 0;

        while (anotherMove) {

            board.printBoard();
            System.out.println("It is currently " + (currentPlayer == white ? "WHITE'S" : "BLACK'S") + " turn");

            scan = new Scanner(System.in);
            int input = 0;
            ArrayList<GameMove> moves = printAvailableMoves();
            int numMoves = moves.size();

            // If Current player could not make any moves during turn, opponent wins
            if (numMoves == 0 && moveCount == 0) {
                System.out.println((currentPlayer == white ? "White" : "Black") + " has no available moves!"); 
                gameOver = true;
                break;
            }
            // At least one move has already been made, but no moves are available
            else if (numMoves == 0) {
                break;
            }

            System.out.println((currentPlayer == white ? "WHITE" : "BLACK") + ", make your move!");

            // Handling user input
            try {
                System.out.println("Enter the number of the move option you would like to perform: ");
                input = scan.nextInt();
                if (input < 1 || input > numMoves) {
                    System.out.println("\n[!!!!!] Invalid input, try again! Select one of the option numbers provided [!!!!!]\n");
                    continue;
                }

                anotherMove = currentPlayer.executeMove(moves.get(input - 1), board, opponentPlayer);
                moveCount++;
                gameOver = checkPawnWin();
                if (gameOver) {
                    break;
                }
            }
            catch (Exception e) {
                System.out.println(e);
                System.out.println("\n[!!!!!] Invalid input, try again [!!!!!]\n");
                continue;
            }

        }; // while

        //scan.close();

        if (gameOver) {
            return gameOver;
        }

        // Clear past moves
        currentPlayer.endTurn();
        currentPlayer = (currentPlayer == white ? black : white);
        opponentPlayer = (opponentPlayer == white ? black : white);

        return gameOver;
    }

    public ArrayList<GameMove> printAvailableMoves() {
        ArrayList<GameMove> moves = board.getMoves(currentPlayer);

        System.out.println();
        for (int i = 0; i < moves.size(); i++) {
            GameMove move = moves.get(i);

            System.out.println("Option " + (i+1) + ": " + move.getMoveString());
        }
        System.out.println();

        return moves;
    }

    public static int colToIndex(char c) {

        int val = -1;
        switch (c) {
            case 'a': val = 0; break; 
            case 'b': val = 1; break; 
            case 'c': val = 2; break; 
            case 'd': val = 3; break; 
            case 'e': val = 4; break; 
            case 'f': val = 5; break; 
            case 'g': val = 6; break; 
            case 'h': val = 7; break; 
        }
        
        return val;
    } // colToIndex

    public static char indexToCol(int i) {

        char val = '?';

        switch (i) {
            case 0: val = 'a'; break;
            case 1: val = 'b'; break;
            case 2: val = 'c'; break;
            case 3: val = 'd'; break;
            case 4: val = 'e'; break;
            case 5: val = 'f'; break;
            case 6: val = 'g'; break;
            case 7: val = 'h'; break;
        }

        return val;
    } // indexToCol

} // Game
