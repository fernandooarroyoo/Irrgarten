
package irrgarten;

public class Monster {
    static private final int INITIAL_HEALTH = 5;
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    public Monster(String name, float intelligence, float strength){
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        
        this.health = INITIAL_HEALTH;
        row = -1;
        col = -1;
                
    }
    
    public boolean dead(){
        if (health <= 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    public float attack(){
        return Dice.intensity(strength);
    }
    
    public boolean defend(float receivedAttack){
        boolean isDead = this.dead();
        
        if(!isDead){
            float defensiveEnergy = Dice.intensity(this.intelligence);
    
            if(defensiveEnergy < receivedAttack){
                this.gotWounded();
                isDead = this.dead();
            }
        }
        return isDead;
    }
    
    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    @Override
    public String toString(){
        return "Name : " + this.name + "\n \t intelligence: " + this.intelligence + "\n \t strength: " + this.strength + "\n \t health: " + this.health + "\n \t position: [" + this.row + "," + this.col + "]";
    }
    
    private void gotWounded(){
        this.health -= 1;
    }
}
