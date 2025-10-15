
package irrgarten;

public class Weapon {
    private float power;
    private int uses;
    //private Player player; //esta variable indicará el owner de weapon
    //static private int numWeapons=0;
    
    public Weapon(float power, int uses){
        this.power = power;
        this.uses = uses;
        //this.player = null; 
        //Weapon.numWeapons = Weapon.numWeapons + 1;
    }
    
    //constructor por defecto
    /*public Weapon(){
        this(Dice.randomStrength(),Dice.usesLeft());
    }
    */
    public float attack(){
        if(this.uses > 0){
            this.uses = this.uses -1;
            return this.power;
        }
        else{
            return 0;
        }
    }
    
    public boolean discard(){
        return Dice.discardElement(this.uses);
    }
    
    /*public void setPlayer(Player player){
        this.player = player;
    }
    */
    
    @Override 
    public String toString(){
        return "W["+this.power+","+this.uses+"]";
    }
}
