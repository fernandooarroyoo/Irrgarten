
package irrgarten;


public class Shield {
    private float protection;
    private int uses;
    private Dice dice;
    
    public Shield(float protection, int uses){
        this.protection = protection;
        this.uses = uses;
    }
    
    public float protect(){
        if (this.uses > 0){
            this.uses = this.uses - 1;
            return this.protection;
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
        return "S["+this.protection+","+this.uses+"]";
    }
}
