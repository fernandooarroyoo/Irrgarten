
package irrgarten;


public class Shield {
    private float protection;
    private int uses;
    //no hace falta crear un dado porque NO es un objeto
    
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
        return Dice.discardElement(this.uses); //se llama directamente a la clase
    }
    
    @Override
    public String toString(){
        return "S["+this.protection+","+this.uses+"]";
    }
}
