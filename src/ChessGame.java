public class ChessGame {
    private Board board;
    private boolean isOver;

    private String Winner;
    private String WinCondition;

    private Player white;
    private Player black;

    public ChessGame(Player white, Player black){
        this.board = new Board();
        this.isOver = false;
        this.white = white;
        this.black = black;
    }

    public void Start(){
        boolean whiteTurn = true;

        while(!isOver){

            if(whiteTurn){
                Move move = white.GenerateMove(board);
                MoveSet validMoves = new MoveSet(this.board);
                generateValidMoves(validMoves, true);
                if(validMoves.contains(move)){
                    makeMove(move);
                    generateValidMoves(validMoves, false);
                    checkBlackStaleMate(validMoves);
                    checkCheckMate(validMoves);
                }else{
                    WinCondition = "forfeit";
                    Winner = "black";
                    isOver = true;
                }
                whiteTurn = false;
            }else{
                Move move = black.GenerateMove(board);
                MoveSet validMoves = new MoveSet(this.board);
                generateValidMoves(validMoves, false);
                if(validMoves.contains(move)){
                    makeMove(move);
                    generateValidMoves(validMoves, true);
                    checkWhiteStaleMate(validMoves);
                    checkCheckMate(validMoves);
                }else{
                    WinCondition = "forfeit";
                    Winner = "white";
                    isOver = true;
                }
                whiteTurn = true;
            }
        }

        System.out.println(Winner + " wins on condition of " + WinCondition);
    }

    private void generateValidMoves(MoveSet validMoves, boolean whiteTurn) {
        if (whiteTurn) {
            for (byte r = 0; r < 8; r++) {
                for (byte c = 0; c < 8; c++) {
                    if (board.pieces[r][c] != null && board.pieces[r][c].white) {
                        if (board.pieces[r][c].type == 'x') { // king
                            generateValidKingMoves(validMoves, r, c);
                        } else if (board.pieces[r][c].type == 'q') { // queen
                            generateValidQueenMoves(validMoves, r, c);
                        } else if (board.pieces[r][c].type == 'p') { // pawn
                            generateValidPawnMoves(validMoves, r, c);
                        } else if (board.pieces[r][c].type == 'r') { // rook
                            generateValidRookMoves(validMoves, r, c);
                        } else if (board.pieces[r][c].type == 'b') { // bishop
                            generateValidBishopMoves(validMoves, r, c);
                        } else { // knight
                            generateValidKnightMoves(validMoves, r, c);
                        }
                    }
                }
            }
        } else {
            for (byte r = 0; r < 8; r++) {
                for (byte c = 0; c < 8; c++) {
                    if (board.pieces[r][c] != null && !board.pieces[r][c].white) {
                        if (board.pieces[r][c].type == 'x') { // king
                            generateValidKingMoves(validMoves, r, c);
                        } else if (board.pieces[r][c].type == 'q') { // queen
                            generateValidQueenMoves(validMoves, r, c);
                        } else if (board.pieces[r][c].type == 'p') { // pawn
                            generateValidPawnMoves(validMoves, r, c);
                        } else if (board.pieces[r][c].type == 'r') { // rook
                            generateValidRookMoves(validMoves, r, c);
                        } else if (board.pieces[r][c].type == 'b') { // bishop
                            generateValidBishopMoves(validMoves, r, c);
                        } else { // knight
                            generateValidKnightMoves(validMoves, r, c);
                        }
                    }
                }
            }
        }
    }

    private void generateValidKnightMoves(MoveSet validMoves, byte row, byte col){
        for(byte i = -2; i <= 2; i++){
            for(byte j = -2; j <= 2; j++){
                if(Math.abs(i) != Math.abs(j) && i != 0 && j != 0){
                    if(inBounds((byte)(row + i), (byte)(col + j)) &&
                            (board.pieces[row][col].type != board.pieces[row + i][col + j].type)){
                        validMoves.addMove(new Move(row, col, (byte) (row + i), (byte) (col + j)));
                    }
                }
            }
        }
    }
    private void generateValidBishopMoves(MoveSet validMoves, byte row, byte col) {
        // Check going up-right
        byte tempRow = (byte)(row + 1); byte tempCol = (byte)(col + 1);
        while(inBounds(tempRow, tempCol)){
            if(board.pieces[tempRow][tempCol] == null){ //trying to move to empty space
                validMoves.addMove(new Move(row, col, tempRow, tempCol)); // add move
            }else if(board.pieces[row][col].white == board.pieces[tempRow][tempCol].white){ // trying to move to white space
                tempRow = 20; // terminate
            }else{ // trying to take
                validMoves.addMove(new Move(row, col, tempRow, tempCol)); // add move
                tempRow = 20; // and terminate
            }
            tempRow++; tempCol++;
        }

        //check going down-right
        tempRow = (byte)(row - 1); tempCol = (byte)(col + 1);
        while(inBounds(tempRow, tempCol)){
            if(board.pieces[tempRow][tempCol] == null){ //trying to move to empty space
                validMoves.addMove(new Move(row, col, tempRow, tempCol)); // add move
            }else if(board.pieces[row][col].white == board.pieces[tempRow][tempCol].white){ // trying to move to white space
                tempRow = 20; // terminate
            }else{ // trying to take
                validMoves.addMove(new Move(row, col, tempRow, tempCol)); // add move
                tempRow = 20; // and terminate
            }
            tempRow--; tempCol++;
        }

        //check going up-left
        tempRow = (byte)(row + 1); tempCol = (byte)(col - 1);
        while(inBounds(tempRow, tempCol)){
            if(board.pieces[tempRow][tempCol] == null){ //trying to move to empty space
                validMoves.addMove(new Move(row, col, tempRow, tempCol)); // add move
            }else if(board.pieces[row][col].white == board.pieces[tempRow][tempCol].white){ // trying to move to white space
                tempRow = 20; // terminate
            }else{ // trying to take
                validMoves.addMove(new Move(row, col, tempRow, tempCol)); // add move
                tempRow = 20; // and terminate
            }
            tempRow++; tempCol--;
        }

        //check going down-left
        tempRow = (byte)(row - 1); tempCol = (byte)(col - 1);
        while(inBounds(tempRow, tempCol)){
            if(board.pieces[tempRow][tempCol] == null){ //trying to move to empty space
                validMoves.addMove(new Move(row, col, tempRow, tempCol)); // add move
            }else if(board.pieces[row][col].white == board.pieces[tempRow][tempCol].white){ // trying to move to white space
                tempRow = 20; // terminate
            }else{ // trying to take
                validMoves.addMove(new Move(row, col, tempRow, tempCol)); // add move
                tempRow = 20; // and terminate
            }
            tempRow--; tempCol--;
        }
    }
    private void generateValidRookMoves(MoveSet validMoves, byte row, byte col) {
        // Check going up
        byte tempRow = (byte)(row + 1); byte tempCol = col;
        while(inBounds(tempRow, tempCol)){
            if(board.pieces[tempRow][tempCol] == null){ //trying to move to empty space
                validMoves.addMove(new Move(row, col, tempRow, tempCol)); // add move
            }else if(board.pieces[row][col].white == board.pieces[tempRow][tempCol].white){ // trying to move to white space
                tempRow = 20; // terminate
            }else{ // trying to take
                validMoves.addMove(new Move(row, col, tempRow, tempCol)); // add move
                tempRow = 20; // and terminate
            }
            tempRow++;
        }

        // Check going down
        tempRow = (byte)(row - 1); tempCol = col;
        while(inBounds(tempRow, tempCol)){
            if(board.pieces[tempRow][tempCol] == null){ //trying to move to empty space
                validMoves.addMove(new Move(row, col, tempRow, tempCol)); // add move
            }else if(board.pieces[row][col].white == board.pieces[tempRow][tempCol].white){ // trying to move to white space
                tempRow = 20; // terminate
            }else{ // trying to take
                validMoves.addMove(new Move(row, col, tempRow, tempCol)); // add move
                tempRow = 20; // and terminate
            }
            tempRow--;
        }

        // Check going right
        tempRow = row; tempCol = (byte)(col + 1);
        while(inBounds(tempRow, tempCol)){
            if(board.pieces[tempRow][tempCol] == null){ //trying to move to empty space
                validMoves.addMove(new Move(row, col, tempRow, tempCol)); // add move
            }else if(board.pieces[row][col].white == board.pieces[tempRow][tempCol].white){ // trying to move to white space
                tempRow = 20; // terminate
            }else{ // trying to take
                validMoves.addMove(new Move(row, col, tempRow, tempCol)); // add move
                tempRow = 20; // and terminate
            }
            tempCol++;
        }

        // Check going left
        tempRow = row; tempCol = (byte)(col - 1);
        while(inBounds(tempRow, tempCol)){
            if(board.pieces[tempRow][tempCol] == null){ //trying to move to empty space
                validMoves.addMove(new Move(row, col, tempRow, tempCol)); // add move
            }else if(board.pieces[row][col].white == board.pieces[tempRow][tempCol].white){ // trying to move to white space
                tempRow = 20; // terminate
            }else{ // trying to take
                validMoves.addMove(new Move(row, col, tempRow, tempCol)); // add move
                tempRow = 20; // and terminate
            }
            tempCol--;
        }
    }
    private void generateValidPawnMoves(MoveSet validMoves, byte row, byte col) {
        if(board.pieces[row][col].white){
            if(row != 7 && board.pieces[row + 1][col] == null){
                validMoves.addMove(new Move(row, col, (byte) (row + 1),col));
                if(row == 1 && board.pieces[row + 2][col] == null){
                    validMoves.addMove(new Move(row, col, (byte) (row + 2),col));
                }
            }
            if(inBounds((byte)(row+1), (byte)(col - 1)) &&
                !board.pieces[row+1][col-1].white){
                validMoves.addMove(new Move(row, col, (byte)(row + 1),(byte)(col-1)));
            }
            if(inBounds((byte)(row+1), (byte)(col + 1)) &&
                    !board.pieces[row+1][col+1].white){
                validMoves.addMove(new Move(row, col, (byte)(row + 1),(byte)(col+1)));
            }
        }else{
            if(row != 0 && board.pieces[row - 1][col] == null){
                validMoves.addMove(new Move(row, col, (byte) (row - 1),col));
                if(row == 6 && board.pieces[row - 2][col] == null){
                    validMoves.addMove(new Move(row, col, (byte) (row - 2),col));
                }
            }
            if(inBounds((byte)(row-1), (byte)(col - 1)) &&
                    board.pieces[row-1][col-1].white){
                validMoves.addMove(new Move(row, col, (byte)(row - 1),(byte)(col-1)));
            }
            if(inBounds((byte)(row-1), (byte)(col + 1)) &&
                    board.pieces[row-1][col+1].white){
                validMoves.addMove(new Move(row, col, (byte)(row - 1),(byte)(col+1)));
            }
        }
    }
    private void generateValidQueenMoves(MoveSet validMoves, byte row, byte col) {
        generateValidBishopMoves(validMoves, row, col);
        generateValidRookMoves(validMoves, row, col);
    }
    private void generateValidKingMoves(MoveSet validMoves, byte row, byte col) {
        for(byte r = (byte)(row - 1); r <= (row+1); row++){
            for(byte c = (byte)(col - 1); c <= (col+1); col++){
                if(inBounds(r, c)){
                    if(board.pieces[r][c] == null){
                        validMoves.addMove(new Move(row, col, r, c));
                    }else if(board.pieces[r][c].white != board.pieces[row][col].white){
                        validMoves.addMove(new Move(row, col, r, c));
                    }
                }
            }
        }
    }

    private boolean inBounds(byte row, byte col) {
        return(
                row >= 0 &&
                row <= 7 &&
                col >= 0 &&
                col <= 7
                );
    }

    private void checkBlackStaleMate(MoveSet validMoves) {
        Link1<Move> temp = validMoves.head;

        boolean safeMoveFound = false;

        while(temp.next != null && !safeMoveFound){
            if(!board.pieces[temp.val.fromRow][temp.val.fromCol].white){

            }
            temp = temp.next;
        }
    }

    private void checkWhiteStaleMate(MoveSet validMoves) {
    }

    private void checkCheckMate(MoveSet validMoves){
        //TODO
    }

    private void makeMove(Move move) {
        board.pieces[move.toRow][move.toCol] = board.pieces[move.fromRow][move.fromCol];
        board.pieces[move.fromRow][move.fromCol] = null;
    }

}