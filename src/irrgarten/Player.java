
package irrgarten;

import java.util.ArrayList;
import java.util.Arrays;

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
        this.weapons.clear();
        this.shields.clear();
        this.health = INITIAL_HEALTH;
        this.consecutiveHits = 0;
    }
    
    public int getRow(){
        return this.row;
    }
    
    public int getCol(){
        return this.col;
    }
    
    public char getNumber(){
        return this.number;
    }
    
    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public boolean dead(){
        if (this.health <= 0){
            return true;
        }else{
            return false;
        }
    }
    
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        int size = validMoves.size();
        boolean contained = Arrays.asList(validMoves).contains(direction);
        
        if(size > 0 && !contained){
            return validMoves.get(0);
        }else{
            return direction;
        }
    }
    
    
    public float attack(){
        return this.sumWeapons() * this.strength;
    }
    
    public boolean defend(float receivedAttack){
        return this.manageHit(receivedAttack);
    }
    
    public void receiveReward(){
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        
        for(int i=0; i< wReward ; ++i){
            Weapon w = newWeapon();
            receiveWeapon(w);
        }
        
        for(int i=0; i< sReward ; ++i){
            Shield s = newShield();
            receiveShield(s);
        }
    }
    
    @Override
    public String toString(){
        return "Player{"
                + "name='" + name + '\''
                + ", number=" + number
                + ", intelligence=" + intelligence
                + ", strength=" + strength
                + ", health=" + health
                + ", row=" + row
                + ", col=" + col
                + ", consecutiveHits=" + consecutiveHits
                + ", weapons=" + weapons
                + ", shields=" + shields
                + '}';
    }
    
    private void receiveWeapon(Weapon w){
         for (int i=weapons.size()-1; i>=0; i--){
            boolean discard = weapons.get(i).discard();
            if (discard)
                weapons.remove(weapons.get(i));
        }
        
        int size = weapons.size();
        if (size<MAX_WEAPONS)
            weapons.add(w);
    }
    
    private void receiveShield(Shield s){
         for (int i=shields.size()-1; i>=0; i--){
            boolean discard = shields.get(i).discard();
            if (discard){
                shields.remove(shields.get(i));
            }
            
            int size = shields.size();
            if (size<MAX_SHIELDS){
                shields.add(s);
            }
        }
    }
    
    private Weapon newWeapon(){
        return new Weapon(Dice.weaponPower(), Dice.usesLeft());
    }
    
    private Shield newShield(){
        return new Shield(Dice.shieldPower(), Dice.usesLeft());
    }
    
    private float sumWeapons(){
        float sum = 0;
        for(Weapon weapon: this.weapons){
            sum += weapon.attack();
        }
        return sum;
    }
    
    private float sumShields(){
        float sum = 0;
        for(Shield shield: this.shields){
            sum += shield.protect();
        }
        return sum;
    }
    
    private float defensiveEnergy(){
        throw new UnsupportedOperationException();
    }
    
    private boolean manageHit(float receivedAttack){
        float defense = this.defensiveEnergy();
        boolean lose;
        if (defense < receivedAttack){
            this.gotWounded();
            this.incConsecutiveHits();
        }
        else 
            this.consecutiveHits = 0;
        
        if ((consecutiveHits == HITS2LOSE) || (this.dead())){
            this.consecutiveHits = 0;
            lose = true;
        }
        else
            lose = false;
        
        return lose;
    }
    
    private void gotWounded(){
        this.health -= 1;
    }
    
    private void incConsecutiveHits(){
        this.consecutiveHits += 1;
    }
}
