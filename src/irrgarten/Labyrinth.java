
package irrgarten;

import java.util.ArrayList;

public class Labyrinth {
    static private final char BLOCK_CHAR = 'X';
    static private final char EMPTY_CHAR = '-';
    static private final char MONSTER_CHAR = 'M';
    static private final char COMBAT_CHAT = 'C';
    static private final char EXIT_CHAT = 'E';
    static private final int ROW = 0;
    static private final int COL = 0;
    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;
    
    //variables de relaciones
    private ArrayList<MonsterSquare> monsters;
    private LabyrinthSquare labyrinth[][]; //lo hago matriz para tener mejor rendimiento (hacer accesos directos)
    private ArrayList<PlayerSquare> players;
    
    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol){
        throw new UnsupportedOperationException();
    }
    
    public void spreadPlayers(Player players[]){
        throw new UnsupportedOperationException();
    }
    
    public boolean haveAWinner(){
        throw new UnsupportedOperationException();
    }
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
    
    public void addMonster(int row, int col, Monster monster){
        throw new UnsupportedOperationException();
    }
    
    public Monster putPlayer(Directions direction, Player player){
        throw new UnsupportedOperationException();
    }
    
    public void addBlock(Orientation orientation, int startRow, int startCol, int length){
        throw new UnsupportedOperationException();
    }
    
    public Directions[] validMoves(int row, int col){
        throw new UnsupportedOperationException();
    }
    
    private boolean posOK(int row, int col){
        throw new UnsupportedOperationException();
    }
    
    private boolean emptyPos(int row, int col){
        throw new UnsupportedOperationException();
    }
    
    private boolean combatPos(int row, int col){
        throw new UnsupportedOperationException();
    }
    
    private boolean canStepOn(int row, int col){
        throw new UnsupportedOperationException();
    }
    
    private void updateOldPos(int row, int col){
        throw new UnsupportedOperationException();
    }
    
    private int[] dir2Pos(int row, int col, Directions direction){
        throw new UnsupportedOperationException();
    }
    
    private int[] randomEmptyPos(){
        throw new UnsupportedOperationException();
    }
    
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player){
        throw new UnsupportedOperationException();
    }
    
    
    
    
    
    
    
    
    
    
    
}
