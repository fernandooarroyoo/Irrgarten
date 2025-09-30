
package irrgarten;

import java.util.Random;

public class Dice {
    //ATRIBUTOS PRIVADOS DE CLASE 
    
    //SE USA TB ESTA SINTAXIS PARA LAS CONSTANTES????
    private int MAX_USES = 5;
    private float MAX_INTELLIGENCE = 10f;
    private float MAX_STRENGTH = 10f;
    private float RESURRECT_PROB= 0.3f;
    private int WEAPONS_REWARD = 2;
    private int SHIELDS_REWARD = 3;
    private int HEALTH_REWARD = 5;
    private int MAX_ATTACK = 3;
    private int MAX_SHIELD = 2;
          
    private Random generator = new Random();
    
    //definir los métodos de la clase
    public int randomPos(int max){
        return this.generator.nextInt(max);
    }
    
    public int whoStarts(int nplayers){
        return this.generator.nextInt(nplayers);
    }
    
    public float randomIntelligence(){
        return this.generator.nextFloat(this.MAX_INTELLIGENCE);
    }
    
    public float randomStrength(){
        return this.generator.nextFloat(this.MAX_STRENGTH);
    }
    
    public boolean resurrectPlayer(){
        if(this.generator.nextFloat(1) < this.RESURRECT_PROB){
            return true;
        }
        else{
            return false;
        }
    }
    
    public int weaponsReward(){
        return this.generator.nextInt(this.WEAPONS_REWARD);
    }
    
    public int shieldsReward(){
        return this.generator.nextInt(this.SHIELDS_REWARD);
    }
    
    public int healthReward(){
        return this.generator.nextInt(this.HEALTH_REWARD);
    }
    
    public float weaponPower(){
        return this.generator.nextFloat(this.MAX_ATTACK);
    }
    
    public float shieldPower(){
        return this.generator.nextFloat(this.MAX_SHIELD);
    }
    
    public int usesLeft(){
        return this.generator.nextInt(this.MAX_USES);
    }
    
    public float intensity(float competence){
        return this.generator.nextFloat(competence);
    }
    
    public boolean discardElement(int usesLeft){
        float prob = usesLeft/this.MAX_USES;
        
        if(this.generator.nextFloat(1) >= prob ){
            return true;
        }
        else{
            return false;
        }
    }
   
    
}
