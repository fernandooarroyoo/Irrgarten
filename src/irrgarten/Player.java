
package irrgarten;

import java.util.ArrayList;

public class Player {
    static private final int MAX_WEAPONS = 2;
    static private final int MAX_SHIELDS = 3;
    static private final int INITIAL_HEALTH = 10;
    static private final int HITS2LOSE = 3;
    static private final int INVALID_POSITION = -1;
    private String name;
    private char number;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    private int consecutiveHits;
    
    private ArrayList<Weapon> weapons; //esto son punteros que hay que inicializarlos
    private ArrayList<Shield> shields;
    
    
    
    public Player(char number, float intelligence, float strength){
        
        this.name = "Player #" + number;
        this.number = number;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = INITIAL_HEALTH;
        this.row = INVALID_POSITION;
        this.col = INVALID_POSITION;
        this.consecutiveHits = 0;
        
        //atributos de relacion
        this.weapons = new ArrayList<>();
        this.shields = new ArrayList<>();
    }
    
    //EJERCICIO DE CLASE
    /*public void takeWeapon(Weapon weapon){
        weapon.setPlayer(this);
        this.weapons.add(weapon);
    }
    */
    
    public void resurrect(){
        throw new UnsupportedOperationException();
    }
    
    public int getRow(){
        throw new UnsupportedOperationException();
    }
    
    public int getCol(){
        throw new UnsupportedOperationException();
    }
    
    public char getNumber(){
        throw new UnsupportedOperationException();
    }
    
    public void setPos(int row, int col){
        throw new UnsupportedOperationException();
    }
    
    public boolean dead(){
        throw new UnsupportedOperationException();
    }
    
    public Directions move(Directions direction, Directions[] validMoves){
        throw new UnsupportedOperationException();
    }
    
    public float attack(){
        throw new UnsupportedOperationException();
    }
    
    public boolean defend(float receivedAttack){
        throw new UnsupportedOperationException();
    }
    
    public void receiveReward(){
        throw new UnsupportedOperationException();
    }
    
    public String toString(){
        throw new UnsupportedOperationException();
    }
    
    private void receiveWeapon(Weapon w){
        throw new UnsupportedOperationException();
    }
    
    private void receiveShield(Shield s){
        throw new UnsupportedOperationException();
    }
    
    private Weapon newWeapon(){
        throw new UnsupportedOperationException();
    }
    
    private Shield newShield(){
        throw new UnsupportedOperationException();
    }
    
    private float sumWeapons(){
        float sum = 0;
        for(Weapon weapon: this.weapons){
            sum += weapon.attack();
        }
        return sum;
    }
    
    private float sumShields(){
        throw new UnsupportedOperationException();
    }
    
    private float defensiveEnergy(){
        throw new UnsupportedOperationException();
    }
    
    private boolean manageHit(float receiveAttack){
        throw new UnsupportedOperationException();
    }
    
    private void getWounded(){
        throw new UnsupportedOperationException();
    }
    
    private void incConsecutiveHits(){
        throw new UnsupportedOperationException();
    }
}
