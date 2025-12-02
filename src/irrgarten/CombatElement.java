
package irrgarten;

public abstract class CombatElement {
    private float effect;
    private int uses;
    
    public CombatElement(float effect, int uses){
        this.effect = effect;
        this.uses = uses;
    }
    
    protected float produceEffect(){
        if(this.uses > 0){
            this.uses = this.uses -1;
            return this.effect;
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
        return "["+this.effect+","+this.uses+"]";
    }
}
