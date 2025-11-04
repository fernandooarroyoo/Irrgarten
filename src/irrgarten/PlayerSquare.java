
package irrgarten;


public class PlayerSquare {
   private int row;
   private int col;
   private Player player;

    
    public PlayerSquare(int row, int col, Player player) {
        this.row = row;
        this.col = col;
        this.player = player;
        
    }
    
    public int getRow(){
        return this.row;
    }
    
    public int getCol(){
        return this.col;
    }
}
