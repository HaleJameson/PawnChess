import java.util.ArrayList;

public class Board {

    private int boardSize = 8;
    private char[][] board = new char[boardSize][boardSize];
    
    // Constructor for the board class
    public Board() {
        for (int i = 0; i < boardSize; i++) {
            board[0][i] = '-';
            board[1][i] = 'B';
            board[2][i] = '-';
            board[3][i] = '-';
            board[4][i] = '-';
            board[5][i] = '-';
            board[6][i] = 'W';
            board[7][i] = '-';
        }

    }

    // Prints the current state of the board
    public void printBoard() {
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                if (c == 0) {
                    System.out.print(boardSize - r + " | ");
                }
                System.out.print(board[r][c] + " | ");
            } // for columns

            System.out.println();
            if (r == boardSize-1) {
                System.out.print("    a   b   c   d   e   f   g   h");
            } // if
        } // for rows
        System.out.println("\n");
    } // printBoard


    // Gets the moves available to a player based on their current pieces, adds it to priorityMoves and forwardMoves
    public ArrayList<GameMove> getMoves(Player player) {
        player.clearMoves();

        int direction = (player.getColor() == Game.Color.Black ? -1 : 1);
        char playerChar = player.getPieceLetter();

        // loop through every piece for this player
        for (int i = 0; i < player.getNumPieces(); i++) {

            ArrayList<Piece> pieces = player.getPieces();
            GameMove move;

            // Check that the piece is available to move
            if (pieces.get(i).getStatus() == true) {
                continue;
            }

            int pieceRow = pieces.get(i).getRow();
            int pieceCol = pieces.get(i).getCol();
            int moveRow = pieceRow + direction;

            // This must be used instead of moveRow whenever indexing the board
            int boardRow = boardSize - (moveRow) - 1;

            // Checks 3 locations, (col-1, col, col+1)
            for (int c = -1; c < 2; c++) {
                int moveCol = pieceCol + c;

                // Make sure we're within range
                if (moveRow < 0 || moveRow > 7 || moveCol < 0 || moveCol > 7) {
                    continue;
                }

                // Add any forward moves
                if (moveCol == pieceCol) {
                    if (board[boardRow][moveCol] == '-') {
                        move = new GameMove(player, pieceRow, pieceCol, moveRow, moveCol, false);
                        player.addForwardMove(move);
                    } // if
                } // if

                // Add any priority moves
                else {
                    if (board[boardRow][moveCol] != '-' && board[boardRow][moveCol] != playerChar) {
                        move = new GameMove(player, pieceRow, pieceCol, moveRow, moveCol, true);
                        player.addPriorityMove(move);
                    }
                } // else

            } // for
        } // for

        
        ArrayList<GameMove> moves = player.getMoves();  
        return moves;
    } // getMoves

    public char getPiece(int row, int col) {
        return board[row][col];
    }

    public void setPiece(int row, int col, char type) {
        // Forward move

        this.board[row][col] = type;
    }

    public int getBoardSize() {
        return this.boardSize;
    }
} // Board
