public class Move {
    public byte fromRow;
    public byte fromCol;
    public byte toRow;
    public byte toCol;

    public Move(byte fromRow, byte fromCol, byte toRow, byte toCol){
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
    }

    public boolean equals(Move move){
        return( this.fromRow == move.fromRow &&
                this.fromCol == move.fromCol &&
                this.toRow == move.toRow &&
                this.toCol == move.toCol
                );
    }
}
