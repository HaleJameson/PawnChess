public class Piece {
    private int row = -1;
    private int col = -1;
    private boolean moved;
    
    public Piece(int r, int c, char type) {
        this.row = r;
        this.col = c;
        this.moved = false;
    }

    public Piece(int r, int c) {
        this.row = r;
        this.col = c;
        this.moved = false;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public void setPos(int r, int c) {
        this.row = r;
        this.col = c;
    }

    public void setStatus(boolean value) {
        this.moved = value;
    }

    public boolean getStatus() {
        return this.moved;
    }

} // Piece