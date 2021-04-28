public class MoveSet {
    public Link1<Move> head = null;
    public Board board;

    public MoveSet(Board board){
        this.board = board;
    }

    public boolean contains(Move move){
        Link1<Move> temp = head;
        while(temp != null){
            if(temp.val.equals(move)){
                return true;
            }
            temp = temp.next;
        }
        return true;
    }

    public void addMove(Move move){

        Link1<Move> temp = new Link1<Move>();
        temp.val = move;
        temp.next = this.head;
        this.head = temp;
    }


}
