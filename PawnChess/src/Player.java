import java.util.ArrayList;

public class Player {
    /* Creates a new player object
    * 0 is white
    * 1 is black
    */

    private Game.Color color;
    //public ArrayList<String> pieceLocations = new ArrayList<String>();
    private char pieceLetter;
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    private ArrayList<GameMove> priorityMoves = new ArrayList<GameMove>();
    private ArrayList<GameMove> forwardMoves = new ArrayList<GameMove>();

    public Player(Game.Color color) {
        this.color = color;

        if (color == Game.Color.White) {
            pieces.add(new Piece(1, 0));
            pieces.add(new Piece(1, 1));
            pieces.add(new Piece(1, 2));
            pieces.add(new Piece(1, 3));
            pieces.add(new Piece(1, 4));
            pieces.add(new Piece(1, 5));
            pieces.add(new Piece(1, 6));
            pieces.add(new Piece(1, 7));

            pieceLetter = 'W';
        }
        else {
            pieces.add(new Piece(6, 0));
            pieces.add(new Piece(6, 1));
            pieces.add(new Piece(6, 2));
            pieces.add(new Piece(6, 3));
            pieces.add(new Piece(6, 4));
            pieces.add(new Piece(6, 5));
            pieces.add(new Piece(6, 6));
            pieces.add(new Piece(6, 7));

            pieceLetter = 'B';
        }
    } // Player

    public int getNumPieces() {
        return this.pieces.size();
    }

    public ArrayList<Piece> getPieces() {
        return this.pieces;
    }


    public int getPiece(int row, int col) {
        int index = -1;
        for (int i = 0; i < getNumPieces(); i++) {
            if (pieces.get(i).getRow() == row && pieces.get(i).getCol() == col) {
                index = i;
                break;
            }
        }

        return index;
    }

    public Game.Color getColor() {
        return this.color;
    }

    public char getPieceLetter() {
        return this.pieceLetter;
    }

    public void endTurn() {
        // set the moved status of all pieces to false
        for (int i = 0; i < pieces.size(); i++) {
            pieces.get(i).setStatus(false);
        } // for

        clearMoves();
    }

    public void clearMoves() {
        forwardMoves.clear();
        priorityMoves.clear();
    }

    public void addForwardMove(GameMove move) {
        forwardMoves.add(move);
    }

    public void addPriorityMove(GameMove move) {
        priorityMoves.add(move);
    }

    public ArrayList<GameMove> getMoves() {
        // if priorityMoves array empty return forwardMoves
        if (priorityMoves.size() != 0) {
            return priorityMoves;
        }

        // only return if there are no priorityMoves available
        return forwardMoves;
    }

    public boolean executeMove(GameMove move, Board board, Player opponent) {
        boolean additionalMove = false;

        // Need to adjust the toColumn to account for the reversed board indexing
        // Use these whenever indexing rows from the board
        int fromRow = board.getBoardSize() - move.getFromRow() - 1;
        int toRow = board.getBoardSize() - move.getToRow() - 1;

        // Capturing/priority/diagonal move
        if (move.isCapturing()) {
            capturePiece(opponent, board, move.getFromRow(), move.getFromCol(), move.getToRow(), move.getToCol());
            additionalMove = true;
        }
        // Forward move
        else {
            movePiece(getPiece(move.getFromRow(), move.getFromCol()), move.getToRow(), move.getToCol());
            board.setPiece(toRow, move.getToCol(), pieceLetter);
            board.setPiece(fromRow, move.getFromCol(), '-');
        }

        return additionalMove;
    }

    public void movePiece(int index, int row, int col) {
        pieces.get(index).setPos(row, col);
        pieces.get(index).setStatus(true);
    }

    
    public void capturePiece(Player captive, Board board, int fromRow, int fromCol, int toRow, int toCol) {
        captive.removePiece(captive.getPiece(toRow, toCol));
        movePiece(getPiece(fromRow, fromCol), toRow, toCol);
        board.setPiece((board.getBoardSize() - fromRow - 1), fromCol, '-');
        board.setPiece(board.getBoardSize() - toRow - 1, toCol, this.pieceLetter);
    }

    
    public void removePiece(int index) {
        pieces.remove(index);
    }
    
}
