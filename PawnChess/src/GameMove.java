public class GameMove {
   private int fromRow;
   private int fromCol;
   private int toRow;
   private int toCol;
   private boolean capturing;

   
   public GameMove(Player player, Piece piece, int fromRow, int fromCol, int toRow, int toCol, boolean capture) {
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
        this.capturing = capture;
   }

   public GameMove(Player player, int fromRow, int fromCol, int toRow, int toCol, boolean capture) {
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
        this.capturing = capture;
}

   public int getFromRow() {
       return this.fromRow;
   }

   public int getFromCol() {
       return this.fromCol;
   }

   public int getToRow() {
       return this.toRow;
   }

   public int getToCol() {
       return this.toCol;
   }

   public boolean isCapturing() {
       return this.capturing;
   }

   public String getMoveString() {
    String str = "";
    String origin = "" + (getFromRow() + 1) + Game.indexToCol(getFromCol());
    String destination = "" + (getToRow() + 1) + Game.indexToCol(getToCol());

    str = origin + " --> " + destination;

    if (this.capturing) {
        str += " | CAPTURE";
    }

    return str;

   }
}
