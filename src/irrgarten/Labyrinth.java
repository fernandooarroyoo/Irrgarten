
package irrgarten;

import static irrgarten.Directions.UP;
import java.util.ArrayList;

public class Labyrinth {
    static private final char BLOCK_CHAR = 'X';
    static private final char EMPTY_CHAR = '-';
    static private final char MONSTER_CHAR = 'M';
    static private final char COMBAT_CHAR = 'C';
    static private final char EXIT_CHAR = 'E';
    static private final int ROW = 0;
    static private final int COL = 0;
    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;
    
    //variables de relaciones. Son relaciones de composición -> NO TIENE SENTIDO TENER UN LABERINTO SIN LAS CASILLAS
    private Monster monsters[][];
    private char labyrinth[][]; //lo hago matriz para tener mejor rendimiento (hacer accesos directos), aunque es mas ineficiente en almacenamiento
    private Player players[][];
   
    
    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol){
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
        
        this.labyrinth = new char[nRows][nCols];
        this.monsters = new Monster[nRows][nCols];
        this.players = new Player[nRows][nCols];
                
        for(int i = 0 ; i<nRows ; i++){
            for(int j=0; j<nCols ; j++){
                this.labyrinth[i][j] = Labyrinth.EMPTY_CHAR;
                this.monsters[i][j] = null;
                this.players[i][j] = null;
                
                
                
            }
        }
        
        this.labyrinth[exitRow][exitCol] = Labyrinth.EXIT_CHAR;
       
    }
    
    public void spreadPlayers(ArrayList<Player> players){
        for(Player player:players){
            int[] pos = randomEmptyPos(); 
            putPlayer2D(player.getRow(),player.getCol(),pos[0],pos[1],player);
        }
    }
    
    public boolean haveAWinner(){
        if(this.players[this.exitRow][this.exitCol] != null){
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public String toString() {
        String laberinto = "";
        
        for(int i = 0 ; i<nRows;++i){
            for(int j = 0; j< nCols; ++j){
                laberinto += this.labyrinth[i][j] + " ";
            }
            laberinto += "\n";
        }
        
        return laberinto;
    }
    
    public void addMonster(int row, int col, Monster monster){
        if(posOk(row,col) && emptyPos(row,col)){
            this.labyrinth[row][col] = this.MONSTER_CHAR;
            this.monsters[row][col] = monster;
            monster.setPos(row, col);
        }
    }
    
    public Monster putPlayer(Directions direction, Player player){
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        int[] newPos = dir2Pos(oldRow,oldCol,direction);
        Monster monster = putPlayer2D(oldRow,oldCol,newPos[0],newPos[1],player);
        return monster;
    }
    
    public void addBlock(Orientation orientation, int startRow, int startCol, int length){
        int incRow;
        int incCol;
        if(orientation == Orientation.VERTICAL){
            incRow = 1;
            incCol = 0;
        }else{
            incRow = 0;
            incCol = 1;
        }
        
        int row = startRow;
        int col = startCol;
        
        while(posOk(row,col) && emptyPos(row,col) && length > 0){
            this.labyrinth[row][col] = BLOCK_CHAR;
            length -= 1;
            row += incRow;
            col += incCol;
        }
    
    }
    
    public ArrayList<Directions> validMoves(int row, int col){
        ArrayList<Directions> output = new ArrayList<>();
        
        if(canStepOn(row+1,col)){
            output.add(Directions.DOWN);
        }
        if(canStepOn(row-1,col)){
            output.add(Directions.UP);
        }
        if(canStepOn(row,col+1)){
            output.add(Directions.RIGHT);
        }
        if(canStepOn(row,col-1)){
            output.add(Directions.LEFT);
        }
        
        return output;
    }
    
            
    private boolean posOk(int row, int col){
        return(row<this.nRows && row>= 0 && col < nCols && col >= 0 );
    }
    
    private boolean emptyPos(int row, int col){
        return(this.labyrinth[row][col] == this.EMPTY_CHAR);
    }
    
    private boolean monsterPos(int row, int col){
        return(this.labyrinth[row][col] == this.MONSTER_CHAR);
    }
    
    private boolean combatPos(int row, int col){
        return(this.labyrinth[row][col] == this.COMBAT_CHAR);
    }
    
     private boolean exitPos(int row, int col){
        return(this.labyrinth[row][col] == this.EXIT_CHAR);
    }
    
    private boolean canStepOn(int row, int col){
        return(posOk(row,col) && (emptyPos(row,col) || monsterPos(row,col) ||exitPos(row,col)));
    }
    
    private void updateOldPos(int row, int col){
        if(posOk(row,col) && this.labyrinth[row][col]==this.COMBAT_CHAR){
            this.labyrinth[row][col]=this.MONSTER_CHAR;
        }
        else{
            this.labyrinth[row][col] = this.EMPTY_CHAR;
        }
    }
    
    private int[] dir2Pos(int row, int col, Directions direction){
        int sol[] = new int[2];
        switch(direction){
            case UP:
                sol[0]=row-1;
                sol[1]=col;
                break;
            case DOWN:
                sol[0]=row+1;
                sol[1]=col;
                break;
            case RIGHT:
                sol[0]=row;
                sol[1]=col+1;
                break;
            case LEFT:
                sol[0]=row;
                sol[1]=col-1;
                break;
        }
        return sol;
    }
    
    private int[] randomEmptyPos(){
        int[] newPos = new int[]{Dice.randomPos(this.nRows), Dice.randomPos(this.nCols)};
        
        while(this.labyrinth[newPos[0]][newPos[1]] != EMPTY_CHAR){
           
            newPos[0] = Dice.randomPos(this.nRows);
            newPos[1] = Dice.randomPos(this.nCols);
        }
        
        return newPos;
    }
    
    private Monster putPlayer2D(int oldRow , int oldCol, int row, int col, Player player){
        Monster output = null;
        
        if (canStepOn(row,col)){
            if (posOk(oldRow,oldCol)){
                Player p = this.players[oldRow][oldCol];
                if(p==player){
                    updateOldPos(oldRow,oldCol);
                    this.players[oldRow][oldCol] = null;
                }
              }
        
        
            boolean monsterPos = monsterPos(row,col);
        
            if(monsterPos){
                this.labyrinth[row][col] = COMBAT_CHAR;
                output = this.monsters[row][col];
            }else{
                char number = player.getNumber();
                this.labyrinth[row][col] = number;
            }
        
            this.players[row][col] = player;
            player.setPos(row,col);
        }
        
        return output;
        
    }
      
}
