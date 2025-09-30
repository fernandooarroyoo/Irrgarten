
package irrgarten;

public class Weapon {
    private float power;
    private int uses;
    private Dice dice;
    
    public Weapon(float power, int uses){
        this.power = power;
        this.uses = uses;
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
        return dice.discardElement(this.uses);
    }
    
    @Override 
    public String toString(){
        return "W["+this.power+","+this.uses+"]";
    }
}
