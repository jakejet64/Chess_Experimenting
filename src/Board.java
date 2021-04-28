public class Board {
    public Piece[][] pieces = new Piece[8][8];
    /*

    7   r | k | b | q | K | b | k | r
    6   p | p | p | p | p | p | p | p
    5   N | N | N | N | N | N | N | N
    4   N | N | N | N | N | N | N | N
    3   N | N | N | N | N | N | N | N
    2   N | N | N | N | N | N | N | N
    1   p | p | p | p | p | p | p | p
    0   r | k | b | q | K | b | k | r

        0   1   2   3   4   5   6   7

     */

    public Board(){
        //initialize white pieces
        pieces[0][0] = new Piece(true, "rook");
        pieces[0][1] = new Piece(true, "knight");
        pieces[0][2] = new Piece(true, "bishop");
        pieces[0][3] = new Piece(true, "queen");
        pieces[0][4] = new Piece(true, "king");
        pieces[0][5] = new Piece(true, "bishop");
        pieces[0][6] = new Piece(true, "knight");
        pieces[0][7] = new Piece(true, "rook");
        //initialize black pieces
        pieces[7][0] = new Piece(false, "rook");
        pieces[7][1] = new Piece(false, "knight");
        pieces[7][2] = new Piece(false, "bishop");
        pieces[7][3] = new Piece(false, "queen");
        pieces[7][4] = new Piece(false, "king");
        pieces[7][5] = new Piece(false, "bishop");
        pieces[7][6] = new Piece(false, "knight");
        pieces[7][7] = new Piece(false, "rook");
        //initialize pawns
        for(int i = 0; i < 8; i++){
            pieces[1][i] = new Piece(true, "pawn");
            pieces[6][i] = new Piece(false, "pawn");
        }
        //initialize blank spaces
        for(int i = 2; i < 6; i++){
            for(int j = 0; j < 8; j++){
                pieces[i][j] = null;
            }
        }
    }

    public String toString(){
        String ret = "";

        for(int i = 7; i >= 0; i--){ // iterates through rows
            ret += (i+1) + " ";
            for(int j = 0; j < 8; j++){ // iterates through columns
                if(pieces[i][j] != null){
                    ret += "| " + pieces[i][j] + " ";
                }else{
                    ret += "| ** ";
                }
            }
            ret += "\n";
        }
        ret += "    a    b    c    d    e    f    g    h";
        return ret;
    }


}
