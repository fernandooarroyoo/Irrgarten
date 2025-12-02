
package irrgarten;

import java.util.ArrayList;
import java.util.Arrays;

public class Player extends LabyrinthCharacter {
    static private final int MAX_WEAPONS = 2;
    static private final int MAX_SHIELDS = 3;
    static private final int INITIAL_HEALTH = 10;
    static private final int HITS2LOSE = 3;
    static private final int INVALID_POSITION = -1;
  
    private char number;
    
    private int consecutiveHits;
    
    private ArrayList<Weapon> weapons; //esto son punteros que hay que inicializarlos
    private ArrayList<Shield> shields;
    
    private WeaponCardDeck weaponCardDeck;
    private ShieldCardDeck shieldCardDeck;
    
    public Player(char number, float intelligence, float strength){
        
        super("Player",intelligence,strength,INITIAL_HEALTH);
        this.number = number;
        
        this.consecutiveHits = 0;
        
        //atributos de relacion
        this.weapons = new ArrayList<>();
        this.shields = new ArrayList<>();
        
        this.weaponCardDeck = new WeaponCardDeck();
        this.weaponCardDeck.addCards();
        this.shieldCardDeck = new ShieldCardDeck();
        this.shieldCardDeck.addCards();
    }
    
    public Player(Player other){
        super(other);
        this.number = other.number;
        this.consecutiveHits = other.consecutiveHits;
        this.weapons = other.weapons;
        this.shields = other.shields;
        this.weaponCardDeck = other.weaponCardDeck;
        
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
        this.setHealth(INITIAL_HEALTH);
        this.consecutiveHits = 0;
        /*
        FuzzyPlayer FuzzyMe = new FuzzyPlayer(this);
        Game.addPlayer(FuzzyMe);
        */
    }
    
    
    
    
    public char getNumber(){
        return this.number;
    }

    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        int size = validMoves.size();
        boolean contains = validMoves.contains(direction);
        
        if(size > 0 && !contains){
            return validMoves.get(0);
        }else{
            return direction;
        }
    }
    
    
    public float attack(){
        return this.sumWeapons() * this.getStrength();
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
                + super.toString()
                + ", number=" + number
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
        //return new Weapon(Dice.weaponPower(), Dice.usesLeft());
        return this.weaponCardDeck.nextCard();
        
    }
    
    private Shield newShield(){
        return new Shield(Dice.shieldPower(), Dice.usesLeft());
    }
    
    protected float sumWeapons(){
        float sum = 0;
        for(Weapon weapon: this.weapons){
            sum += weapon.attack();
        }
        return sum;
    }
    
    protected float sumShields(){
        float sum = 0;
        for(Shield shield: this.shields){
            sum += shield.defend();
        }
        return sum;
    }
    
    protected float defensiveEnergy(){
        return sumShields() + getIntelligence();
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
    
    private void resetHits(){
        this.consecutiveHits = 0;
    }
    
    private void incConsecutiveHits(){
        this.consecutiveHits += 1;
    }
}
