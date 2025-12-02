
package irrgarten;

public class Monster extends LabyrinthCharacter{
    static private final int INITIAL_HEALTH = 5;
    
    public Monster(String name, float intelligence, float strength){
        super(name,intelligence,strength,INITIAL_HEALTH);
                
    }
    
    public float attack(){
        return Dice.intensity(this.getStrength());
    }
    
    public boolean defend(float receivedAttack){
        boolean isDead = this.dead();
        
        if(!isDead){
            float defensiveEnergy = Dice.intensity(this.getIntelligence());
    
            if(defensiveEnergy < receivedAttack){
                this.gotWounded();
                isDead = this.dead();
            }
        }
        return isDead;
    }
}
