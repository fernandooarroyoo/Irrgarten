
package irrgarten;

public class Weapon {
    private float power;
    private int uses;
    static private int numWeapons=0;
    
    public Weapon(float power, int uses){
        this.power = power;
        this.uses = uses;
        Weapon.numWeapons = Weapon.numWeapons + 1;
    }
    
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
    
    @Override 
    public String toString(){
        return "W["+this.power+","+this.uses+"]";
    }
}
