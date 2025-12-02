
package irrgarten;

import java.util.Random;
import java.util.ArrayList;

public class Dice {
    //ATRIBUTOS PRIVADOS DE CLASE 
    
    
    static private final int MAX_USES = 5; //static es porque es de clase, final porque es constante
    static private final float MAX_INTELLIGENCE = 10f;
    static private final float MAX_STRENGTH = 10f;
    static private final float RESURRECT_PROB= 0.3f;
    static private final int WEAPONS_REWARD = 2;
    static private final int SHIELDS_REWARD = 3;
    static private final int HEALTH_REWARD = 5;
    static private final int MAX_ATTACK = 3;
    static private final int MAX_SHIELD = 2;
          
    static private Random generator = new Random();
    
    //definir los métodos de la clase
    static public int randomPos(int max){
        return generator.nextInt(max);
    }
    
    static public int whoStarts(int nplayers){
        return generator.nextInt(nplayers);
    }
    
    static public float randomIntelligence(){
        return generator.nextFloat() * MAX_INTELLIGENCE;
    }
    
    static public float randomStrength(){
        return generator.nextFloat() * MAX_STRENGTH;
    }
    
    static public boolean resurrectPlayer(){
        if(generator.nextFloat() < RESURRECT_PROB){
            return true;
        }
        else{ 
            return false;
        }
    }
    
    static public int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD);
    }
    
    static public int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD);
    }
    
    static public int healthReward(){
        return generator.nextInt(HEALTH_REWARD);
    }
    
    static public float weaponPower(){
        return generator.nextFloat() * MAX_ATTACK;
    }
    
    static public float shieldPower(){
        return generator.nextFloat() * MAX_SHIELD;
    }
    
    static public int usesLeft(){
        return generator.nextInt(MAX_USES);
    }
    
    static public float intensity(float competence){
        return generator.nextFloat() * competence;
    }
    
    static public boolean discardElement(int usesLeft){
        float prob = usesLeft/MAX_USES;
        
        if(generator.nextFloat() >= prob ){
            return true;
        }
        else{
            return false;
        }
    }
    
    static public Directions nextStep(Directions preference, ArrayList<Directions> validMoves, float intelligence){
        float proporcion = intelligence/100;
        float probabilidad = generator.nextFloat();
        
        if(probabilidad > proporcion){
            return preference;
        }else{
            int indice = generator.nextInt(validMoves.size());
            return validMoves.get(indice);
        }
        
        
    }
   
    
}
