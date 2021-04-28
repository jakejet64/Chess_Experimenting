public class Piece {
    public boolean white;
    public char type;

    public Piece(Boolean white, String type){
        this.white = white;
        if(type.equals("pawn")){
            this.type = 'p';
        }else if(type.equals("rook")){
            this.type = 'r';
        }else if(type.equals("bishop")){
            this.type = 'b';
        }else if(type.equals("knight")){
            this.type = 'k';
        }else if(type.equals("queen")){
            this.type = 'q';
        }else if(type.equals("king")){
            this.type = 'x';
        }else{
            System.out.println("Cannot initiate piece with value: " + type);
        }
    }

    public String toString(){
        String ret = "";
        if(white){
            ret += "w";
        }else{
            ret += "b";
        }
        if(this.type == 'x'){
            ret += "k";
        }else{
            ret+= this.type + "";
        }

        return ret;
    }
}
